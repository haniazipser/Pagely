package com.example.Sklep_z_ksiazkami.Repozytorium;

import com.example.Sklep_z_ksiazkami.Model.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  UserRepo extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findByLogin(String email);
}
