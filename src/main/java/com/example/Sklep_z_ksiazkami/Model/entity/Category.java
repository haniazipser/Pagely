package com.example.Sklep_z_ksiazkami.Model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

//(Id int IDENTITY NOT NULL, Nazwa_kategorii varchar(255) NOT NULL, Kategoria_Nadrzedna int NULL, PRIMARY KEY (Id));
@Entity
@Table(name = "Kategoria")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "Nazwa_kategorii")
    String categoryName;
    //@Column(name = "Kategoria_Nadrzedna")

    @ManyToOne()
    @JoinColumn(name="Kategoria_Nadrzedna")
    @JsonIgnore
    Category parentCategory;

    @OneToMany(mappedBy="parentCategory")
    @JsonIgnore
    Set<Category> childCategories;

    public Category(){};

    public Category(int id, String categoryName, Category parentCategory, Set<Category> childCategories) {
        this.id = id;
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.childCategories = childCategories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<Category> childCategories) {
        this.childCategories = childCategories;
    }
}
