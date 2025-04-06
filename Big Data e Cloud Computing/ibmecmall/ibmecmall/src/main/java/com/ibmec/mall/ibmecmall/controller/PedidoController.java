package com.ibmec.mall.ibmecmall.controller;

import com.ibmec.mall.ibmecmall.model.*;
import com.ibmec.mall.ibmecmall.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    // [Regra] POST /pedido → Criar um novo pedido
    @PostMapping
    @Transactional
    public ResponseEntity<?> criarPedido(@Valid @RequestBody PedidoRequest request) {
        try {
            // 1. Valida usuário e produto
            Usuario usuario = usuarioRepo.findById(request.getIdUsuario())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

            Produto produto = produtoRepo.findById(request.getIdProduto())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

            // 2. Valida saldo e estoque
            double valorTotal = produto.getPreco() * request.getQuantidade();

            if (usuario.getSaldo() < valorTotal) {
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED)
                        .body("Saldo insuficiente no cartão");
            }

            if (produto.getEstoque() < request.getQuantidade()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Estoque insuficiente");
            }

            // 3. Atualiza saldo e estoque
            usuario.setSaldo(usuario.getSaldo() - valorTotal);
            produto.setEstoque(produto.getEstoque() - request.getQuantidade());

            usuarioRepo.save(usuario);
            produtoRepo.save(produto);

            // 4. Cria o pedido
            Pedido pedido = new Pedido();
            pedido.setIdUsuario(request.getIdUsuario());
            pedido.setIdProduto(request.getIdProduto());
            pedido.setQuantidade(request.getQuantidade());
            pedido.setTotal(valorTotal);

            Pedido pedidoSalvo = pedidoRepo.save(pedido);
            logger.info("Pedido criado com sucesso: ID {}", pedidoSalvo.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);

        } catch (ResponseStatusException e) {
            logger.error("Erro na requisição: {}", e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            logger.error("Erro inesperado ao processar o pedido: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar pedido: " + e.getMessage());
        }
    }

    // Classe DTO interna
    static class PedidoRequest {
        @NotBlank(message = "ID do usuário é obrigatório")
        private String idUsuario;

        @NotBlank(message = "ID do produto é obrigatório")
        private String idProduto;

        @Min(value = 1, message = "Quantidade deve ser no mínimo 1")
        private int quantidade;

        // Getters e Setters
        public String getIdUsuario() { return idUsuario; }
        public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

        public String getIdProduto() { return idProduto; }
        public void setIdProduto(String idProduto) { this.idProduto = idProduto; }

        public int getQuantidade() { return quantidade; }
        public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    }
}
