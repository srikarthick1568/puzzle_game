package com.botree.csng.bundleproduct.repository;

import com.botree.csng.bundleproduct.entity.BundleCreationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BundleCreationLogRepository extends JpaRepository<BundleCreationLog, Long> {
}