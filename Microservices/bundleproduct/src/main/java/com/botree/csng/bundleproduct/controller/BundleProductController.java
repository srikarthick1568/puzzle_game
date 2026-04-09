

package com.botree.csng.bundleproduct.controller;

        import com.botree.csng.bundleproduct.dto.ApiResponse;
        import com.botree.csng.bundleproduct.dto.BundleRequest;
        import com.botree.csng.bundleproduct.entity.BundleProductHeader;
        import com.botree.csng.bundleproduct.entity.ProductHierValue;
        import com.botree.csng.bundleproduct.service.BundleProductService;
        import com.botree.csng.bundleproduct.service.ProductHierValueService;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Map;


@RestController
@RequestMapping("/api/bundle")
public class BundleProductController {

    private final BundleProductService service;

    private final ProductHierValueService prodHierValService;


    /**
     *
     * @param service
     * @param prodHierValService
     */
    public BundleProductController(BundleProductService service, ProductHierValueService prodHierValService) {
        this.service = service;
        this.prodHierValService = prodHierValService;
    }

    /**
     *
     * @param cmpcode,productcode
     * @return
     */
    @GetMapping("/getBundlePro")
    public BundleRequest getBundleProduct(@RequestParam String cmpCode, @RequestParam String productCode) {

        return service.getProduct(cmpCode, productCode);
    }
    @GetMapping("/test")
    public String  getTest() {
        return "test";
    }

    /**
     *
     * @param BundleRequest payload
     * @return
     */

    @PostMapping("/createBundle")
    public ResponseEntity<?> createNewBundleProduct(@RequestBody BundleRequest request) {
        try {
            service.createBundleProduct(request);
            return ResponseEntity.ok("Bundle Created Successfully");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    @GetMapping("/product-hier-value/{cmpCode}")
    public List<String> getAllLevels(@PathVariable String cmpCode) {

        return prodHierValService.getByCmpCode(cmpCode)
                .stream()
                .map(r ->
                        r.getProdHierValName() + "/" +
                                r.getProdHierValCode() + "/" +
                                r.getProdHierLvlCode()
                )
                .toList();
    }

}
