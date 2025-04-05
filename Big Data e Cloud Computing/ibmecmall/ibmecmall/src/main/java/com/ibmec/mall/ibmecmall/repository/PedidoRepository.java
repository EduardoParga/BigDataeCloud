package com.ibmec.mall.ibmecmall.repository;

import com.ibmec.mall.ibmecmall.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
}
