package com.ibmec.mall.ibmecmall.model;

public class RelatorioVendas {
    private int totalPedidos;
    private double valorTotalVendas;
    private int quantidadeTotalItensVendidos;

    // Construtor
    public RelatorioVendas(int totalPedidos, double valorTotalVendas, int quantidadeTotalItensVendidos) {
        this.totalPedidos = totalPedidos;
        this.valorTotalVendas = valorTotalVendas;
        this.quantidadeTotalItensVendidos = quantidadeTotalItensVendidos;
    }

    // Getters
    public int getTotalPedidos() {
        return totalPedidos;
    }

    public double getValorTotalVendas() {
        return valorTotalVendas;
    }

    public int getQuantidadeTotalItensVendidos() {
        return quantidadeTotalItensVendidos;
    }
}