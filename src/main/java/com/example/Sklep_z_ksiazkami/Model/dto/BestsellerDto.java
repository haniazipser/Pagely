package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;
@Getter @Setter
public class BestsellerDto {

    int id;
    String title;
    String description;
    String category;
    Double rating;
    public BestsellerDto (Integer id, String title, String description, String category, Double rating){
        this.id = id;
        this.title = title;
        this.description =description;
        this.category = category;
        this.rating = rating;
    }

    public BestsellerDto(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.category = book.getCategory().getCategoryName();
    }
}
