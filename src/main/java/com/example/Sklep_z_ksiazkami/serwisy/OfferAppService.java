package com.example.Sklep_z_ksiazkami.serwisy;

import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.dto.OfferDto;
import com.example.Sklep_z_ksiazkami.Model.entity.*;
import com.example.Sklep_z_ksiazkami.Repozytorium.ClientRepo;
import com.example.Sklep_z_ksiazkami.Repozytorium.OfferRepo;
import com.example.Sklep_z_ksiazkami.Repozytorium.PriceProposalRepo;
import com.example.Sklep_z_ksiazkami.Repozytorium.ShippingMethodRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfferAppService {

    Logger logger = LoggerFactory.getLogger(OfferAppService.class);
    private final OfferRepo repo;
    private final ClientRepo clientRepo;
    private final ShippingMethodRepo shippingMethodRepo;

    private final PriceProposalRepo priceProposalRepo;

    public OfferAppService(OfferRepo repo, ClientRepo clientRepo, ShippingMethodRepo shippingMethodRepo, PriceProposalRepo priceProposalRepo) {
        this.repo = repo;
        this.clientRepo = clientRepo;
        this.shippingMethodRepo = shippingMethodRepo;
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

    public List<OfferDto> getOffersWithShipping() {
        List<Offer> offers = repo.findByStatus(Status.ACTIVE);
        List<OfferDto> offerDtos = new ArrayList<>();
        for (int i = 0; i < offers.size(); i++){
            Integer id = offers.get(i).getClient().getId();
            List<ShippingMethod> shippingMethods = shippingMethodRepo.findByIdClientId(id);
            offerDtos.add(new OfferDto(offers.get(i), shippingMethods));
        }
        return offerDtos;
    }

    public Offer getOfferById(int id) {
        return repo.getById(id);
    }

    public void createPriceProposal(int offerId, int clientId, Money price){
        logger.info("w serwisie");
        Offer offer = repo.getById(offerId);
        Client client = clientRepo.getById(clientId);
        PriceProposal proposal = new PriceProposal(offer, client, price);
        logger.info("id{},price{}",proposal.getId(), proposal.getPrice());
        priceProposalRepo.save(proposal);
    }

    public List<OfferDto> getOffers() {
        return repo.findAll().stream().map(o -> new OfferDto(o)).collect(Collectors.toList());
    }

    public OfferDto getOfferByIdWithShipping(Integer id) {
        Offer offer = repo.getById(id);
        Integer clientId = offer.getClient().getId();
        List<ShippingMethod> shippingMethods = shippingMethodRepo.findByIdClientId(id);
        OfferDto offerDto = new OfferDto(offer, shippingMethods);
        return offerDto;
    }

    public List<OfferDto> getOffersByIsbn(String isbn) {
        return repo.findByIsbn(isbn).stream().map(o -> new OfferDto(o)).collect(Collectors.toList());
    }
}
