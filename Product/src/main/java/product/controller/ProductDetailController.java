package product.controller;

import org.springframework.web.bind.annotation.*;
import product.model.ProductDetail;
import product.service.ProductDetailService;

import java.util.List;

@RestController
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    public ProductDetailController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @GetMapping("/allProductDetails")
    List<ProductDetail> findAllAddress() {
        return productDetailService.findAllProductDetail();
    }

    @GetMapping("/productDetails/{id}")
    ProductDetail getProductDetailById(@PathVariable Long id) {
        return productDetailService.getProductDetailById(id);
    }

    @PostMapping("/createProductDetail")
    ProductDetail createProductDetail(@RequestBody ProductDetail newProductDetail) {
        return productDetailService.createProductDetail(newProductDetail);
    }

    @PutMapping("/updateProductDetail/{id}")
    ProductDetail updateProductDetail(@RequestBody ProductDetail productDetail, @PathVariable Long id) throws Exception {
        return productDetailService.updateProductDetail(productDetail, id);
    }

    @DeleteMapping({"/deleteProductDetail/{id}"})
    void deleteProductDetail(@PathVariable Long id) {
        productDetailService.deleteProductDetail(id);
    }

    @DeleteMapping({"/deleteProductDetailOnly/{id}"})
    void deleteProductDetailOnly(@PathVariable Long id) {
        productDetailService.deleteProductDetailOnly(id);
    }
}