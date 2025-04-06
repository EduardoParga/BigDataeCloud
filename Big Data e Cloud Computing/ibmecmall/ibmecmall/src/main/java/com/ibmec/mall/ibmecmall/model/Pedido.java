package com.ibmec.mall.ibmecmall.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Geração automática do ID
    private Long id;  // Agora é do tipo Long

    @NotBlank(message = "ID do usuário é obrigatório")
    private String idUsuario;

    @NotBlank(message = "ID do produto é obrigatório")
    private String idProduto;

    @Min(value = 1, message = "Quantidade deve ser maior que zero")
    private int quantidade;

    @PositiveOrZero(message = "Total deve ser positivo")
    private double total;

    // Construtores
    public Pedido() {}

    public Pedido(String idUsuario, String idProduto, int quantidade, double total) {
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.total = total;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getIdProduto() { return idProduto; }
    public void setIdProduto(String idProduto) { this.idProduto = idProduto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
