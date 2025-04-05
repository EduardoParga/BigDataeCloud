package com.ibmec.mall.ibmecmall.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    private String nome;
    private String cartaoCredito;
    private double saldo;

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCartaoCredito() {
        return cartaoCredito;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
