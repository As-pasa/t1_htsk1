package com.as_pasa.t1_htsk1.repositories;

import com.as_pasa.t1_htsk1.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
