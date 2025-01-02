package com.example.Sklep_z_ksiazkami.Repozytorium;

public class ObjectNotExistException extends RuntimeException {
    public ObjectNotExistException(Integer id, String objectName){
        super(String.format("Object %s with id %d Not exists",objectName,id));
    }
}
