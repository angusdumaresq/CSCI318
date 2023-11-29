package product.model;

import org.hibernate.annotations.NaturalId;
import product.productException.ProductException;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    private Long id;

    private String category;

    @NaturalId
    @Column(nullable=false)
    private String name;

    private double price;

    private int stockQuantity;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private ProductDetail productDetail;


    public Product(Long id, String category, String name, double price, int stockQuantity, ProductDetail productDetail) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;

        if(stockQuantity >= 0)
            this.stockQuantity = stockQuantity;
        else
            throw new ProductException(stockQuantity);

        if(price > 0)
            this.productDetail = productDetail;
        else
            throw new ProductException(price);
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


    public void setProduct(Product product) {
        if (product.category != null) {
            this.setCategory(product.category);
        }
        if (product.name != null) {
            this.setName(product.name);
        }
        if (product.price > 0.0) {
            this.setPrice(product.price);
        }
        else {
            throw new ProductException(product.price);
        }

        if(product.stockQuantity >= 0) {
            this.setStockQuantity(product.stockQuantity);
        }
        else {
            throw new ProductException(product.stockQuantity);
        }

        if(product.getProductDetail() != null)
        {
            this.productDetail.setProductDetail(product.getProductDetail());
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", productDetail=" + productDetail +
                '}';
    }

    public void updateProductDetail(ProductDetail productDetail){
        if(this.productDetail == null)
            this.productDetail = new ProductDetail();

        this.productDetail.setId(this.getId());
        this.productDetail.setDetail(productDetail.getDetail());
        this.productDetail.setDescription(productDetail.getDetail());
    }

}