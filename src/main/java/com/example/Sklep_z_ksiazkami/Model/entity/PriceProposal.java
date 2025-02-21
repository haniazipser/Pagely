package com.example.Sklep_z_ksiazkami.Model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//Propozycje_cen (Id int IDENTITY NOT NULL, Id_klienta int NULL, Id_oferty int NULL, Cena MONEY NOT NULL,
// Status varchar(255) NOT NULL, Data DATETIME NOT NULL,
@Entity
@Table(name = "Propozycje_cen")
public class PriceProposal {
    public enum ProposalStatus {
        ACCEPTED,
        REJECTED,
        WAITING
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name= "Id_klienta")
    Client client;
    @ManyToOne
    @JoinColumn(name= "Id_oferty")
    Offer offer;
    @Column(name = "Cena")
    Float price;
    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    ProposalStatus status;
    @Column(name = "Data")
    LocalDateTime date;

    public PriceProposal(){};

    public PriceProposal(Offer offer, Client client, Float price){
        this.client = client;
        this.offer = offer;
        this.price = price;
        this.date = LocalDateTime.now();
        this.status = ProposalStatus.WAITING;
    }

    public PriceProposal(Integer id, Client client, Offer offer, Float price, ProposalStatus status, LocalDateTime date) {
        this.id = id;
        this.client = client;
        this.offer = offer;
        this.price = price;
        this.status = status;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
