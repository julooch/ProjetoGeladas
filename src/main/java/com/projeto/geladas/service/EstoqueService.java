package com.projeto.geladas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.geladas.models.Estoque;
import com.projeto.geladas.repositories.EstoqueRepository;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;


    public List<Estoque> listarEstoques() {
        return estoqueRepository.findAll();
    }

    public Estoque salvarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public void deletarEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }

    public Estoque atualizarQuantidade(Long id, int quantidade) {
        return estoqueRepository.findById(id)
                .map(estoque -> {
                    estoque.setQuantidade(quantidade);
                    return estoqueRepository.save(estoque);
                })
                .orElse(null);
    }

    public Estoque buscarEstoquePorId(Long id) {
        Optional<Estoque> estoque = estoqueRepository.findById(id);
        return estoque.orElse(null);
    }



}
