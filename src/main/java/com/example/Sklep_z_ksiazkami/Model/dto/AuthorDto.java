package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.Author;
import com.example.Sklep_z_ksiazkami.Model.entity.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;
@Getter @Setter
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
}
