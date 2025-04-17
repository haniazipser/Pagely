package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.entity.Offer;
import com.example.Sklep_z_ksiazkami.Model.entity.ShippingMethod;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OfferDto {
    private int id;
    private String title;
    private String author;
    String language;
    Integer published;
    private float price;
    private LocalDate date;
    private String state;
    private CategoryDto category;

    private String isbn;

    Set<String> keyWords;

    List<ShippingMethodDto> shippingMethods;



    public OfferDto(Offer offer) {
        this.id = offer.getId();
        this.title = offer.getTitle();
        this.author = offer.getAuthor();
        this.price = offer.getPrice();
        this.date = offer.getDate();
        this.state = offer.getState();
        this.keyWords = offer.getKeyWords();
        this.category= new CategoryDto(offer.getCategory());
        this.language = offer.getLanguage();
        this.published = offer.getPublished();
        this.isbn = offer.getIsbn();
    }

    public OfferDto(Offer offer, List<ShippingMethod> shippingMethod) {
        this.id = offer.getId();
        this.title = offer.getTitle();
        this.author = offer.getAuthor();
        this.price = offer.getPrice();
        this.date = offer.getDate();
        this.state = offer.getState();
        this.keyWords = offer.getKeyWords();
        this.category= new CategoryDto(offer.getCategory());
        this.language = offer.getLanguage();
        this.published = offer.getPublished();
        this.shippingMethods =shippingMethod.stream().map(s -> new ShippingMethodDto(s)).collect(Collectors.toList());
        this.isbn = offer.getIsbn();
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Set<String> keyWords) {
        this.keyWords = keyWords;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<ShippingMethodDto> getShippingMethods() {
        return shippingMethods;
    }

    public void setShippingMethods(List<ShippingMethodDto> shippingMethods) {
        this.shippingMethods = shippingMethods;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
