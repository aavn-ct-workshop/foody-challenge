package com.axonactive.workshop.order.service.repository;

import com.axonactive.workshop.order.service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
