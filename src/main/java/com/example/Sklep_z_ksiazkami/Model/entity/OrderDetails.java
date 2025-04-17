package com.example.Sklep_z_ksiazkami.Model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

//Id int IDENTITY, Id_zamowienia int NULL, Id_oferty int NULL, [Cena_w_chwili_sprzedazy] MONEY NOT NULL,
// Znizka_w_chwili_sprzedazy FLOAT NULL
@Entity
@Table (name = "Szczegoly_zamowienia")
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
    @Column(name="Cena_w_chwili_sprzedazy")
    Float price;

    //@Column(name="Znizka_w_chwili_sprzedazy")
    //Float discount;

    public OrderDetails(Integer id, Order order, Offer offer, Float price) {
        this.id = id;
        this.order = order;
        this.offer = offer;
        this.price = price;
        //this.discount = discount;
    }

    public OrderDetails(Order order, Offer offer, Float price) {
        this.order = order;
        this.offer = offer;
        this.price = price;
        //this.discount = discount;
    }


    public OrderDetails(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
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
