package com.projeto.geladas.models;


import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotNull
    @Column
    private boolean statusPagamento;


    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date dataPagamento;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itensPedido;

    @PrePersist
    @PreUpdate
    public void calcularValorTotal() {
        this.valorTotal = this.itensPedido.stream()
                                          .mapToDouble(item -> item.getQuantidade() * item.getPrecoUnitario())
                                          .sum();
    }


}
