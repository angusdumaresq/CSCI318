package order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import order.model.OrderEvent;
import order.repository.OrderEventRepository;


@Service
public class OrderEventHandler {

    private final RestTemplate restTemplate;
    private final OrderEventRepository orderEventRepository;
    private final StreamBridge streamBridge;

    @Autowired
    public OrderEventHandler(RestTemplate restTemplate, OrderEventRepository orderEventRepository, StreamBridge streamBridge)
    {
        this.restTemplate = restTemplate;
        this.orderEventRepository = orderEventRepository;
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void handle(OrderEvent orderEvent) {
        String url = "http://localhost:8002/updateQuantityByProductName/" + orderEvent.getProductName() + "/" + orderEvent.getStockQuantity();

        restTemplate.put( url, String.class );

        orderEventRepository.save(orderEvent);

        streamBridge.send("order-outbound", orderEvent);
    }
}