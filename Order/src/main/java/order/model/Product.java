package order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Long id;
    private String category;
    private String name;
    private double price;
    private int stockQuantity;

    private ProductDetail productDetail;

    public Product(Long id, String category, String name, double price, int stockQuantity, ProductDetail productDetail) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productDetail = productDetail;
    }

    public Product(Long id, String category, String name, double price, int stockQuantity) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Product() {}

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public void setId(Long id) {
        this.id = id;
    }

}