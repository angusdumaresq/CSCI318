package customer.customerException;

public class CustomerException extends RuntimeException {

    public CustomerException(Long id, String name) {
        super("-- Warning : Could not find " + name + " with ID : " + id + "! --");
    }

    public CustomerException(String name, Long id) {
        super("-- Warning :" + name + " with ID : " + id + " already Exists ! --");
    }
}