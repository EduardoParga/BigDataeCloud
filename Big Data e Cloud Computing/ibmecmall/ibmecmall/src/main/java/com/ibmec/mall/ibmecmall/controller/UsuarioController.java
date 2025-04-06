package com.ibmec.mall.ibmecmall.controller;

import com.ibmec.mall.ibmecmall.model.Usuario;
import com.ibmec.mall.ibmecmall.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable String id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable String id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setCartaoCredito(usuarioAtualizado.getCartaoCredito());
            usuario.setSaldo(usuarioAtualizado.getSaldo());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        usuarioRepository.deleteById(id);
    }
}
