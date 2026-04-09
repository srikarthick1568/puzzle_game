package com.botree.csng.bundleproduct.service;

import com.botree.csng.bundleproduct.dto.BundleRequest;
import com.botree.csng.bundleproduct.entity.BundleProductHeader;

public interface IBundleProductService {

    void createBundleProduct(BundleRequest request);

}
