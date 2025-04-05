package com.ibmec.mall.ibmecmall.repository;

import com.ibmec.mall.ibmecmall.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {
}
