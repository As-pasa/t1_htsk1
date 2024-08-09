package com.as_pasa.t1_htsk1.repositories;

import com.as_pasa.t1_htsk1.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
