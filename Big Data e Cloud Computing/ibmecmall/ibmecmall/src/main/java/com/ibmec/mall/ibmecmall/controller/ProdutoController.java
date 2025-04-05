package com.ibmec.mall.ibmecmall.controller;

import com.ibmec.mall.ibmecmall.model.Produto;
import com.ibmec.mall.ibmecmall.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable String id, @RequestBody Produto produtoAtualizado) {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setCategoria(produtoAtualizado.getCategoria());
        produto.setEstoque(produtoAtualizado.getEstoque());
        return produtoRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable String id) {
        produtoRepository.deleteById(id);
    }
}
