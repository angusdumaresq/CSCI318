package product.loadDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import product.model.Product;
import product.model.ProductDetail;
import product.repository.ProductRepository;


@Configuration
class LoadProducts {

    private static final Logger log = LoggerFactory.getLogger(LoadProducts.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Product(1L,"Food","Pizza", 10.5, 5000, new ProductDetail(1L,"Very Yummy Italian Pizza", " Calories : 350, Fat : 10G, Protein : 25G"))));
            log.info("Preloading " + repository.save(new Product(2L,"Electronics","Iphone-11", 5000, 200, new ProductDetail(2L,"Preimum smart phone from Apple", "Liquid Retina HD display · 6.1‑inch (diagonal) all-screen LCD Multi-Touch display with IPS technology"))));
            log.info("Preloading " + repository.save(new Product(3L,"Drinks","Corona", 15.2, 5000, new ProductDetail(3L,"Very Yummy Alcohol", " Calories : 500, Fat : 100G, Protein : 50G"))));
            log.info("Preloading " + repository.save(new Product(4L,"Drinks","Vodka", 20.5, 5000, new ProductDetail(4L,"Very Tasty Alcohol", " Calories : 650, Fat : 150G, Protein : 55G"))));
            log.info("Preloading " + repository.save(new Product(5L,"Cleaning-Product","Soap", 5, 5000, new ProductDetail(5L,"Very Clean Soap", "100Grams, Silk white Soap."))));

        };

    }
}