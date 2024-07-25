package com.projeto.geladas.service;

import com.projeto.geladas.models.Bebida;
import com.projeto.geladas.models.Estoque;
import com.projeto.geladas.repositories.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
