package com.projeto.geladas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.geladas.models.Pedido;
import com.projeto.geladas.repositories.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void createPedido(Pedido pedido) {
        this.pedidoRepository.save(pedido);
    }



}
