package com.example.Sklep_z_ksiazkami.Repozytorium;

import com.example.Sklep_z_ksiazkami.Model.Klient;
import com.example.Sklep_z_ksiazkami.Model.Mapper;
import com.example.Sklep_z_ksiazkami.Model.Rodzaj;
import com.example.Sklep_z_ksiazkami.Model.Status;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class Repozytorium {
    private final JdbcTemplate jdbcTemplate;
    private final Mapper mapper;

    public Repozytorium(JdbcTemplate jdbcTemplate, Mapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    public List<Klient> findAll() {
        String sql = "SELECT * FROM Klienci";
        List<Klient> klienci = jdbcTemplate.query(sql, mapper);
        return klienci;
    }

    public Klient findById(int id) {
        String sql = "SELECT * FROM Klienci WHERE Id=?";
        Object[] arg = {id};
        try {
            Klient klient = jdbcTemplate.queryForObject(sql, arg, mapper);
            return klient;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Klient>  findByNazwaKonta(String nazwaKonta) {
        String sql = "SELECT * FROM Klienci WHERE  Nazwa like '"+nazwaKonta+"%'";
        //Object[] arg = {nazwaKonta};
        try {
            List<Klient> klienci = jdbcTemplate.query(sql, mapper);
            return klienci;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    /*
     Integer id,
        String NIP, LocalDate dataUrodzenia, String plec, Rodzaj rodzajKlienta, Status statusKlienta,
        String numerRachunku,String nazwaKonta
     */
    public void createContent(String nazwa, String NIP, LocalDate dataUrodzenia, String plec, Rodzaj rodzajKlienta, Status statusKlienta,
                              String numerRachunku, String nazwaKonta) {
        String sql = "INSERT INTO Klienci (Nazwa, NIP, Data_urodzenia, Plec, Rodzaj_klienta, Status, Numer_rachunku, Nazwa_konta) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, nazwa, NIP, dataUrodzenia, plec, rodzajKlienta.toString(), statusKlienta.toString(), numerRachunku, nazwaKonta);
    }

    public void updateContent(int id, String nazwa, String NIP, LocalDate dataUrodzenia, String plec, Rodzaj rodzajKlienta, Status statusKlienta,
                              String numerRachunku, String nazwaKonta) {
        String sql = "UPDATE Klienci SET Nazwa = ?, NIP = ?, Data_urodzenia = ?, Plec = ?, Rodzaj_klienta = ?, Status = ?, Numer_rachunku = ?, Nazwa_konta = ? WHERE Id = ?;";
        int count = jdbcTemplate.update(sql, nazwa, NIP, dataUrodzenia, plec, rodzajKlienta.toString(), statusKlienta.toString(), numerRachunku, nazwaKonta, id);
        if (count==0){
            throw new ObjectNotExistException(id, "Klient") ;
        }
    }

    public void deleteContent(int id) {
        String sql = "DELETE FROM Klienci WHERE Id=?";
        int count=jdbcTemplate.update(sql, id);
        if (count==0){
            throw new ObjectNotExistException(id, "Klient") ;
        }
    }
}