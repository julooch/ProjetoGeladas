package com.projeto.geladas.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private int numeroPedido;

    @NotNull
    @Column
    private double valorTotal;

    @NotBlank
    @Size(max = 1000, message = "Caracteres maiores que 1000")
    @Column
    private String formaPagamento;

    @NotBlank
    @Size(max = 1000, message = "Caracteres maiores que 1000")
    @Column
    private boolean statusPagamento;


    @UpdateTimestamp
    @Column
    private Date dataPagamento;

    @OneToMany
    @JoinColumn(name = "bebida_id")
    private List<Bebida> bebidas;


}
