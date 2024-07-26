package com.projeto.geladas.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@JsonIgnoreProperties({"itensPedido"})
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
    @JsonManagedReference
    private List<ItemPedido> itensPedido = new ArrayList<>();

    public void adicionarItem(ItemPedido item) {
        itensPedido.add(item);
        item.setPedido(this);
        calcularValorTotal();
    }

    @PrePersist
    @PreUpdate
    public void calcularValorTotal() {
        this.valorTotal = this.itensPedido.stream()
                                          .mapToDouble(item -> item.getQuantidade() * item.getPrecoUnitario())
                                          .sum();
    }


}
