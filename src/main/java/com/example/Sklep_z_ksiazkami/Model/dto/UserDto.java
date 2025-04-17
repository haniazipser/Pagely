package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.UserType;
import com.example.Sklep_z_ksiazkami.Model.entity.MyUser;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    Integer Id;
    String login;
    String password;
    ClientDto client;
    UserType type;
    Status status;
    String role;

    public UserDto(MyUser user){
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.client = new ClientDto(user.getClient());
        this.type = user.getType();
        this.status = user.getStatus();
    }
}
