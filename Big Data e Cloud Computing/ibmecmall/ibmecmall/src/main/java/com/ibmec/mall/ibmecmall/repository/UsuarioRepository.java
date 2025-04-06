package com.ibmec.mall.ibmecmall.repository;

import com.ibmec.mall.ibmecmall.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}

