package com.example.Sklep_z_ksiazkami.Model.entity;

import com.example.Sklep_z_ksiazkami.Model.ShippingMethodId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


//(Id_klienta int NOT NULL, Sposob_wysylki varchar(255) NOT NULL,
// Cena MONEY NOT NULL, PRIMARY KEY (Id_klienta, Sposob_wysylki));
@Entity
@Table(name="Obslugiwane_sposoby_wysylki")
@Getter @Setter
public class ShippingMethod {
    @EmbeddedId
    ShippingMethodId id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "value", column = @Column(name = "Cena")),
    })
    Money price;

    public ShippingMethod(ShippingMethodId id, Money price) {
        this.id = id;
        this.price = price;
    }

    public ShippingMethod(){}

}
