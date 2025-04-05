package com.ibmec.mall.ibmecmall.repository;

import com.ibmec.mall.ibmecmall.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {
}
