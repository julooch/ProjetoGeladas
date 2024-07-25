package com.projeto.geladas.service;

import com.projeto.geladas.models.Bebida;
import com.projeto.geladas.models.Estoque;
import com.projeto.geladas.models.Pedido;
import com.projeto.geladas.repositories.BebidaRepository;
import com.projeto.geladas.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void createPedido(Pedido pedido) {
        this.pedidoRepository.save(pedido);

        Estoque estoque = new Estoque();
        estoque.setQuantidade();
    }



}
