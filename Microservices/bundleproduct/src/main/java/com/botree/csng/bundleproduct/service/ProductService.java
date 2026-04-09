package com.botree.csng.bundleproduct.service;

import com.botree.csng.bundleproduct.entity.Product;
import com.botree.csng.bundleproduct.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private  ProductRepository productRepository;


    public Product getProduct(String cmpCode, String prodCode) {
        return productRepository
                .findByCmpCodeAndProdCode(cmpCode, prodCode)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
