package com.example.Sklep_z_ksiazkami.Model.entity;

import com.example.Sklep_z_ksiazkami.Model.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
//Id int IDENTITY NOT NULL, Id_klienta int NULL, Data date NOT NULL, Tytul varchar(255) NOT NULL, Autor varchar(255) NOT NULL,
// Jezyk varchar(255) NULL, Opis text NULL, Stan varchar(255) NOT NULL, Rok_wydania int NULL, ISBN varchar(255) NULL, Cena MONEY NOT NULL, Id_kategorii int NOT NULL,
// Nr_oferty varchar(255) NOT NULL UNIQUE, Status_oferty varchar(255) NULL, Dezaktywowano DATETIME NULL PRIMARY KEY (Id))

@Entity
@Table (name="Oferty")
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
    @ManyToOne()
    @JoinColumn(name="ISBN")
    ISBN isbn;
    @Column(name = "Cena")
    Float price;

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

    public Offer(Integer id, Client client, LocalDate date, String title, String author, String language, String description, String state, Integer published, ISBN isbn, Float price, Category category, String offerNumber, Status status, LocalDateTime deactivated, Set <String> K) {
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
    public Offer(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public void setIsbn(ISBN isbn) {
        this.isbn = isbn;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOfferNumber() {
        return offerNumber;
    }

    public void setOfferNumber(String offerNumber) {
        this.offerNumber = offerNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(LocalDateTime deactivated) {
        this.deactivated = deactivated;
    }

    public Set<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Set<String> keyWords) {
        this.keyWords = keyWords;
    }
}
