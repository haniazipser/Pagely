package com.example.Sklep_z_ksiazkami.Model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Id int IDENTITY, Id_zamowienia int NULL, Id_oferty int NULL, [Cena_w_chwili_sprzedazy] MONEY NOT NULL,
// Znizka_w_chwili_sprzedazy FLOAT NULL
@Entity
@Table (name = "Szczegoly_zamowienia")
@Getter @Setter
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name="Id_zamowienia")
    @JsonBackReference
    Order order;
    @ManyToOne
    @JoinColumn(name="Id_oferty")
    Offer offer;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "value", column = @Column(name = "Cena_w_chwili_sprzedazy")),
    })
    Money price;

    //@Column(name="Znizka_w_chwili_sprzedazy")
    //Float discount;

    public OrderDetails(Integer id, Order order, Offer offer, Money price) {
        this.id = id;
        this.order = order;
        this.offer = offer;
        this.price = price;
        //this.discount = discount;
    }

    public OrderDetails(Order order, Offer offer, Money price) {
        this.order = order;
        this.offer = offer;
        this.price = price;
        //this.discount = discount;
    }

    public OrderDetails(){}
}
