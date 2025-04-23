package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.entity.Offer;
import com.example.Sklep_z_ksiazkami.Model.entity.ShippingMethod;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Getter @Setter
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
}
