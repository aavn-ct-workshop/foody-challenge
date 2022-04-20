package com.vnagroup.driver.boundary;

import java.util.List;

import com.vnagroup.driver.control.DriverService;
import com.vnagroup.driver.entity.Delivery;
import com.vnagroup.driver.entity.Order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("drivers")
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }
    
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllReadyOrders() {
        return ResponseEntity.ok(service.getAllReadyOrders());
    }

    @PostMapping()
    public ResponseEntity<Void> updateOrderStatus(@RequestBody @NotNull @Valid Delivery delivery) {
        service.updateStatus(delivery);
        return ResponseEntity.ok().build();
    }
}
