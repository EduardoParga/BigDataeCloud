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

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable String id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setCartaoCredito(usuarioAtualizado.getCartaoCredito());
        usuario.setSaldo(usuarioAtualizado.getSaldo());
        return usuarioRepository.save(usuario);
    }
}
