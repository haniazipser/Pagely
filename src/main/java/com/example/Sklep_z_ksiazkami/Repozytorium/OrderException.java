package com.example.Sklep_z_ksiazkami.Repozytorium;

public class OrderException  extends RuntimeException {
    public OrderException(String message){
        super(message);
    }
}