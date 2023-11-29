package customer.loadDataBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import customer.model.Contact;
import customer.model.Customer;
import customer.repository.CustomerRepository;

@Configuration
class LoadCustomers {

    private static final Logger log = LoggerFactory.getLogger(LoadCustomers.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Customer(1L, "KFC", "Street-123", "Australia", new Contact(1L, "Maysar", "04-1234-5678","Maysar@gmail.com", "Shift-SuperVisor") )));
            log.info("Preloading " + repository.save(new Customer(2L, "IT-Tech", "Green-Str", "Australia", new Contact(2L, "Yousef", "050-1838-5678","Yousef@gmail.com", "CEO") )));
            log.info("Preloading " + repository.save(new Customer(3L, "Bunnings", "Street-A321", "Australia", new Contact(3L, "Angus", "04-5426-1243","Lishy@gmail.com", "Manager") )));
            log.info("Preloading " + repository.save(new Customer(4L, "Noxus", "Street-971+", "Turkey", new Contact(4L, "Riven", "04-420-6969","Riven@gmail.com", "Exile") )));
            log.info("Preloading " + repository.save(new Customer(5L, "Coles", "Street-5/12", "Sweden", new Contact(5L, "Emily", "04-1642-7353","Emily@gmail.com", "Worker") )));
        };
    }
}