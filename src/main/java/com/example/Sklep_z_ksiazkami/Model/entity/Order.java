package com.example.Sklep_z_ksiazkami.Model.entity;

import com.example.Sklep_z_ksiazkami.Model.OrderStatus;
import com.example.Sklep_z_ksiazkami.Model.dto.OrderDetailsDto;
import com.example.Sklep_z_ksiazkami.Repozytorium.OrderException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.random;
import static java.lang.Math.round;
import static java.time.LocalDateTime.now;

//(Id int IDENTITY NOT NULL, Nr_zamowienia varchar(255) NOT NULL UNIQUE, Id_kupujacego int NULL,
// Data_zamowienia DATETIME, Kwota MONEY NOT NULL, Status_zamowienia varchar(255) NULL, Data_wysylki DATETIME NULL,
// Data_potwierdzenia_odbioru DATETIME NULL, Cena_wysylki MONEY NULL,   varchar(255) NULL,
@Entity
@Table(name = "Zamowienia")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "Nr_zamowienia")
    String orderNumber;
    @ManyToOne
    @JoinColumn(name="Id_kupujacego")
    //@JsonBackReference
    @JsonIgnore
    Client buyer;
    @Column(name = "Data_zamowienia")
    LocalDateTime orderDate;

    //@Column(name = "Kwota")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "value", column = @Column(name = "Kwota")),
    })
    Money orderTotal;

    @Column(name = "Status_zamowienia")
    @Enumerated(EnumType.STRING)
    OrderStatus status;
    @Column(name = "Data_wysylki")
    LocalDateTime shippingDate;
    @Column(name = "Data_potwierdzenia_odbioru")
    LocalDateTime deliveryDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "value", column = @Column(name = "Cena_wysylki")),
    })
    Money shippingCost;
    @Column(name = "Wybrany_sposob_wysylki")
    String shippingMethod;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    Set<OrderDetails> items;


    public Order(Integer id, String orderNumber, Client buyer, LocalDateTime orderDate, Money orderTotal, OrderStatus status, LocalDateTime shippingDate, LocalDateTime deliveryDate, Money shippingCost, String shippingMethod,Set<OrderDetails> items) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.buyer = buyer;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.status = status;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
        this.shippingCost = shippingCost;
        this.shippingMethod = shippingMethod;
        this.items = items;
    }

    //DECLARE @Nr_zamowienia varchar (255) = CONCAT(LEFT(@Nazwa_kupujacego, 3),'-',FORMAT(GETDATE(), 'yyyyMMddHHmmss'), '-', CAST(FLOOR(RAND() * 1000) AS INT)

    public Order(Client client){
        this.buyer = client;
        this.orderTotal = Money.zero();
        this.orderNumber = String.format("%s-%s-%d", client.getName().substring(0,3), now(), (int) (random() * 1000 + 1));
        this.status = OrderStatus.DRAFT;
        this.items = new HashSet<>();
    }

    public Order(){}

    public OrderDetailsDto addOffer(Offer offer, Money price){
        checkIfDraft();
        for (OrderDetails o : items){
            if (o.getOffer().equals(offer)){
                throw new OrderException("This offer is already in your cart");
            }
        }
        OrderDetails o = new OrderDetails(this, offer, price);
        items.add(o);
        orderTotal = orderTotal.add( price);
        return new OrderDetailsDto(o);
    }

    public void deleteOffer(OrderDetails o){
        checkIfDraft();
        items.remove(o);
        orderTotal = orderTotal.subtract( o.getPrice());
        o.order = null;
    }



    public void addShippingMethod(ShippingMethod method){
            shippingMethod = method.getId().getShippingMethod();
            shippingCost = method.getPrice();
            orderTotal = orderTotal.add(shippingCost);
    }

    public void changeShippingMethod(ShippingMethod method) {
        Money old = shippingCost;
        shippingMethod = method.getId().getShippingMethod();
        shippingCost = method.getPrice();
        orderTotal = orderTotal.subtract(old);
        orderTotal = orderTotal.add(shippingCost);
    }

    public void removeShippingMethod() {
        shippingMethod = null;
        shippingCost = Money.zero();
        orderTotal = Money.zero();
    }


    public void submit() {
        checkIfDraft();
        orderDate = now();
        status = OrderStatus.PAID;
    }

    public void checkIfDraft(){
        if (status != OrderStatus.DRAFT){
            throw new OrderException("This order is already submitted");
        }
    }

}
