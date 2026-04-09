package com.botree.csng.bundleproduct.dto;

import com.botree.csng.bundleproduct.entity.BundleProductHeader;
import com.botree.csng.bundleproduct.entity.BundleProductDetails;
import com.botree.csng.bundleproduct.entity.BundleProductUOM;
import lombok.Data;

import java.util.List;

@Data
public class BundleRequest {

    private BundleProductHeader header;
    private List<BundleProductDetails> details;
    private List<BundleProductUOM> uomList;
}