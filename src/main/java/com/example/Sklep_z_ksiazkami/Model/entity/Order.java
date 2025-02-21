package com.example.Sklep_z_ksiazkami.Model.entity;

import com.example.Sklep_z_ksiazkami.Model.OrderStatus;
import com.example.Sklep_z_ksiazkami.Repozytorium.OrderException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

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
    @Column(name = "Kwota")
    Float orderTotal;
    @Column(name = "Status_zamowienia")
    @Enumerated(EnumType.STRING)
    OrderStatus status;
    @Column(name = "Data_wysylki")
    LocalDateTime shippingDate;
    @Column(name = "Data_potwierdzenia_odbioru")
    LocalDateTime deliveryDate;
    @Column(name = "Cena_wysylki")
    Float shippingCost;
    @Column(name = "Wybrany_sposob_wysylki")
    String shippingMethod;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    Set<OrderDetails> items;


    public Order(Integer id, String orderNumber, Client buyer, LocalDateTime orderDate, Float orderTotal, OrderStatus status, LocalDateTime shippingDate, LocalDateTime deliveryDate, Float shippingCost, String shippingMethod,Set<OrderDetails> items) {
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
        this.orderTotal = 0f;
        this.orderNumber = String.format("%s-%s-%d", client.getName().substring(0,3), now().toString(), (int) (random() * 1000 + 1));
        this.status = OrderStatus.DRAFT;
        this.items = new HashSet<>();
    }

    public Order(){};

    public void addOffer(Offer offer, Float price){
        checkIfDraft();
        items.add(new OrderDetails(this, offer, price));
        orderTotal += price;
    }

    public void deleteOffer(OrderDetails o){
        checkIfDraft();
        items.remove(o);
        orderTotal -= o.getPrice();
        o.order = null;
    }



    public void addShippingMethod(ShippingMethod method){
            shippingMethod = method.getId().getShippingMethod();
            shippingCost = method.getPrice();
            orderTotal +=shippingCost;
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

    ////////////////

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Client getBuyer() {
        return buyer;
    }

    public void setBuyer(Client buyer) {
        this.buyer = buyer;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Float getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Float shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Set<OrderDetails> getItems() {
        return items;
    }

    public void setItems(Set<OrderDetails> items) {
        this.items = items;
    }


}
