package com.as_pasa.t1_htsk1.services;

import com.as_pasa.t1_htsk1.entities.Order;
import com.as_pasa.t1_htsk1.entities.OrderState;
import com.as_pasa.t1_htsk1.entities.User;
import com.as_pasa.t1_htsk1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;


    @Autowired
    public OrderService(OrderRepository orderRepo) {
        this.orderRepository = orderRepo;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Not Found"));
    }

    public Order updateOrder(Long id, Order details) {
        Order order = findOrderById(id);
        order.setDescription(details.getDescription());
        order.setState(details.getState());
        order.setUser(details.getUser());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = findOrderById(id);
        orderRepository.delete(order);
    }
    public Order makeOrder(String description, User user){
        Order order= new Order(description,OrderState.waitingForCollection,user);
        return createOrder(order);
    }
}
