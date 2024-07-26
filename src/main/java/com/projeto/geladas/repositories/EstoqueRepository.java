package com.projeto.geladas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.geladas.models.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByBebidaId(Long bebidaId);
}
