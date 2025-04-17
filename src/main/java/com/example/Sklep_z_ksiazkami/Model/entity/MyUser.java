package com.example.Sklep_z_ksiazkami.Model.entity;


import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.UserType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Login varchar(255) UNIQUE NOT NULL, Haslo varchar(255) NOT NULL, Id_klienta int NOT NULL, Rodzaj_uzytkownika varchar(255) NOT NULL, Status_uzytkownika varchar(255) NOT NULL
@Entity
@Table(name = "Uzytkownicy")
@Getter @Setter
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "Login")
    String login;
    @Column(name = "Haslo")
    String password;

    @Column(name="Imie")
    String name;

    @Column(name = "Zdjecie")
    String picture;
    @ManyToOne
    @JoinColumn(name = "Id_klienta")
    @JsonBackReference
    Client client;
    @Column(name = "Rodzaj_uzytkownika")
    @Enumerated(EnumType.STRING)
    UserType type;
    @Column(name = "Status_uzytkownika")
    @Enumerated(EnumType.STRING)
    Status status;
    @Column(name = "Rola")
    String role;

    public MyUser(){}

    public MyUser(String login, String password, Client client, UserType type, Status status,String name, String picture, String role) {
        this.login = login;
        this.password = password;
        this.client = client;
        this.type = type;
        this.status = status;
        this.name = name;
        this.picture=picture;
        this.role = role;
    }

}
