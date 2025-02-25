package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class BookDto {
    int id;
    String title;
    String description;
   CategoryDto category;
    int published;
    Set<AuthorDto> authors;
    Set <String> ISBN;

    Double rating;
    Set <BookReviewDto> reviews;


    public BookDto (Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.category = new CategoryDto( book.getCategory());
        this.published = book.getPublished();
        this.authors = book.getAuthors().stream().map(a -> new AuthorDto(a)).collect(Collectors.toSet());
        this.ISBN = book.getISBN().stream().map(i -> i.getISBN()).collect(Collectors.toSet());
        this.reviews = book.getReviews().stream().map(r -> new BookReviewDto(r)).collect(Collectors.toSet());
        Set<BookReview> reviews= book.getReviews();
        Double sum = 0.0;
        for (BookReview r : reviews){
            sum += r.getStars();
        }
        this.rating = sum / reviews.size();
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

    public void setCategory(CategoryDto category) {
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

    public Set<String> getISBN() {
        return ISBN;
    }

    public void setISBN(Set<String> ISBN) {
        this.ISBN = ISBN;
    }


    public Set<BookReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(Set<BookReviewDto> reviews) {
        this.reviews = reviews;
    }


    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
