package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {
    @CrossOrigin
    @GetMapping("/")
    public String defaultPoruka() {
        return "Dobrodošli na početnu stranicu modula za Autorizaciju! Za pregled dostupnih servisa, unesite https://si2019oscar.herokuapp.com/swagger-ui.html";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
