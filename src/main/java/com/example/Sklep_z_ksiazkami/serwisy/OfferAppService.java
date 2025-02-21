package com.example.Sklep_z_ksiazkami.serwisy;

import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.entity.Client;
import com.example.Sklep_z_ksiazkami.Model.entity.Offer;
import com.example.Sklep_z_ksiazkami.Model.entity.PriceProposal;
import com.example.Sklep_z_ksiazkami.Repozytorium.ClientRepo;
import com.example.Sklep_z_ksiazkami.Repozytorium.OfferRepo;
import com.example.Sklep_z_ksiazkami.Repozytorium.PriceProposalRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfferAppService {

    Logger logger = LoggerFactory.getLogger(OfferAppService.class);
    private final OfferRepo repo;
    private final ClientRepo clientRepo;

    private final PriceProposalRepo priceProposalRepo;

    public OfferAppService(OfferRepo repo, ClientRepo clientRepo, PriceProposalRepo priceProposalRepo) {
        this.repo = repo;
        this.clientRepo = clientRepo;
        this.priceProposalRepo = priceProposalRepo;
    }

    public void updateStatus (int id, Status status){
        Offer offer = repo.getById(id);
        offer.setStatus(status);
        repo.save(offer);
    }

   /* public void connectIsbn(String title, String author, ISBN isbn){
        Set<Offer> offers = repo.findByTitleAndAuthor(title, author);
        for (Offer o :offers){
            o.setIsbn(isbn);
        }
        repo.saveAll(offers);
    }*/

    public List<Offer> getClientsOffers(int clientId) {
        logger.info("klient po ktorym szukamy:{}",clientId);
        List<Offer> offers = repo.findByClientId(clientId);
        //logger.info("oferty:{}",offers);
        return offers;
    }

    public List<Offer> getAllOffers() {
        logger.info("Repo instance: {}", repo);
        return repo.findAll();
    }

    public Offer getOfferById(int id) {
        return repo.getById(id);
    }

    public void createPriceProposal(int offerId, int clientId, Float price){
        logger.info("w serwisie");
        Offer offer = repo.getById(offerId);
        Client client = clientRepo.getById(clientId);
        PriceProposal proposal = new PriceProposal(offer, client, price);
        logger.info("id{},price{}",proposal.getId(), proposal.getPrice());
        priceProposalRepo.save(proposal);
    }

}
