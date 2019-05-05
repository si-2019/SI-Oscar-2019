package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/korisnici")
public class KorisnikKontroler {

    private KorisnikRepozitorij korisnikRepozitorij;

    @Autowired
    public KorisnikKontroler(KorisnikRepozitorij korisnikRepozitorij) {
        this.korisnikRepozitorij = korisnikRepozitorij;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public boolean getAll() {
        Korisnik k = korisnikRepozitorij.findById(Long.valueOf(1)).get();
        return k.imaPrivilegiju("editovanje-kreirane-zadace");
    }
}