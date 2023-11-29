package order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import order.model.Customer;
import order.model.Order;
import order.model.OrderEvent;
import order.model.Product;
import order.orderException.OrderException;
import order.repository.OrderEventRepository;

import java.util.List;

@Service
public class OrderService {

    private ApplicationEventPublisher publisher;
    private RestTemplate restTemplate;
    private OrderEventRepository orderEventRepository;

    @Autowired
    public void OrderService(ApplicationEventPublisher publisher, RestTemplate restTemplate, OrderEventRepository orderEventRepository )
    {
        this.publisher = publisher;
        this.restTemplate = restTemplate;
        this.orderEventRepository = orderEventRepository;
    }

    public Customer getCustomerInfoByOrderID(Long orderId)
    {
        OrderEvent orderEvent = orderEventRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId));

        Customer customer;

        try {
            customer= restTemplate.getForObject("http://localhost:8001/customer/" + orderEvent.getCustomerID(), Customer.class);
        }
        catch (Exception exception) {
            throw new OrderException(exception.getMessage());
        }

        return customer;
    }

    public Product getProductInfoByOrderID(Long orderId) {

        OrderEvent orderEvent = orderEventRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId));

        Product product;

        try {
            product = restTemplate.getForObject("http://localhost:8002/getProductByName/" + orderEvent.getProductName(), Product.class);
        }
        catch (Exception exception) {
            throw new OrderException(exception.getMessage());
        }

        return product;
    }

    public List<OrderEvent> findAllOrders()
    {
        return orderEventRepository.findAll();
    }


    public OrderEvent getOrderById(Long id) {
        return  orderEventRepository.findById(id) //
                .orElseThrow(() -> new OrderException(id));
    }

// ******************************************************************************************************************************

    public Order createOrder(Order order)  {

        try {
            String customerAddress = restTemplate.getForObject("http://localhost:8001/getCustomerAddress/" + order.getCustomerID(), String.class);
            String  customerPhoneNo = restTemplate.getForObject("http://localhost:8001/getCustomerPhoneNumber/" + order.getCustomerID(), String.class);
            int availableQuantity = restTemplate.getForObject("http://localhost:8002/getQuantityByProductName/" + order.getProductName(), Integer.class);

            if(order.getStockQuantity() > availableQuantity)
            {
                throw new OrderException(order.getStockQuantity(),order.getProductName(),availableQuantity);
            }
            //returns the unit price
            double price = restTemplate.getForObject("http://localhost:8002/getPriceByProductName/" + order.getProductName(), double.class);


            OrderEvent orderEvent = new OrderEvent(order, customerAddress, customerPhoneNo, price);

            //Publishing the OrderEvent.
            publisher.publishEvent(orderEvent);
            return order;
        }
        catch (Exception exception) {
            throw new OrderException(exception.getMessage());
        }

    }

//======================================================================================================================================

    public void deleteOrder(Long id) {

        OrderEvent foundOrder = getOrderById(id);

        foundOrder.setStockQuantity(-foundOrder.getStockQuantity());

        //Publishing To reverse the entry
        publisher.publishEvent(foundOrder);

        orderEventRepository.deleteById(id);
    }

}