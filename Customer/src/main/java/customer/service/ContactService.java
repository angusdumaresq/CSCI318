package customer.service;

import org.springframework.stereotype.Service;
import customer.customerException.CustomerException;
import customer.model.Contact;
import customer.model.Customer;
import customer.repository.ContactRepository;
import customer.repository.CustomerRepository;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final CustomerRepository customerRepository;

    public ContactService(ContactRepository contactRepository, CustomerRepository customerRepository) {
        this.contactRepository = contactRepository;
        this.customerRepository = customerRepository;
    }


    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }


    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new CustomerException(id, "Contact"));
    }


    public Contact createContact(Contact newContact) {
        if(contactRepository.findById(newContact.getId()).isPresent()) {
            throw new CustomerException("Contact", newContact.getId());
        }

        return contactRepository.save(newContact);
    }

    public Contact updateContact(Contact contact, Long id) {

        Contact foundContact = getContactById(id);

        if (foundContact == null) {
            throw new CustomerException(id, "Contact");
        }
        foundContact.setContact(contact);
        return contactRepository.save(foundContact);
    }

    public void deleteContact(Long id) { contactRepository.deleteById(id);}

   public void deleteContactOnly(Long id) {

        Customer customer = customerRepository.getById(id);

        deleteContact(id);

        if(customer != null) {
            customer.setContact(null);
            customerRepository.save(customer); }
    }


}
