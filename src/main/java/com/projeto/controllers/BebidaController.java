package com.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.geladas.models.Bebida;
import com.projeto.geladas.service.BebidaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bebidas")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;

    @PostMapping
    public ResponseEntity<Bebida> createBebida(@Valid @RequestBody Bebida bebida) {
        bebidaService.createBebida(bebida);
        return new ResponseEntity<>(bebida, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Bebida>> findAll(){
        List<Bebida> bebida = this.bebidaService.findAll();
        return new ResponseEntity<>(bebida, HttpStatus.OK);
    }
    
}
