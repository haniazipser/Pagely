package com.example.Sklep_z_ksiazkami.config;

import com.example.Sklep_z_ksiazkami.serwisy.PurchaseAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()).csrf(c -> c.disable())
                .authorizeHttpRequests((a) ->
                        a.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // <-- to jest KLUCZOWE!
                        .requestMatchers("/public/**").permitAll()
                                .anyRequest().authenticated());
        http.formLogin(form -> form.defaultSuccessUrl("http://localhost:3000/", true).loginPage("/login").permitAll());

        http.httpBasic(withDefaults());
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("http://localhost:3000/")
                .invalidateHttpSession(true)
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
