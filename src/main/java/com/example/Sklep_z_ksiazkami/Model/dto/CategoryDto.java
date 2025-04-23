package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;
@Getter @Setter
public class CategoryDto {
    int id;
    String categoryName;
    Set<CategoryDto> childCategories;

    public CategoryDto (Category category){
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.childCategories = category.getChildCategories().stream().map(c -> new CategoryDto(c)).collect(Collectors.toSet());
    }
}
