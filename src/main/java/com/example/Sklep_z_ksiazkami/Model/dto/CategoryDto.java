package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryDto {
    int id;
    String categoryName;
    Set<CategoryDto> childCategories;

    public CategoryDto (Category category){
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.childCategories = category.getChildCategories().stream().map(c -> new CategoryDto(c)).collect(Collectors.toSet());
    }

    /*public Category dtoToCategory(){
        return new Category(id, categoryName, parentCategory.dtoToCategory(), childCategories)
    }*/

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

    public Set<CategoryDto> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<CategoryDto> childCategories) {
        this.childCategories = childCategories;
    }
}
