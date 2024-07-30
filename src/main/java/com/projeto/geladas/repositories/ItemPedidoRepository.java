package com.projeto.geladas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.geladas.models.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    List<ItemPedido> findByBebidaId(Long bebidaId);
}
