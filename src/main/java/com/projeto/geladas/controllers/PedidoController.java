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

import com.projeto.geladas.DTOs.ItemPedidoDTO;
import com.projeto.geladas.DTOs.PedidoDTO;
import com.projeto.geladas.models.ItemPedido;
import com.projeto.geladas.models.Pedido;
import com.projeto.geladas.repositories.BebidaRepository;
import com.projeto.geladas.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private BebidaRepository bebidaRepository;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(pedidoDTO.getNumeroPedido());
        pedido.setFormaPagamento(pedidoDTO.getFormaPagamento());
        pedido.setStatusPagamento(pedidoDTO.isStatusPagamento());
        pedido.setDataPagamento(pedidoDTO.getDataPagamento());

        // Adicionar itens ao pedido
        for (ItemPedidoDTO itemDTO : pedidoDTO.getItensPedido()) {
            ItemPedido item = new ItemPedido();
            item.setBebida(bebidaRepository.findById(itemDTO.getBebidaId()).orElseThrow(() -> new ResourceNotFoundException("Bebida não encontrada")));
            item.setPrecoUnitario(itemDTO.getPrecoUnitario());
            item.setQuantidade(itemDTO.getQuantidade());
            pedido.adicionarItem(item);
        }

        Pedido pedidoSalvo = pedidoService.criarPedido(pedido);
        return ResponseEntity.ok(pedidoSalvo);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPedidoPorId(id);
        if (pedido != null) {
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
