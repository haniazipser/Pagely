package com.example.Sklep_z_ksiazkami.Repozytorium;

import com.example.Sklep_z_ksiazkami.Model.entity.Order;
import com.example.Sklep_z_ksiazkami.Model.entity.PriceProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  PriceProposalRepo extends JpaRepository<PriceProposal, Integer> {
    @Query("SELECT P FROM PriceProposal P WHERE P.offer.id  = ?2 AND P.client.id = ?1 AND P.status = ACCEPTED ORDER BY P.date DESC")
    List<PriceProposal> findByClientIdAndOfferId(Integer buyer, Integer offerId);
}
