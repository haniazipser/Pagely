package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.Book;

import java.util.stream.Collectors;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
