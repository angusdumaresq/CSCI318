package order.loadDatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import order.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

@Configuration
public class LoadOrders {

    private static final Logger log = LoggerFactory.getLogger(LoadOrders.class);

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {

        return args -> {
            try {
                String [] productNames = {"Pizza", "Iphone-11", "Corona", "Vodka", "Soap"};
                Random random = new Random();

                while (!Thread.currentThread().isInterrupted()){

                    Long randomCustomerID = Integer.toUnsignedLong(random.nextInt(5)+1);

                    int randomProductIndex = random.nextInt( 5);

                    int randomStockQuantity = random.nextInt(10)+1;

                    Order order= new Order(randomCustomerID, productNames[randomProductIndex], randomStockQuantity);
                    log.info(order.toString());
                    try {
                        restTemplate.postForObject("http://localhost:8003/createOrder", order, Order.class);
                    }
                    catch (Exception e){
                        System.err.println(e.getMessage());
                    }
                    Thread.sleep(2000);
                }
            }
            catch(InterruptedException ignored){}
        };
    }
}
