package com.example.Sklep_z_ksiazkami.config;

import com.example.Sklep_z_ksiazkami.Model.entity.MyUser;
import com.example.Sklep_z_ksiazkami.Repozytorium.UserRepo;
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

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepo.findByLogin(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getLogin(),user.getPassword(),authorityList);
    }
}
