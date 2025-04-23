package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.Book;
import com.example.Sklep_z_ksiazkami.Model.entity.BookReview;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;
@Getter @Setter
public class BookDto {
    int id;
    String title;
    String description;

    String fullDescription;
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
        this.fullDescription = book.getFullDescription();
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
}
