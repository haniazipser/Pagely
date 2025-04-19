package com.example.Sklep_z_ksiazkami.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getlogin(){
        return "login";
    }

}
