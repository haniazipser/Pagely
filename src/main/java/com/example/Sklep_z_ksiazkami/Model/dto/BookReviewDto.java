package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.BookReview;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
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
}
