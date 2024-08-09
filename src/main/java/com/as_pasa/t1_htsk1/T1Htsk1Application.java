package com.as_pasa.t1_htsk1;

import com.as_pasa.t1_htsk1.models.User;
import com.as_pasa.t1_htsk1.services.OrderService;
import com.as_pasa.t1_htsk1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableAspectJAutoProxy
public class T1Htsk1Application {
    @Autowired
    public T1Htsk1Application(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(T1Htsk1Application.class, args);
    }


    private final OrderService orderService;

    private final UserService userService;


    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {

    }
}
