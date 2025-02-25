package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.BookReview;

import java.time.LocalDateTime;

public class BookReviewDto {
        Integer id;

        LocalDateTime date;

        String content;

        Integer stars;

        String client;
        String book;

        public BookReviewDto(BookReview bookReview){
            this.id = bookReview.getId();
            this.date = bookReview.getDate();
            this.content = bookReview.getContent();
            this.stars = bookReview.getStars();
            this.client = bookReview.getClient().getName();
            this.book = bookReview.getBook().getTitle();
        }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
