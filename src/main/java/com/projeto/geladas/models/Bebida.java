package com.projeto.geladas.models;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_bebida")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotBlank
    @Size(max = 1000, message = "Caracteres maiores que 1000")
    @Column
    private String nome;

    @NotNull
    @Column
    private double preco;

    @UpdateTimestamp
    @Column
    private Date dataCadastro;

    @NotNull
    @Column
    private BebidaTipo tipo;
    
}
