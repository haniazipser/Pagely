package com.example.Sklep_z_ksiazkami.Model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

//(Id int IDENTITY NOT NULL, Imie_i_nazwisko nvarchar(255) NOT NULL, Krotka_biografia ntext NULL, PRIMARY KEY (Id));
@Entity
@Table(name = "Autorzy")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="Imie_i_nazwisko")
    String name;
    @Column(name="Krotka_biografia")
    String biography;
    @ManyToMany
    @JoinTable(
            name = "Autorzy_ksiazek",
            joinColumns = @JoinColumn(name = "Id_autora"),
            inverseJoinColumns = @JoinColumn(name = "Id_ksiazki"))
    @JsonBackReference
    Set <Book> books;

    public Author(){}

    public Author(int id, String name, String biography, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
