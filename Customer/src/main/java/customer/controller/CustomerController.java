package customer.controller;

import org.springframework.web.bind.annotation.*;
import customer.model.Customer;
import customer.service.CustomerService;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/allCustomers")
    List<Customer> findAllAddress() {
        return customerService.findAllAddress();
    }

    @GetMapping("/customer/{id}")
    Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/createCustomer")
    Customer createCustomer(@RequestBody Customer newCustomer) {
        return customerService.createCustomer(newCustomer);
    }

    @PutMapping("/updateCustomer/{id}")
    Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id)  {
        return customerService.updateCustomer(customer, id);
    }

    @DeleteMapping({"/deleteCustomer/{id}"})
    void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }


    @PutMapping("/customer/{id}/contact/{contactID}")
    Customer updateCustomerContact(@PathVariable Long id, @PathVariable Long contactID ) {
        return customerService.updateCustomerContact(id, contactID);
    }


    @DeleteMapping({"/deleteCustomerOnly/{id}"})
    void deleteCustomerOnly(@PathVariable Long id) {
        customerService.deleteCustomerOnly(id);
    }


    //For OrderService Communication.
    //*************************************************************************************************************

    @GetMapping("/getCustomerAddress/{id}")
    String getCustomerAddressById(@PathVariable Long id) {
        return customerService.getCustomerAddressById(id);
    }

    @GetMapping("/getCustomerPhoneNumber/{id}")
    String getCustomerPhoneNumberById(@PathVariable Long id) {
        return customerService.getCustomerPhoneNumberById(id);
    }

}