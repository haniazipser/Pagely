package com.example.Sklep_z_ksiazkami.controler.user;



import com.example.Sklep_z_ksiazkami.Model.dto.ClientUserDto;
import com.example.Sklep_z_ksiazkami.Model.dto.UserDto;
import com.example.Sklep_z_ksiazkami.Model.entity.Client;
import com.example.Sklep_z_ksiazkami.Model.entity.MyUser;
import com.example.Sklep_z_ksiazkami.Repozytorium.ClientRepo;
import com.example.Sklep_z_ksiazkami.Repozytorium.UserRepo;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepo clientRepo;

    public UserController(UserRepo userRepo, PasswordEncoder passwordEncoder, ClientRepo clientRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.clientRepo = clientRepo;
    }

    @GetMapping("/user")
    public String getLogin (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @PostMapping("/public/register")
    public ResponseEntity<String> registerUser(@RequestBody ClientUserDto userClient){
        try{
            String hashPwd = passwordEncoder.encode(userClient.getPassword());
            Client client = new Client(userClient.getName(), userClient.getNIP(), userClient.getClientType(),userClient.getClientStatus());
            Client savedClient = clientRepo.save(client);
            MyUser user = new MyUser(userClient.getEmail(), hashPwd, savedClient, userClient.getUserType(), userClient.getUserStatus(), userClient.getName(), null, userClient.getRole());
            MyUser savedUser = userRepo.save(user);
            if (savedUser.getId() > 0){
                return ResponseEntity.status(HttpStatus.CREATED).body("given user was saved");
            } else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("registration failed");
            }
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("exception occurred");
        }
    }
}