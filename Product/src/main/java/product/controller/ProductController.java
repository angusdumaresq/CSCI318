package product.controller;

import org.springframework.web.bind.annotation.*;
import product.model.Product;
import product.service.ProductService;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/allProducts")
    List<Product> findAllProducts () {
        return productService.findAllProducts();
    }

    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable Long id) {return productService.getProductById(id); }

    @PostMapping("/createProduct")
    Product createProduct(@RequestBody Product newProduct) {
        return productService.createProduct(newProduct);
    }

    @PutMapping("/updateProduct/{id}")
    Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping({"/deleteProduct/{id}"})
    void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


    @DeleteMapping({"/deleteProductOnly/{id}"})
    void deleteProductOnly(@PathVariable Long id) { productService.deleteProductOnly(id); }


    @PutMapping("/product/{id}/productDetail/{productDetailID}")
    Product updateProduct_ProductDetails(@PathVariable Long id, @PathVariable Long productDetailID ) {
        return productService.updateProduct_ProductDetails(id, productDetailID); }




    //For OrderService Communication.
    //****************************************************************************************************************************

    @GetMapping("/getProductByName/{name}")
    Product findProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping("/getQuantityByProductName/{name}")
    int findQuantityByProductName(@PathVariable String name) {
        return productService.findQuantityByProductName(name);
    }

    @GetMapping("/getPriceByProductName/{name}")
    double findPriceByProductName(@PathVariable String name) {
        return productService.findPriceByProductName(name);
    }

    @PutMapping("/updateQuantityByProductName/{name}/{quantity}")
    Product updateQuantityByProductName(@PathVariable String name, @PathVariable int quantity) {
        return productService.updateQuantityByProductName(name, quantity);
    }

}
