package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.ShippingMethod;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShippingMethodDto {
    private Integer clientId;
    private String shippingMethod;
    Float price;

    public ShippingMethodDto(ShippingMethod shippingMethod){
        this.clientId = shippingMethod.getId().getClientId();
        this.shippingMethod = shippingMethod.getId().getShippingMethod();
        this.price = shippingMethod.getPrice();
    }
}
