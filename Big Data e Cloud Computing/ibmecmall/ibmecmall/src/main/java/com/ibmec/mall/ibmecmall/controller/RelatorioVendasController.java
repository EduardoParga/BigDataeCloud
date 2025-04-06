package com.ibmec.mall.ibmecmall.controller;

import com.ibmec.mall.ibmecmall.model.Pedido;
import com.ibmec.mall.ibmecmall.model.RelatorioVendas;
import com.ibmec.mall.ibmecmall.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorio-vendas")
public class RelatorioVendasController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public RelatorioVendas gerarRelatorioVendas() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();

        int totalPedidos = todosPedidos.size();
        double valorTotalVendas = todosPedidos.stream().mapToDouble(Pedido::getTotal).sum();
        int quantidadeTotalItens = todosPedidos.stream().mapToInt(Pedido::getQuantidade).sum();

        return new RelatorioVendas(totalPedidos, valorTotalVendas, quantidadeTotalItens);
    }
}