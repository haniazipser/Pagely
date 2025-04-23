package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.ClientType;
import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.UserType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
@Getter @Setter
public class ClientUserDto {

    String name;
    String NIP;
    ClientType clientType;
    Status clientStatus;

    String email;
    String password;
    UserType userType;
    Status userStatus;
    String role;
}