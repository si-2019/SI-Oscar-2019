package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/korisnici")
public class KorisnikKontroler {

    private KorisnikRepozitorij korisnikRepozitorij;

    @Autowired
    public KorisnikKontroler(KorisnikRepozitorij korisnikRepozitorij) {
        this.korisnikRepozitorij = korisnikRepozitorij;
    }
}


