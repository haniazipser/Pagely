package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.Offer;
import com.example.Sklep_z_ksiazkami.Model.entity.Order;
import com.example.Sklep_z_ksiazkami.Model.entity.OrderDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDetailsDto {
    Integer id;
    OfferDto offerDto;
    Float price;

    public OrderDetailsDto(OrderDetails orderDetails){
        this.id = orderDetails.getId();
        this.offerDto = new OfferDto(orderDetails.getOffer());
        this.price = orderDetails.getPrice();
    }
}
