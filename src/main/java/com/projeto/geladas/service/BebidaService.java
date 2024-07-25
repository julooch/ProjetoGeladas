package com.projeto.geladas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.geladas.models.Bebida;
import com.projeto.geladas.repositories.BebidaRepository;

@Service
public class BebidaService {

    @Autowired
    private BebidaRepository bebidaRepository;

    public List<Bebida> findAll() {
        return this.bebidaRepository.findAll();
    }

    public void createBebida(Bebida bebida){
        this.bebidaRepository.save(bebida);
    }

    public void deleteBebida(Long id){
        this.bebidaRepository.deleteById(id);
    }


}
