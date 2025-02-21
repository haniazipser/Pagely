package com.example.Sklep_z_ksiazkami.Model;
/*
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

//CREATE TABLE Klienci (Id int IDENTITY NOT NULL, Nazwa varchar(255) NULL,
// NIP varchar(255) NULL, Data_urodzenia date NULL, Plec varchar(255) NULL,
// Rodzaj_klienta varchar(255) NOT NULL, Status varchar(255) NOT NULL,
// Numer_rachunku varchar(255) NULL, Nazwa_konta varchar(255) NULL UNIQUE, PRIMARY KEY (Id));

@Component
public class Mapper implements RowMapper<Klient> {
    @Override
    public Klient mapRow(ResultSet rs, int rowNum) throws SQLException {
        var res = new Klient(rs.getInt("Id"),
                rs.getString("Nazwa"),
                rs.getString("NIP"),
                rs.getObject("Data_urodzenia", LocalDate.class),
                rs.getString("Plec"),
                Rodzaj.valueOf(rs.getString("Rodzaj_klienta")),
                Status.valueOf(rs.getString("Status")),
                rs.getString("Numer_rachunku"),
                rs.getString("Nazwa_konta"));
        return res;
    }
}

*/