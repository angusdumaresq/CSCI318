package product.service;

import org.springframework.stereotype.Service;
import product.model.Product;
import product.model.ProductDetail;
import product.productException.ProductException;
import product.repository.ProductDetailRepository;
import product.repository.ProductRepository;

import java.util.List;

@Service
public class ProductDetailService {

    private final ProductDetailRepository productDetailRepository;
    private final ProductRepository productRepository;

    public ProductDetailService(ProductDetailRepository productDetailRepository, ProductRepository productRepository) {
        this.productDetailRepository = productDetailRepository;
        this.productRepository = productRepository;
    }

    public List<ProductDetail> findAllProductDetail () {
        return productDetailRepository.findAll();
    }

    public ProductDetail getProductDetailById (Long id) {
        return productDetailRepository.findById(id)
                .orElseThrow(() -> new ProductException(id , "Product-Detail"));
    }

    public ProductDetail createProductDetail (ProductDetail newProductDetail) {

        if(productDetailRepository.findById(newProductDetail.getId()).isPresent()) {
            throw new ProductException("Product-Detail", newProductDetail.getId());
        }

        return productDetailRepository.save(newProductDetail);
    }

    public ProductDetail updateProductDetail(ProductDetail productDetail, Long id) {
        ProductDetail foundProductDetail = getProductDetailById(id);
        if (foundProductDetail == null) {
            throw new ProductException(id, "Product-Detail");
        }
        foundProductDetail.setProductDetail(productDetail);
        return productDetailRepository.save(foundProductDetail);
    }


    public void deleteProductDetail (Long id) {
        productDetailRepository.deleteById(id);
    }

    public void deleteProductDetailOnly(Long id) {

        Product product = productRepository.getById(id);

        deleteProductDetail(id);

        if(product != null) {
            product.setProductDetail(null);
            productRepository.save(product); }
        }
}