package com.example.Sklep_z_ksiazkami.Model;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;

//CREATE TABLE Klienci (Id int IDENTITY NOT NULL, Nazwa varchar(255) NULL,
// NIP varchar(255) NULL, Data_urodzenia date NULL, Plec varchar(255) NULL,
// Rodzaj_klienta varchar(255) NOT NULL, Status varchar(255) NOT NULL,
// Numer_rachunku varchar(255) NULL, Nazwa_konta varchar(255) NULL UNIQUE, PRIMARY KEY (Id));
public record Klient(
        Integer id,
        @NotBlank
        @NotEmpty
        String nazwa,
        @Pattern(regexp = "(^[0-9]{3}\\-[0-9]{3}\\-[0-9]{2}\\-[0-9]{2}$)|(^[0-9]{10}$)|(^$)", message="Błędny NIP")
        String NIP,
        LocalDate dataUrodzenia,
        String plec,
        @NotBlank
        @NotEmpty
        Rodzaj rodzajKlienta,
        Status statusKlienta,
        @Pattern(regexp = "(^PL[0-9]{26}$)|(^[0-9]{26}$)|(^$)", message="Błędny numer rachunku")
        String numerRachunku,

        String nazwaKonta

) {
    public Klient(){
        this(null,null,null,null,null,null,null,null,null);
    }
}
