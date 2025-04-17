package com.example.Sklep_z_ksiazkami.Model.entity;

import com.example.Sklep_z_ksiazkami.Model.ShippingMethodId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


//(Id_klienta int NOT NULL, Sposob_wysylki varchar(255) NOT NULL,
// Cena MONEY NOT NULL, PRIMARY KEY (Id_klienta, Sposob_wysylki));
@Entity
@Table(name="Obslugiwane_sposoby_wysylki")
public class ShippingMethod {
    @EmbeddedId
    ShippingMethodId id;

    @Column(name = "Cena")
    Float price;

    public ShippingMethod(ShippingMethodId id, Float price) {
        this.id = id;
        this.price = price;
    }

    public ShippingMethod(){}

    public ShippingMethodId getId() {
        return id;
    }

    public void setId(ShippingMethodId id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
