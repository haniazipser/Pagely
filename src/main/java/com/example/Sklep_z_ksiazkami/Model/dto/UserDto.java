package com.example.Sklep_z_ksiazkami.Model.dto;

import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.UserType;
import com.example.Sklep_z_ksiazkami.Model.entity.User;
import jakarta.persistence.Id;

public class UserDto {
    String login;
    String password;
    ClientDto client;
    UserType type;
    Status status;

    public UserDto(User user){
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.client = new ClientDto(user.getClient());
        this.type = user.getType();
        this.status = user.getStatus();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientDto getClientId() {
        return client;
    }

    public void setClientId(ClientDto client) {
        this.client = client;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
