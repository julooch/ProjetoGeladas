package com.projeto.geladas.service;

import java.util.List;

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


}
