package ru.gb.spring.security.sem7.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("ui/**").authenticated() //всем авторизованным
                        .requestMatchers("issue/**").hasAuthority("admin") // только админам
                        .requestMatchers("reader").hasAnyAuthority("admin", "user")
                        .requestMatchers("book").permitAll() //всем
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }


}
