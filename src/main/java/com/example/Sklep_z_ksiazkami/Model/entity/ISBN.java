package com.example.Sklep_z_ksiazkami.Model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

//ISBN varchar(255) NOT NULL, Id_ksiazki int NOT NULL,
@Entity
@Table(name = "Nr_ISBN_ksiazek")
public class ISBN {
    @Id
    String ISBN;
    @ManyToOne()
    @JoinColumn(name="Id_ksiazki")
    @JsonBackReference
    Book book;

    @OneToMany(mappedBy = "isbn")
    Set<Offer> offers;

    public ISBN(){};

    public ISBN(String ISBN, Book book) {
        this.ISBN = ISBN;
        this.book = book;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
