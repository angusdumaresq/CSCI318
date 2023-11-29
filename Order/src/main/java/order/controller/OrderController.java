package order.controller;

import org.springframework.web.bind.annotation.*;
import order.model.Customer;
import order.model.Order;
import order.model.OrderEvent;
import order.model.Product;
import order.service.OrderService;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/allOrders")
    List<OrderEvent> allOrders() {
       return orderService.findAllOrders();
    }

    @GetMapping("/Order/{id}")
    OrderEvent getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }


    @DeleteMapping({"/deleteOrder/{id}"})
    void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }


    //****************************************************************************************************************************

    @GetMapping("getCustomerInfoByOrderID/{orderID}")
    Customer getCustomerInfoByOrderID(@PathVariable Long orderID){
        return orderService.getCustomerInfoByOrderID(orderID);
    }

    @GetMapping("getProductInfoByOrderID/{orderID}")
    Product getProductInfoByOrderID(@PathVariable Long orderID){
        return orderService.getProductInfoByOrderID(orderID);
    }

    @PostMapping("/createOrder")
    Order createOrder(@RequestBody Order newOrder) {
        return orderService.createOrder(newOrder);
    }

    //****************************************************************************************************************************
}
