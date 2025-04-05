package com.ibmec.mall.ibmecmall.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pedidos")
public class Pedido {

    @Id
    private String id;
    private String idUsuario;
    private String idProduto;
    private int quantidade;
    private double total;

    public String getId() {
        return id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getTotal() {
        return total;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
