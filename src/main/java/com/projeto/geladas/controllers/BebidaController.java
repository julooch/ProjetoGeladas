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

import com.projeto.geladas.models.Bebida;
import com.projeto.geladas.service.BebidaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bebidas")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;

    @GetMapping
    public List<Bebida> listarBebidas() {
        return bebidaService.listarBebidas();
    }

    @PostMapping
    public ResponseEntity<?> salvarBebida(@Valid @RequestBody Bebida bebida) {
        try {
            Bebida novaBebida = bebidaService.salvarBebida(bebida);
            return new ResponseEntity<>(novaBebida, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirBebida(@PathVariable Long id) {
            bebidaService.excluirBebida(id);
            return ResponseEntity.noContent().build();
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bebida> obterBebidaPorId(@PathVariable Long id) {
        Bebida bebida = bebidaService.buscarBebidaPorId(id);
        return new ResponseEntity<>(bebida, HttpStatus.OK);
    }
    
}
