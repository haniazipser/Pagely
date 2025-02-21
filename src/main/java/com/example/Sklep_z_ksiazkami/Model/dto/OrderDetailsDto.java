package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.Offer;
import com.example.Sklep_z_ksiazkami.Model.entity.Order;
import com.example.Sklep_z_ksiazkami.Model.entity.OrderDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

public class OrderDetailsDto {
    Integer id;
    OfferDto offerDto;
    Float price;
   // Float discount;

    public OrderDetailsDto(OrderDetails orderDetails){
        this.id = orderDetails.getId();
        this.offerDto = new OfferDto(orderDetails.getOffer());
        this.price = orderDetails.getPrice();
       // this.discount = orderDetails.getDiscount();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OfferDto getOfferDto() {
        return offerDto;
    }

    public void setOfferDto(OfferDto offerDto) {
        this.offerDto = offerDto;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

   /* public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }*/
}
