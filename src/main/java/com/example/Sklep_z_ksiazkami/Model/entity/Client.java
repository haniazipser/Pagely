package com.example.Sklep_z_ksiazkami.Model.entity;

import com.example.Sklep_z_ksiazkami.Model.ClientType;
import com.example.Sklep_z_ksiazkami.Model.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

// Nazwa varchar(255) NULL, NIP varchar(255) NULL, Data_urodzenia date NULL, Plec varchar(255) NULL, Rodzaj_klienta varchar(255) NOT NULL, Status varchar(255) NOT NULL, Numer_rachunku varchar(255) NULL, Nazwa_konta varchar(255) NULL, Dezaktywowano DATETIME NULL, PRIMARY KEY (Id));
@Entity
@Table(name = "Klienci")
@Getter @Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="Nazwa")
    String name;
    @Column(name="NIP")
    String NIP;
    @Column(name="Rodzaj_klienta")
    @Enumerated(EnumType.STRING)
    ClientType type;
    @Column(name="Status")
    @Enumerated(EnumType.STRING)
    Status status;
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

    public Client(Integer id, String name, String NIP, ClientType type, Status status, LocalDateTime deactivated, Set<MyUser> users, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.NIP = NIP;
        this.type = type;
        this.status = status;
        this.deactivated = deactivated;
        this.users = users;
        this.orders = orders;
    }

    public Client(String name, String NIP, ClientType type, Status status) {
        this.name = name;
        this.NIP = NIP;
        this.type = type;
        this.status = status;
    }
}
