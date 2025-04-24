package com.example.Sklep_z_ksiazkami.Model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//Propozycje_cen (Id int IDENTITY NOT NULL, Id_klienta int NULL, Id_oferty int NULL, Cena MONEY NOT NULL,
// Status varchar(255) NOT NULL, Data DATETIME NOT NULL,
@Entity
@Table(name = "Propozycje_cen")
@Getter @Setter
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "value", column = @Column(name = "Cena")),
    })
    Money price;
    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    ProposalStatus status;
    @Column(name = "Data")
    LocalDateTime date;

    public PriceProposal(){}

    public PriceProposal(Offer offer, Client client, Money price){
        this.client = client;
        this.offer = offer;
        this.price = price;
        this.date = LocalDateTime.now();
        this.status = ProposalStatus.WAITING;
    }

    public PriceProposal(Integer id, Client client, Offer offer, Money price, ProposalStatus status, LocalDateTime date) {
        this.id = id;
        this.client = client;
        this.offer = offer;
        this.price = price;
        this.status = status;
        this.date = date;
    }
}
