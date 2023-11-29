package product.productException;

public class ProductException extends RuntimeException {

    public ProductException(String productName) {
        super("-- The Product called (" + productName + ") does not exist! --");
    }

    public ProductException(Long id, String name) {
        super("-- Warning : Could not find " + name + " with ID : " + id + "! --");
    }

    public ProductException(String name, Long id) {
        super("-- Warning :" + name + " with ID : " + id + " already Exists ! --");
    }

    public ProductException(double number) {
        super("--The set number "+ number + " is invalid ! --");
    }

}