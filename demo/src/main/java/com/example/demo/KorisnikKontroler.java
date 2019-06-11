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
    public KorisnikKontroler(KorisnikRepozitorij korisnikRepozitorij, PrivilegijaRepozitorij privilegijaRepozitorij, UlogaRepozitorij ulogaRepozitorij) {
        this.korisnikRepozitorij = korisnikRepozitorij;
        this.privilegijaRepozitorij = privilegijaRepozitorij;
        this.ulogaRepozitorij = ulogaRepozitorij;
    }
    @RequestMapping(value = "/pretragaUsername/imaPrivilegiju/{username}/{privilegija}", method = RequestMethod.GET)
    public boolean korisnikImaPrivilegijuUsername(@PathVariable String username, @PathVariable String privilegija) {
        if (korisnikRepozitorij.findByusername(username) == null) {
            return false;
        }
        return korisnikRepozitorij.findByusername(username).imaPrivilegiju(privilegija);
    }
    


}