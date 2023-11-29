package product.service;

import org.springframework.stereotype.Service;
import product.model.ProductDetail;
import product.productException.ProductException;
import product.model.Product;
import product.repository.ProductDetailRepository;
import product.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    public ProductService(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
    }

    public List<Product> findAllProducts () {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductException(id , "Product"));
    }

    public Product createProduct (Product newProduct) {

        if(productRepository.findById(newProduct.getId()).isPresent()) {
            throw new ProductException("Product", newProduct.getId());
        }

        if (newProduct.getProductDetail() != null) {
            newProduct.getProductDetail().setId(newProduct.getId());
        }

        return productRepository.save(newProduct);
    }

    public Product updateProduct(Product product, Long id) {
        Product foundProduct = getProductById(id);
        if(foundProduct == null) {
            throw new ProductException(id, "Product");
        }

        foundProduct.setProduct(product);

        return productRepository.save(foundProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product findByName(String name) {
        Product foundProduct = productRepository.findByName(name);

        if (foundProduct == null) {
            throw new ProductException(name); }

        return foundProduct;
    }

    public void deleteProductOnly(Long id) {
        Product product = getProductById(id);

        ProductDetail productDetail = product.getProductDetail();

        deleteProduct(id);

        if(productDetail != null) {
            productDetail.setProduct(null);
            productDetailRepository.save(productDetail); }

    }


    public Product updateProduct_ProductDetails(Long id, Long ProductDetailID) {

        Product product = getProductById(id);
        ProductDetail productDetail = productDetailRepository.findById(ProductDetailID)
                .orElseThrow(() -> new ProductException(ProductDetailID, "Product-Detail"));

        product.updateProductDetail(productDetail);

        Product extraProduct = productRepository.getById(ProductDetailID);

        productDetailRepository.deleteById(ProductDetailID);

        if(extraProduct != null) {
            //extraProduct.setProductDetail(null); // It depends on how you think of it !!!
            productRepository.save(extraProduct); }

        return productRepository.save(product);
    }


//********************************************************************************************************************************************************

    public int findQuantityByProductName(String name) {
        Product foundProduct = findByName(name);
        return foundProduct.getStockQuantity();
    }

    public double findPriceByProductName(String name) {
        Product foundProduct = findByName(name);
        return foundProduct.getPrice();
        }


    public Product updateQuantityByProductName(String name, int quantity) {
        Product foundProduct = findByName(name);
        foundProduct.setStockQuantity(foundProduct.getStockQuantity() - quantity);
        return productRepository.save(foundProduct);
    }

}