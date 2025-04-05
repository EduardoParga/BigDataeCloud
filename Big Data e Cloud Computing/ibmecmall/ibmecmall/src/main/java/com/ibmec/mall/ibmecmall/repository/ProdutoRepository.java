package com.ibmec.mall.ibmecmall.repository;

import com.ibmec.mall.ibmecmall.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
}
