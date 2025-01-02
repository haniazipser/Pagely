package com.example.Sklep_z_ksiazkami.Kontroler;

import com.example.Sklep_z_ksiazkami.Model.Klient;
import com.example.Sklep_z_ksiazkami.Repozytorium.Repozytorium;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/klienci")
public class Kontroler {
    Logger logger = LoggerFactory.getLogger(Kontroler.class);

    private final Repozytorium repozytorium;
    public Kontroler(Repozytorium repozytorium) {
        this.repozytorium = repozytorium;
    }

    @GetMapping("")
    public List<Klient> findAll() {
        return repozytorium.findAll();
    }

    @GetMapping("/{id}")
    public Klient findById(@PathVariable Integer id){
        logger.debug("Get by id:{}",id);
        var klient = repozytorium.findById(id);
        if(isNull(klient)){
            logger.error("Content is null");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Klient not found");
        }
        logger.info("Return Get by id:{} \n {}",id, klient);
        return  klient;
    }

    @PostMapping("")
    public void create (@Valid @RequestBody Klient klient){
        repozytorium.createContent(klient.nazwa(),klient.NIP(), klient.dataUrodzenia(), klient.plec(),
                klient.rodzajKlienta(), klient.statusKlienta(), klient.numerRachunku(), klient.nazwaKonta());
    }

    @PutMapping("/{id}")
    public void update (@PathVariable Integer id, @Valid @RequestBody Klient klient){
        repozytorium.updateContent(id, klient.nazwa(), klient.NIP(), klient.dataUrodzenia(), klient.plec(),
                klient.rodzajKlienta(), klient.statusKlienta(), klient.numerRachunku(), klient.nazwaKonta());
    }

    @DeleteMapping(value = "/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Integer id){
        repozytorium.deleteContent(id);
    }
}
