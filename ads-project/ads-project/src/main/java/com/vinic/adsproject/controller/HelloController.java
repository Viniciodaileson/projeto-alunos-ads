package com.vinic.adsproject.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") // Liberado para acessos externos com segurança
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String dizerOla() {
        return "Olá, Vinic! Seu primeiro endpoint Spring Boot está funcionando!";
    }
}
