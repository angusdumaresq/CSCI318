package order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetail {

    private Long id;
    private String description;
    private String detail;

    private Product product;


    public ProductDetail(Long id, String description, String detail, Product product) {
        this.id = id;
        this.description = description;
        this.detail = detail;
        this.product = product;
    }

    public ProductDetail(Long id, String description, String detail) {
        this.id = id;
        this.description = description;
        this.detail = detail;
    }

    public ProductDetail() {}

    public Long getId() {
        return id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductDetail(ProductDetail productDetail) {

        if (productDetail.description != null) {
            this.description = productDetail.description;
        }

        if (productDetail.detail != null) {
            this.detail = productDetail.detail;
        }
    }

}
