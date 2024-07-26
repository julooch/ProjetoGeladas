package com.projeto.geladas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.geladas.models.Estoque;
import com.projeto.geladas.service.EstoqueService;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public List<Estoque> listarEstoques() {
        return estoqueService.listarEstoques();
    }

    @PostMapping
    public ResponseEntity<?> salvarEstoque(@RequestBody Estoque estoque) {
        try {
            Estoque novoEstoque = estoqueService.salvarEstoque(estoque);
            return new ResponseEntity<>(novoEstoque, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id) {
        estoqueService.deletarEstoque(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
