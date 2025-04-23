package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.ISBN;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ISBNDto {
    String ISBN;
    BookDto book;

    public ISBNDto (ISBN ISBN){
        this.ISBN = ISBN.getISBN();
        this.book = new BookDto (ISBN.getBook());
    }
}
