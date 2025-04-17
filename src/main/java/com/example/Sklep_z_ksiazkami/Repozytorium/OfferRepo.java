package com.example.Sklep_z_ksiazkami.Repozytorium;

import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface  OfferRepo extends JpaRepository<Offer, Integer> {
    Set<Offer> findByTitleAndAuthor(String title, String Author);

   // @Query("SELECT O FROM Offer O WHERE O.client.id = ?1")
    List<Offer> findByClientId(int clientId);

    List<Offer> findByStatus(Status status);

    List<Offer> findByIsbn(String isbn);
}
