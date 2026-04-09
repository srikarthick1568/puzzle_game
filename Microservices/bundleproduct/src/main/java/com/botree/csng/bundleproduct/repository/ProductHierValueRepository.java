package com.botree.csng.bundleproduct.repository;

import com.botree.csng.bundleproduct.entity.ProductHierValue;
import com.botree.csng.bundleproduct.entity.ProductHierValueId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductHierValueRepository
        extends JpaRepository<ProductHierValue, ProductHierValueId> {

    List<ProductHierValue> findByCmpCode(String cmpCode);

    List<ProductHierValue> findByCmpCodeAndProdHierLvlCode(
            String cmpCode, Integer prodHierLvlCode);

    boolean existsByCmpCodeAndProdHierLvlCodeAndProdHierValCode(
            String cmpCode, Integer prodHierLvlCode, String prodHierValCode);

}
