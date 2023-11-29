package customer.controller;

import org.springframework.web.bind.annotation.*;
import customer.model.Contact;
import customer.service.ContactService;

import java.util.List;

@RestController
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/allContacts")
    List<Contact> findAllContacts() {
        return contactService.findAllContacts();
    }

    @GetMapping("/contact/{id}")
    Contact getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @PostMapping("/createContact")
    Contact createContact(@RequestBody Contact newContact) {
        return contactService.createContact(newContact);
    }


    @PutMapping("/updateContact/{id}")
    Contact updateContact(@RequestBody Contact contact, @PathVariable Long id) {
        return contactService.updateContact(contact, id);
    }

    @DeleteMapping({"/deleteContact/{id}"})
    void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }


    @DeleteMapping({"/deleteContactOnly/{id}"})
    void deleteContactOnly(@PathVariable Long id) {
        contactService.deleteContactOnly(id);
    }

}