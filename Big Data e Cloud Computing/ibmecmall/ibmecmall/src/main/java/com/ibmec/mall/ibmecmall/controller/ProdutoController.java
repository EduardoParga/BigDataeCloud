package com.ibmec.mall.ibmecmall.controller;

import com.ibmec.mall.ibmecmall.model.Produto;
import com.ibmec.mall.ibmecmall.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepo;

    // [Regra] GET /produtos → Listar produtos
    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
        return produtoRepo.findAll();
    }

    // [Regra] POST /produto → Criar um novo produto
    @PostMapping("/produto")
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoRepo.save(produto);
    }

    // [Regra] PUT /produto/{id} → Atualizar um produto
    @PutMapping("/produto/{id}")
    public Produto atualizarProduto(@PathVariable String id, @RequestBody Produto produtoAtualizado) {
        produtoAtualizado.setId(id);
        return produtoRepo.save(produtoAtualizado);
    }

    // [Regra] DELETE /produto/{id} → Remover um produto
    @DeleteMapping("/produto/{id}")
    public void removerProduto(@PathVariable String id) {
        produtoRepo.deleteById(id);
    }
}