package com.cowsalud.salud.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    
    @GetMapping("/user")
    public String greetings(){
        return "Hola Usuario bonito";
    } 

}
