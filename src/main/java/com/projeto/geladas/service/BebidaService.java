package com.projeto.geladas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.geladas.models.Bebida;
import com.projeto.geladas.models.Estoque;
import com.projeto.geladas.repositories.BebidaRepository;

@Service
public class BebidaService {

    @Autowired
    private BebidaRepository bebidaRepository;

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

    public void deletarBebida(Long id) {
        bebidaRepository.deleteById(id);
    }


}
