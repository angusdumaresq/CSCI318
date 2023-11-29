package order.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderEvent  {

    @Id
    @GeneratedValue
    private Long orderID;

    private String supplier;

    private Long customerID;
    private String customerPhoneNO;
    private String customerAddress;

    private String productName;
    private double price;
    private int stockQuantity;

    @Override
    public String toString() {
        return "OrderEvent{" +
                "orderID=" + orderID +
                ", supplier='" + supplier + '\'' +
                ", customerID=" + customerID +
                ", customerPhoneNO='" + customerPhoneNO + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", productName='" + productName + '\'' +
                ", totalPrice=" + price +
                ", stockQuantity=" + stockQuantity +
                '}';
    }

    public OrderEvent(){}

    public OrderEvent(Order order, String customerAddress, String customerPhoneNO, double price)
    {
        setCustomerID(order.getCustomerID());
        setProductName(order.getProductName());
        setStockQuantity(order.getStockQuantity());
        this.customerAddress = customerAddress;
        this.customerPhoneNO = customerPhoneNO;
        this.price = price ;
        this.supplier = "OnlineOrderingSupplier#1";
    }


    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getCustomerPhoneNO() {
        return customerPhoneNO;
    }

    public void setCustomerPhoneNO(String customerPhoneNO) {
        this.customerPhoneNO = customerPhoneNO;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double totalPrice) {
        this.price = totalPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void updateOrder(Order orderUpdate) {}

}