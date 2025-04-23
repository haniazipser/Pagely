package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.OrderStatus;
import com.example.Sklep_z_ksiazkami.Model.entity.Client;
import com.example.Sklep_z_ksiazkami.Model.entity.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
@Getter @Setter
public class OrderDto {
    private Integer id;
    private String orderNumber;
    private LocalDateTime orderDate;
    private Float orderTotal;
    private OrderStatus status;

    private LocalDateTime shippingDate;
    private LocalDateTime deliveryDate;
    private Float shippingCost;
    private String shippingMethod;

    private Set<OrderDetailsDto> items;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.orderDate = order.getOrderDate();
        this.orderTotal = order.getOrderTotal();
        this.status = order.getStatus();
        this.shippingDate = order.getShippingDate();
        this.deliveryDate = order.getDeliveryDate();
        this.shippingCost = order.getShippingCost();
        this.shippingMethod = order.getShippingMethod();
        this.items = order.getItems().stream().map(o -> new OrderDetailsDto(o)).collect(Collectors.toSet());
    }
}
