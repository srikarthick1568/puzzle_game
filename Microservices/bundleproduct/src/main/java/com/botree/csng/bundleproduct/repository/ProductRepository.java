package com.botree.csng.bundleproduct.repository;

import com.botree.csng.bundleproduct.entity.Product;
import com.botree.csng.bundleproduct.entity.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductId> {



    Optional<Product> findByCmpCodeAndProdCode(String cmpCode, String prodCode);

}
