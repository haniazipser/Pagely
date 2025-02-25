package com.example.Sklep_z_ksiazkami.Model.entity;

import com.example.Sklep_z_ksiazkami.Model.dto.BookDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//(Id int IDENTITY NOT NULL, Tytul varchar(255) NOT NULL, Opis text NULL, Id_kategorii int NOT NULL, PRIMARY KEY (Id), Rok_wydania int NOT NULL);
@Entity
@Table(name = "Ksiazki")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="Tytul")
    String title;
    @Column(name="Opis")
    String description;

    @ManyToOne
    @JoinColumn(name = "Id_kategorii")
    @JsonManagedReference
    Category category;
    @Column(name="Rok_wydania")
    int published;
    @ManyToMany(mappedBy = "books")
    @JsonManagedReference
    Set<Author> authors;
    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    Set <ISBN> ISBN;

    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    Set <BookReview> reviews;

    public Book(){};
    public Book(int id, String title, String description, Category category, int published, Set<Author> authors, Set<ISBN> ISBN, Set<BookReview> reviews) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.published = published;
        this.authors = authors;
        this.ISBN = ISBN;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }
    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
       this.authors = authors;
    }

    public Set<ISBN> getISBN() {
        return ISBN;
    }

    public void setISBN(Set<ISBN> ISBN) {
        this.ISBN = ISBN;
    }

    public Set<BookReview> getReviews() {
        return reviews;
    }

    public void setReviews(Set<BookReview> reviews) {
        this.reviews = reviews;
    }
}
