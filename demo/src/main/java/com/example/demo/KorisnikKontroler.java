package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping(value = "/pretragaId/imaPrivilegiju/{idKorisnika}/{privilegija}", method = RequestMethod.GET)
    public boolean korisnikImaPrivilegiju(@PathVariable Long idKorisnika, @PathVariable String privilegija) {
        if (korisnikRepozitorij.findById(idKorisnika).equals(Optional.empty())) {


    @RequestMapping(value = "/pretragaUsername/imaUlogu/{username}/{uloga}", method = RequestMethod.GET)
    public boolean korisnikImaUloguUsername(@PathVariable String username, @PathVariable String uloga) {
        if (korisnikRepozitorij.findByusername(username) == null) {

            return false;
        }
        return korisnikRepozitorij.findById(idKorisnika).get().imaPrivilegiju(privilegija);
    }


    @RequestMapping(value = "/pretragaId/{idKorisnika}/dajPrivilegije", method = RequestMethod.GET)
    public List<String> privilegijeKorisnika (@PathVariable Long idKorisnika) {
        List<Privilegija> privilegije;
        List<String> povratna = new ArrayList<String>();
        if (korisnikRepozitorij.findById(idKorisnika).isPresent()) {
            privilegije = korisnikRepozitorij.findById(idKorisnika).get().getUloga_id().getPrivilegije();
            for (Privilegija p : privilegije) {
                povratna.add(p.getNazivPrivilegije());
            }
            return povratna;
        }
        return null;
    }

    @RequestMapping(value = "/pretragaUsername/{username}/dajPrivilegije", method = RequestMethod.GET)
    public List<String> privilegijeKorisnikaUsername(@PathVariable String username) {
        List<Privilegija> privilegije;
        List<String> povratna = new ArrayList<>();
        if (korisnikRepozitorij.findByusername(username.toLowerCase()) != null) {
            privilegije = korisnikRepozitorij.findByusername(username.toLowerCase()).getUloga_id().getPrivilegije();
            for (Privilegija p : privilegije) {
                povratna.add(p.getNazivPrivilegije());
            }
            return povratna;
        }
        return null;
    }
}