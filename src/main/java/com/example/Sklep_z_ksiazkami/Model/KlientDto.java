package com.example.Sklep_z_ksiazkami.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.pl.NIP;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

public class KlientDto {
    private Integer id;
    @NotBlank
    @NotEmpty
    private String nazwa;
    @Pattern(regexp = "(^[0-9]{3}\\-[0-9]{3}\\-[0-9]{2}\\-[0-9]{2}$)|(^[0-9]{10}$)|(^$)", message="Błędny NIP")
    private String NIP;
    private LocalDate dataUrodzenia;
    private String plec;
    @NotBlank
    @NotEmpty
    private Rodzaj rodzajKlienta;
    private Status statusKlienta;
    @Pattern(regexp = "(^PL[0-9]{26}$)|(^[0-9]{26}$)|(^$)", message="Błędny numer rachunku")
    private String numerRachunku;
    @Size(min=1,max=20)
    @NotBlank
    private String nazwaKonta;

    public KlientDto(){

    }
    public KlientDto(Klient klient){
        this.id = klient.id();
        this.nazwa = klient.nazwa();
        this.dataUrodzenia = klient.dataUrodzenia();
        this.plec = klient.plec();
        this.NIP = klient.NIP();
        this.rodzajKlienta = klient.rodzajKlienta();
        this.statusKlienta = klient.statusKlienta();
        this.numerRachunku = klient.numerRachunku();
        this.nazwaKonta = klient.nazwaKonta();
    }

    public static KlientDto KlientToKlientDto(Klient klient){
        return new KlientDto(klient);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public Rodzaj getRodzajKlienta() {
        return rodzajKlienta;
    }

    public void setRodzajKlienta(Rodzaj rodzajKlienta) {
        this.rodzajKlienta = rodzajKlienta;
    }

    public Status getStatusKlienta() {
        return statusKlienta;
    }

    public void setStatusKlienta(Status statusKlienta) {
        this.statusKlienta = statusKlienta;
    }

    public String getNumerRachunku() {
        return numerRachunku;
    }

    public void setNumerRachunku(String numerRachunku) {
        this.numerRachunku = numerRachunku;
    }

    public String getNazwaKonta() {
        return nazwaKonta;
    }

    public void setNazwaKonta(String nazwaKonta) {
        this.nazwaKonta = nazwaKonta;
    }

    public Klient getRecord(){
        return new Klient(id,nazwa,NIP,dataUrodzenia,plec,rodzajKlienta,statusKlienta,numerRachunku,nazwaKonta);
    }
}
