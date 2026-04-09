package com.botree.csng.bundleproduct.service;

import com.botree.csng.bundleproduct.dto.BundleRequest;
import com.botree.csng.bundleproduct.entity.BundleCreationLog;
import com.botree.csng.bundleproduct.entity.BundleProductDetails;
import com.botree.csng.bundleproduct.entity.BundleProductHeader;
import com.botree.csng.bundleproduct.repository.BundleProductDetailsRepository;
import com.botree.csng.bundleproduct.repository.BundleProductHeaderRepository;
import com.botree.csng.bundleproduct.repository.BundleCreationLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BundleProductService implements IBundleProductService {

    private final BundleProductHeaderRepository bundleProHeaderRepository;
    private final BundleProductDetailsRepository bundleProDetailsRepository;
    private final BundleCreationLogRepository bundleCreationLogRepository;

    public BundleProductService(BundleProductHeaderRepository bundleProHeaderRepository, BundleProductDetailsRepository bundleProDetailsRepository,BundleCreationLogRepository bundleCreationLogRepository) {
        this.bundleProHeaderRepository = bundleProHeaderRepository;
        this.bundleProDetailsRepository = bundleProDetailsRepository;
        this.bundleCreationLogRepository=bundleCreationLogRepository;
    }

    public BundleRequest getProduct(String cmpCode, String productCode) {
        BundleProductHeader header = bundleProHeaderRepository
                .findByCmpCodeAndProductCode(cmpCode, productCode)
                .orElseThrow(() -> new RuntimeException("Not found"));

        List<BundleProductDetails> details =
                bundleProDetailsRepository.findByParentCode(productCode);

        BundleRequest response = new BundleRequest();
        response.setHeader(header);
        response.setDetails(details);

        return response;
    }

    @Transactional
    public void createBundleProduct(BundleRequest request) {

        BundleProductHeader header = request.getHeader();

        if (bundleProHeaderRepository.existsByCmpCodeAndProductCode(
                header.getCmpCode(), header.getProductCode())) {
            throw new RuntimeException("Bundle Product already exists");
        }


        header.setModDt(LocalDateTime.now());

        bundleProHeaderRepository.save(header);

        if (request.getDetails() != null) {
            for (BundleProductDetails d : request.getDetails()) {
                d.setParentCode(header.getParentCode());
                d.setCmpCode(header.getCmpCode());
                d.setDistrCode(header.getDistrCode());
                d.setDistrBrCode(header.getDistrBrCode());
            }
            bundleProDetailsRepository.saveAll(request.getDetails());
        }


        BundleCreationLog log = new BundleCreationLog();
        log.setCmpCode(header.getCmpCode());
        log.setDistrCode(header.getDistrCode());
        log.setWorkAssignmentID("WF-" + System.currentTimeMillis());
        log.setApprovedProductCode(header.getProductCode());
        log.setApprovedBy("SYSTEM");
        log.setApprovedDate(new Date());
        log.setModdt(new Date());
        log.setModUserCode("");
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonPayload = mapper.writeValueAsString(request.toString());
            log.setPayload(jsonPayload);
        } catch (Exception e) {
            throw new RuntimeException("Error converting payload to JSON", e);
        }


        bundleCreationLogRepository.save(log);

//        // ✅ save UOM
//        if (request.getUomList() != null) {
//            for (BundleProductUOM u : request.getUomList()) {
//                u.setProductCode(header.getProductCode());
//            }
//            uomRepository.saveAll(request.getUomList());
//        }
    }

}
