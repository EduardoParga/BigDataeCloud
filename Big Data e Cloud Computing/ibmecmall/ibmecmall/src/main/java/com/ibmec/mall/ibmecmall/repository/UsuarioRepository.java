package com.ibmec.mall.ibmecmall.repository;

import com.ibmec.mall.ibmecmall.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
