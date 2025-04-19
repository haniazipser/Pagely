package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.ClientType;
import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.entity.Client;
import com.example.Sklep_z_ksiazkami.Model.entity.MyUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
@Getter @Setter
public class ClientDto {

    Integer id;

    String name;

    String NIP;

    LocalDate dateOfBirth;

    String sex;
    ClientType type;

    Status status;

    String accountNumber;
    String accountHolder;
    //LocalDateTime deactivated;

   // Set<UserDto> users;

    public ClientDto (Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.NIP = client.getNIP();
        this.dateOfBirth = client.getDateOfBirth();
        this.sex = client.getSex();
        this.type = client.getType();
        this.status = client.getStatus();
        this.accountNumber = client.getAccountNumber();
        this.accountHolder = client.getAccountHolder();
        //this.users = client.getUsers().stream().map(u -> new UserDto(u)).collect(Collectors.toSet());
    }

}
