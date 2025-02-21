package com.example.Sklep_z_ksiazkami.Model.entity;


import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.UserType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
//Login varchar(255) UNIQUE NOT NULL, Haslo varchar(255) NOT NULL, Id_klienta int NOT NULL, Rodzaj_uzytkownika varchar(255) NOT NULL, Status_uzytkownika varchar(255) NOT NULL
@Entity
@Table(name = "Uzytkownicy")
public class User {
    @Id
    @Column(name = "Login")
    String login;
    @Column(name = "Haslo")
    String password;
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

    public User(){};

    public User(String login, String password, Client client, UserType type, Status status) {
        this.login = login;
        this.password = password;
        this.client = client;
        this.type = type;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    ////////////////////////////////////////////

}
