package com.projeto.geladas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projeto.geladas.exceptions.ResourcesNotFoundException;
import com.projeto.geladas.models.Bebida;
import com.projeto.geladas.models.Estoque;
import com.projeto.geladas.models.ItemPedido;
import com.projeto.geladas.repositories.BebidaRepository;
import com.projeto.geladas.repositories.ItemPedidoRepository;

@Service
public class BebidaService {

    @Autowired
    private BebidaRepository bebidaRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Bebida salvarBebida(Bebida bebida) {
        // Inicializar o estoque com um valor padrão
        Estoque estoque = new Estoque();
        estoque.setQuantidade(0); // Valor padrão para quantidade
        estoque.setBebida(bebida);
        
        bebida.setEstoque(estoque);
        
        return bebidaRepository.save(bebida);
    }

    public List<Bebida> listarBebidas() {
        return bebidaRepository.findAll();
    }

    public void excluirBebida(Long id) {
        Bebida bebida = bebidaRepository.findById(id)
            .orElseThrow(() -> new ResourcesNotFoundException("Bebida não encontrada"));

        // Verificar se a bebida está associada a algum ItemPedido
        List<ItemPedido> itens = itemPedidoRepository.findByBebidaId(id);
        if (!itens.isEmpty()) {
            throw new DataIntegrityViolationException("Não é possível excluir a bebida. Ela está associada a um ou mais itens de pedido.");
        }

        bebidaRepository.delete(bebida);
    }

    public Bebida buscarBebidaPorId(Long id) {
        Optional<Bebida> bebida = bebidaRepository.findById(id);
        return bebida.orElse(null);
    }

}
