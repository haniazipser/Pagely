package com.example.Sklep_z_ksiazkami.Repozytorium;

import com.example.Sklep_z_ksiazkami.Model.entity.Category;
import com.example.Sklep_z_ksiazkami.Model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo  extends JpaRepository<Category, Integer> {
    List<Category> findByParentCategoryId(int i);
}
