package customer.service;

import org.springframework.stereotype.Service;
import customer.customerException.CustomerException;
import customer.model.Contact;
import customer.model.Customer;
import customer.repository.ContactRepository;
import customer.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;


    public CustomerService(CustomerRepository customerRepository, ContactRepository contactRepository) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
    }

    public List<Customer> findAllAddress() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return  customerRepository.findById(id) //
                .orElseThrow(() -> new CustomerException(id, "Customer"));
    }

    public Customer createCustomer(Customer newCustomer) {

        if(customerRepository.findById(newCustomer.getId()).isPresent()) {
            throw new CustomerException("Customer", newCustomer.getId());
        }
/*
        contactRepository.findById(newCustomer.getId())
                .ifPresent(contact -> {
                    contact.setContact(newCustomer.getContact());
                    newCustomer.setContact(contact);
                });
*/

        if (newCustomer.getContact() != null) {
            newCustomer.getContact().setId(newCustomer.getId());
        }

        return customerRepository.save(newCustomer);
    }

    public Customer updateCustomer(Customer customer, Long id)  {
        Customer foundCustomer = getCustomerById(id);
        if (foundCustomer == null) {
            throw new CustomerException(id , "Customer");
        }
        foundCustomer.setCustomer(customer);
        return customerRepository.save(foundCustomer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public void deleteCustomerOnly(Long id) {
        Customer customer = getCustomerById(id);
        Contact contact = customer.getContact();
        deleteCustomer(id);

        if(contact != null) {
            contact.setCustomer(null);
            contactRepository.save(contact); }
    }

    public Customer updateCustomerContact(Long id, Long contactID) {

        Customer customer = getCustomerById(id);

        Contact contact = contactRepository.findById(contactID)
                    .orElseThrow(() -> new CustomerException(contactID, "Contact"));

        customer.updateContact(contact);

        Customer extraCustomer = customerRepository.getById(contactID);

        contactRepository.deleteById(contactID);

        if(extraCustomer != null) {
          //extraCustomer.setContact(null); // It depends on how you think of it !!!
            customerRepository.save(extraCustomer); }

        return customerRepository.save(customer);
    }





//***************************************************************************************************************************

    public String getCustomerAddressById(Long id) {

        if(getCustomerById(id).getAddress() == null) {
            throw new CustomerException(id, "Address for Customer"); }

        return getCustomerById(id).getAddress();
    }

    public String getCustomerPhoneNumberById(Long id) {

        if(getCustomerById(id).getContact() == null ||getCustomerById(id).getContact().getPhone() == null) {
            throw new CustomerException(id, "Contact Information (Phone Number) for Customer"); }

     return getCustomerById(id).getContact().getPhone();
    }

}