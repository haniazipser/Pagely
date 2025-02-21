package com.example.Sklep_z_ksiazkami.Repozytorium;

import com.example.Sklep_z_ksiazkami.Model.entity.ISBN;
import com.example.Sklep_z_ksiazkami.Model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ISBNRepo extends JpaRepository<ISBN, String> {

}
