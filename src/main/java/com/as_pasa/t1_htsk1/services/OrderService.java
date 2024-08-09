package com.as_pasa.t1_htsk1.services;


import com.as_pasa.t1_htsk1.annotations.IgnoreLogging;
import com.as_pasa.t1_htsk1.exceptions.ApplicationException;
import com.as_pasa.t1_htsk1.models.Order;
import com.as_pasa.t1_htsk1.models.OrderState;
import com.as_pasa.t1_htsk1.models.User;
import com.as_pasa.t1_htsk1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository repo) {
        this.orderRepository = repo;
    }

    public Order persistOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order addOrder(User user, String details) {
        Order order = new Order(details, user, OrderState.NEW);
        return orderRepository.save(order);
    }

    public Order modifyOrder(Long id, Order details) {
        Order order = findById(id);
        order.setState(details);
        return orderRepository.save(order);

    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    @IgnoreLogging
    public Order initOrder(User user,String product){
        return new Order(product,user,OrderState.NEW);

    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ApplicationException("No such order"));

    }

}
