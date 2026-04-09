package com.botree.csng.bundleproduct.service;

import com.botree.csng.bundleproduct.entity.ProductHierValue;
import com.botree.csng.bundleproduct.repository.ProductHierValueRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductHierValueService {

    private final ProductHierValueRepository repository;

    public ProductHierValueService(ProductHierValueRepository repository) {
        this.repository = repository;
    }

    public List<ProductHierValue> getByLevel(String cmpCode, Integer levelCode) {
        return repository.findByCmpCodeAndProdHierLvlCode(cmpCode, levelCode);
    }

    public List<ProductHierValue> getAll() {
        return repository.findAll();
    }
    public  List<ProductHierValue> getByCmpCode(String cmpCode ){
        return repository.findByCmpCode(cmpCode);
    }

}
