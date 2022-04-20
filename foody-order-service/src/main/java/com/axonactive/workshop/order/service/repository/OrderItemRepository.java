package com.axonactive.workshop.order.service.repository;

import com.axonactive.workshop.order.service.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

}
