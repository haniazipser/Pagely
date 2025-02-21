package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.ISBN;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class ISBNDto {
    String ISBN;
    BookDto book;

    public ISBNDto (ISBN ISBN){
        this.ISBN = ISBN.getISBN();
        this.book = new BookDto (ISBN.getBook());
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }
}
