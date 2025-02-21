package com.example.Sklep_z_ksiazkami.serwisy;

import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.entity.User;
import com.example.Sklep_z_ksiazkami.Repozytorium.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAppService {
    private final UserRepo repo;

    public UserAppService(UserRepo repo) {
        this.repo = repo;
    }

    public void updateStatus (String login, Status status){
        User user = repo.getById(login);
        user.setStatus(status);
        repo.save(user);
    }
}
