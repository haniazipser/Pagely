package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.ShippingMethod;
import jakarta.persistence.Column;

public class ShippingMethodDto {
    private Integer clientId;
    private String shippingMethod;
    Float price;

    public ShippingMethodDto(ShippingMethod shippingMethod){
        this.clientId = shippingMethod.getId().getClientId();
        this.shippingMethod = shippingMethod.getId().getShippingMethod();
        this.price = shippingMethod.getPrice();
    }

    public ShippingMethodDto(){}

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
