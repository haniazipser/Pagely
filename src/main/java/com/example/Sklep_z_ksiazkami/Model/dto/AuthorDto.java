package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.Author;
import com.example.Sklep_z_ksiazkami.Model.entity.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.Set;
import java.util.stream.Collectors;

public class AuthorDto {

    int id;

    String name;
    String biography;
    Set<BestsellerDto> books;

    public AuthorDto (Author author){
        this.id= author.getId();
        this.name = author.getName();
        this.biography = author.getBiography();
        this.books =author.getBooks().stream().map(b -> new BestsellerDto(b)).collect(Collectors.toSet());
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

    public Set<BestsellerDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BestsellerDto> books) {
        this.books = books;
    }
}
