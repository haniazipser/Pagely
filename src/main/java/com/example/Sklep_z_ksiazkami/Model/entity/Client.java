package com.example.Sklep_z_ksiazkami.Model.entity;

import com.example.Sklep_z_ksiazkami.Model.ClientType;
import com.example.Sklep_z_ksiazkami.Model.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

// Nazwa varchar(255) NULL, NIP varchar(255) NULL, Data_urodzenia date NULL, Plec varchar(255) NULL, Rodzaj_klienta varchar(255) NOT NULL, Status varchar(255) NOT NULL, Numer_rachunku varchar(255) NULL, Nazwa_konta varchar(255) NULL, Dezaktywowano DATETIME NULL, PRIMARY KEY (Id));
@Entity
@Table(name = "Klienci")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="Nazwa")
    String name;
    @Column(name="NIP")
    String NIP;
    @Column(name="Data_urodzenia")
    LocalDate dateOfBirth;
    @Column(name="Plec")
    String sex;
    @Column(name="Rodzaj_klienta")
    @Enumerated(EnumType.STRING)
    ClientType type;
    @Column(name="Status")
    @Enumerated(EnumType.STRING)
    Status status;
    @Column(name="Numer_rachunku")
    String accountNumber;
    @Column(name="Nazwa_konta")
    String accountHolder;
    @Column(name="Dezaktywowano")
    LocalDateTime deactivated;
    @JsonManagedReference
    @OneToMany(mappedBy = "client")
    Set <MyUser> users;
    @JsonManagedReference
    @OneToMany(mappedBy = "client")
    Set <Offer> offers;
    @JsonManagedReference
    @OneToMany(mappedBy = "buyer")
    Set<Order> orders;



    public Client(){}

    public Client(Integer id, String name, String NIP, LocalDate dateOfBirth, String sex, ClientType type, Status status, String accountNumber, String accountHolder, LocalDateTime deactivated, Set<MyUser> users, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.NIP = NIP;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.type = type;
        this.status = status;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.deactivated = deactivated;
        this.users = users;
        this.orders = orders;
    }

    public Client(String name, String NIP, LocalDate dateOfBirth, String sex, ClientType type, Status status, String accountNumber, String accountHolder) {
        this.name = name;
        this.NIP = NIP;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.type = type;
        this.status = status;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public LocalDateTime getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(LocalDateTime deactivated) {
        this.deactivated = deactivated;
    }

    public Set<MyUser> getUsers() {
        return users;
    }

    public void setUsers(Set<MyUser> users) {
        this.users = users;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
