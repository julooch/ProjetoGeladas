package com.projeto.geladas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.geladas.models.Estoque;
import com.projeto.geladas.models.Pedido;
import com.projeto.geladas.repositories.BebidaRepository;
import com.projeto.geladas.repositories.EstoqueRepository;
import com.projeto.geladas.repositories.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BebidaRepository bebidaRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Transactional
    public Pedido criarPedido(Pedido pedido) {
        // Atualiza o preço unitário de cada item do pedido e define o pedido
        pedido.getItensPedido().forEach(item -> {
            item.setPrecoUnitario(item.getBebida().getPreco());
            item.setPedido(pedido);
        });

        // Calcula o valor total do pedido
        pedido.calcularValorTotal();

        // Salva o pedido e os itens do pedido
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // Atualiza o estoque com base nos itens do pedido
        atualizarEstoque(pedido);

        return pedidoSalvo;
    }

    private void atualizarEstoque(Pedido pedido) {
        pedido.getItensPedido().forEach(item -> {
            Estoque estoque = estoqueRepository.findByBebidaId(item.getBebida().getId())
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado para a bebida id " + item.getBebida().getId()));

            // Atualiza a quantidade do estoque
            int novaQuantidade = estoque.getQuantidade() - item.getQuantidade();

            if (novaQuantidade < 0) {
                throw new RuntimeException("Estoque insuficiente para a bebida id " + item.getBebida().getId());
            }

            estoque.setQuantidade(novaQuantidade);
            estoqueRepository.save(estoque);
        });
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElse(null);
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }



}
