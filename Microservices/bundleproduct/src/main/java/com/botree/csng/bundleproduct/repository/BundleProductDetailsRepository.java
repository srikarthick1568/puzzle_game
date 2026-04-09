package com.botree.csng.bundleproduct.repository;

import com.botree.csng.bundleproduct.entity.BundleProductDetails;
import com.botree.csng.bundleproduct.entity.BundleProductDetailsId;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BundleProductDetailsRepository extends JpaRepository<BundleProductDetails, BundleProductDetailsId> {
    List<BundleProductDetails> findByParentCode(String productCode);

//    Optional<BundleProductDetails> findByCmpCodeAndProdCode(
//            String cmpCode, String prodCode);

//    List<BundleProductDetails> findByCmpCodeAndIsActive(
//            String cmpCode, String isActive);

//    boolean existsByCmpCodeAndProdCode(
//            String cmpCode, String prodCode);
}