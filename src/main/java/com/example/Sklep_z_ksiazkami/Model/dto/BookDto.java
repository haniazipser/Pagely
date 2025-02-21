package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.Author;
import com.example.Sklep_z_ksiazkami.Model.entity.Book;
import com.example.Sklep_z_ksiazkami.Model.entity.Category;
import com.example.Sklep_z_ksiazkami.Model.entity.ISBN;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class BookDto {
    int id;
    String title;

    String description;
    CategoryDto category; //czy dto??
    int published;
    Set<AuthorDto> authors;
    Set <ISBNDto> ISBN;


    public BookDto (Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.category = new CategoryDto(book.getCategory());
        this.published = book.getPublished();
        this.authors = book.getAuthors().stream().map(a -> new AuthorDto(a)).collect(Collectors.toSet());
        this.ISBN = book.getISBN().stream().map(i -> new ISBNDto(i)).collect(Collectors.toSet());
    }

    /*public Book dtoToBook (){
        return new Book(id, title, description, category.dtoToCategory(), published, authors, ISBN);
    }*/

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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategoryId(CategoryDto category) {
        this.category = category;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public Set<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDto> authors) {
        this.authors = authors;
    }

    public Set<ISBNDto> getISBN() {
        return ISBN;
    }

    public void setISBN(Set<ISBNDto> ISBN) {
        this.ISBN = ISBN;
    }
}
