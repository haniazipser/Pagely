package com.example.Sklep_z_ksiazkami.Model.entity;

import com.example.Sklep_z_ksiazkami.Model.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
//Id int IDENTITY NOT NULL, Id_klienta int NULL, Data date NOT NULL, Tytul varchar(255) NOT NULL, Autor varchar(255) NOT NULL,
// Jezyk varchar(255) NULL, Opis text NULL, Stan varchar(255) NOT NULL, Rok_wydania int NULL, ISBN varchar(255) NULL, Cena MONEY NOT NULL, Id_kategorii int NOT NULL,
// Nr_oferty varchar(255) NOT NULL UNIQUE, Status_oferty varchar(255) NULL, Dezaktywowano DATETIME NULL PRIMARY KEY (Id))

@Entity
@Table (name="Oferty")
@Getter @Setter
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne()
    @JoinColumn(name="Id_klienta")
    @JsonBackReference
    Client client;
    @Column(name = "Data")
    LocalDate date;
    @Column(name = "Tytul")
    String title;
    @Column(name = "Autor")
    String author;
    @Column(name = "Jezyk")
    String language;
    @Column(name = "Opis")
    String description;
    @Column(name = "Stan")
    String state;
    @Column(name = "Rok_Wydania")
    Integer published;
    String isbn;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "value", column = @Column(name = "Cena")),
    })
    Money price;
    @ManyToOne()
    @JoinColumn(name="Id_kategorii")
    Category category;
    @Column(name = "Nr_oferty")
    String offerNumber;
    @Column(name = "Status_oferty")
    @Enumerated(EnumType.STRING)
    Status status;
    @Column(name = "Dezaktywowano")
    LocalDateTime deactivated;

    @ElementCollection
    @CollectionTable(
            name="Slowa_klucz",
            joinColumns=@JoinColumn(name="Id_oferty")
    )
    @Column(name="Slowo_klucz")
    Set<String> keyWords;

    public Offer(Integer id, Client client, LocalDate date, String title, String author, String language, String description, String state, Integer published, String isbn, Money price, Category category, String offerNumber, Status status, LocalDateTime deactivated, Set <String> K) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.title = title;
        this.author = author;
        this.language = language;
        this.description = description;
        this.state = state;
        this.published = published;
        this.isbn = isbn;
        this.price = price;
        this.category = category;
        this.offerNumber = offerNumber;
        this.status = status;
        this.deactivated = deactivated;
        this.keyWords = K;
    }
    public Offer(){}

    }
