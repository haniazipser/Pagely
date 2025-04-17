package com.example.Sklep_z_ksiazkami.Model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

//Recenzje_ksiazek (Id int IDENTITY NOT NULL, [Data] date NOT NULL,	Tresc text NULL,
// Ilosc_gwiazdek int NOT NULL, Id_wystawiajacego int NULL, Id_ksiazki int NOT NULL, PRIMARY KEY (Id));
@Entity
@Table(name="Recenzje_ksiazek")
public class BookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;
    @Column(name="Data")
    LocalDateTime date;
    @Column(name="Tresc")
    String content;
    @Column(name="Ilosc_gwiazdek")
    Integer stars;
    @ManyToOne
    @JoinColumn(name="Id_wystawiajacego")
    Client client;
    @ManyToOne
    @JoinColumn(name="Id_ksiazki")
    @JsonBackReference
    Book book;

    public BookReview(Integer id, LocalDateTime date, String content, Integer starts, Client client, Book book) {
        Id = id;
        this.date = date;
        this.content = content;
        this.stars = starts;
        this.client = client;
        this.book = book;
    }

    public BookReview(){}

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer starts) {
        this.stars = starts;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
