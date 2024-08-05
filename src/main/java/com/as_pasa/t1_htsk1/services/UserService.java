package com.as_pasa.t1_htsk1.services;

import com.as_pasa.t1_htsk1.entities.Order;
import com.as_pasa.t1_htsk1.entities.User;
import com.as_pasa.t1_htsk1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));

    }

    public User updateUser(Long id, User details) {
        User user = findUserById(id);
        user.setName(details.getName());
        user.setEmail(details.getEmail());
        user.setOrders(details.getOrdersList());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    public User addOrder(Long uId, Order order) {
        User user = findUserById(uId);
        List<Order> orders = user.getOrdersList();
        orders.add(order);
        user.setOrders(orders);
        return userRepository.save(user);
    }
    public User createEmtpyUser(String name, String email){
        return createUser(new User(name,email));
    }
}
