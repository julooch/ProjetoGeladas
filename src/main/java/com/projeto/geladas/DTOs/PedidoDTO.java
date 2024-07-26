package com.projeto.geladas.DTOs;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {

    private int numeroPedido;
    private double valorTotal;
    private String formaPagamento;
    private boolean statusPagamento;
    private Date dataPagamento;
    private List<ItemPedidoDTO> itensPedido;
    
}
