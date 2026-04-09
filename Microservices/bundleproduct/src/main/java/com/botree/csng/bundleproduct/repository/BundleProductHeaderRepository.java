package com.botree.csng.bundleproduct.repository;

import com.botree.csng.bundleproduct.entity.BundleProductHeader;
import com.botree.csng.bundleproduct.entity.BundleProductHeaderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BundleProductHeaderRepository extends JpaRepository<BundleProductHeader, BundleProductHeaderId> {



    Optional<BundleProductHeader> findByCmpCodeAndProductCode(String cmpCode, String productCode);

    boolean existsByCmpCodeAndProductCode(String cmpCode, String productCode);
}
