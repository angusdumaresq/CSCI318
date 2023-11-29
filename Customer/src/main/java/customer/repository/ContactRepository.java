package customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import customer.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
