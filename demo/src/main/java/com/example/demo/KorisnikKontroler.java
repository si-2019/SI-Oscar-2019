package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KorisnikKontroler {

    private KorisnikRepozitorij korisnikRepozitorij;
    private PrivilegijaRepozitorij privilegijaRepozitorij;
    private UlogaRepozitorij ulogaRepozitorij;

    @Autowired
    public KorisnikKontroler(KorisnikRepozitorij korisnikRepozitorij, PrivilegijaRepozitorij privilegijaRepozitorij,
            UlogaRepozitorij ulogaRepozitorij) {
        this.korisnikRepozitorij = korisnikRepozitorij;
        this.privilegijaRepozitorij = privilegijaRepozitorij;
        this.ulogaRepozitorij = ulogaRepozitorij;
    }

    @RequestMapping(value = "/pretragaId/imaUlogu/{idKorisnika}/{uloga}", method = RequestMethod.GET)
    public boolean korisnikImaUlogu(@PathVariable Long idKorisnika, @PathVariable String uloga) {
        if (korisnikRepozitorij.findById(idKorisnika).equals(Optional.empty())) {
            return false;
        }
        return korisnikRepozitorij.findById(idKorisnika).get().imaUlogu(uloga);
    }

}