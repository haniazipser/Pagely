package com.example.Sklep_z_ksiazkami.serwisy;

import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.entity.Client;
import com.example.Sklep_z_ksiazkami.Repozytorium.ClientRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientAppService {
    private final ClientRepo repo;

    public ClientAppService(ClientRepo repo) {
        this.repo = repo;
    }

    public void updateStatus (Integer id, Status status){
        Client client = repo.getById(id);
        System.out.println(client);
        client.setStatus(status);
        System.out.println(client);
        repo.save(client);
    }


    public List<Client> getAllClients() {
        return repo.findAll();
    }

    public Client getClientByUser(String login) {
        return repo.findByUsersLogin(login);
    }
}
