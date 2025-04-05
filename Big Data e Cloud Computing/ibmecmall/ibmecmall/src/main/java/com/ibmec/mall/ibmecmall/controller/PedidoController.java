package com.ibmec.mall.ibmecmall.controller;

import com.ibmec.mall.ibmecmall.model.Pedido;
import com.ibmec.mall.ibmecmall.model.Produto;
import com.ibmec.mall.ibmecmall.model.Usuario;
import com.ibmec.mall.ibmecmall.repository.PedidoRepository;
import com.ibmec.mall.ibmecmall.repository.ProdutoRepository;
import com.ibmec.mall.ibmecmall.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public Object criarPedido(@RequestBody Pedido pedido) {
        Usuario usuario = usuarioRepository.findById(pedido.getIdUsuario()).orElse(null);
        Produto produto = produtoRepository.findById(pedido.getIdProduto()).orElse(null);

        if (usuario == null || produto == null) {
            return "Usuário ou Produto não encontrado";
        }

        double total = produto.getPreco() * pedido.getQuantidade();

        if (usuario.getSaldo() < total) {
            return "Saldo insuficiente no cartão de crédito";
        }

        usuario.setSaldo(usuario.getSaldo() - total);
        usuarioRepository.save(usuario);

        pedido.setTotal(total);
        return pedidoRepository.save(pedido);
    }

    @GetMapping("/relatorio-vendas")
    public List<Object> gerarRelatorio() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<Object> relatorio = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            Usuario usuario = usuarioRepository.findById(pedido.getIdUsuario()).orElse(null);
            Produto produto = produtoRepository.findById(pedido.getIdProduto()).orElse(null);

            if (usuario != null && produto != null) {
                Map<String, Object> item = new HashMap<>();
                item.put("produto", produto.getNome());
                item.put("usuario", usuario.getNome());
                item.put("quantidade", pedido.getQuantidade());
                item.put("total", pedido.getTotal());
                relatorio.add(item);
            }
        }

        return relatorio;
    }
}
