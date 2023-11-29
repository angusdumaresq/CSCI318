package order.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import order.orderException.OrderException;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private Long customerID;
    private String productName;
    private int stockQuantity;

    public Order(){}

    public Order(Long customerID, String productName, int stockQuantity) {
        this.customerID = customerID;
        this.productName = productName;

        validateOrder(stockQuantity);

        this.stockQuantity = stockQuantity;
    }

    private void validateOrder(int stockQuantity) {
        if (stockQuantity <= 0) {
            throw new OrderException(stockQuantity);
        }
    }


    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        validateOrder(stockQuantity);
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerID=" + customerID +
                ", productName='" + productName + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}