package com.example.Sklep_z_ksiazkami.config;

import com.example.Sklep_z_ksiazkami.Model.entity.MyUser;
import com.example.Sklep_z_ksiazkami.Repozytorium.UserRepo;
import com.example.Sklep_z_ksiazkami.controler.user.ClientController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("trying to load:" + username);
        MyUser user = userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        logger.info("loaded " + user);
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getEmail(),user.getPassword(),authorityList);
    }
}
