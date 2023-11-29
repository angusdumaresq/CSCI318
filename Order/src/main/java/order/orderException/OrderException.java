package order.orderException;

public class OrderException extends RuntimeException  {

    public OrderException(String Message) {
        super(Message);
    }

    public OrderException(int quantity, String productName, int availableQuantity) {
        super("-- The Ordered quantity (" + quantity + ") of the product ("+ productName +") exceeds the available quantity ("+ availableQuantity +") --");
    }

    public OrderException(Long orderId) {
    super("-- Warning : Could not find Any Orders with ID: " + orderId + " ! --");
    }

    public OrderException(int stockQuantity) {
        super("-- Warning : Stock Quantity Can't Be Negative : (" + stockQuantity + ") ! --");
    }

}
