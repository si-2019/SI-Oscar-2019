package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = "/pretragaId/imaPrivilegiju/{idKorisnika}/{privilegija}", method = RequestMethod.GET)
    public boolean korisnikImaPrivilegiju(@PathVariable Long idKorisnika, @PathVariable String privilegija) {
        if (korisnikRepozitorij.findById(idKorisnika).equals(Optional.empty())) {
            return false;
        }
        return korisnikRepozitorij.findById(idKorisnika).get().imaPrivilegiju(privilegija.toLowerCase());
    }

    @RequestMapping(value = "/pretragaUsername/imaPrivilegiju/{username}/{privilegija}", method = RequestMethod.GET)
    public boolean korisnikImaPrivilegijuUsername(@PathVariable String username, @PathVariable String privilegija) {
        if (korisnikRepozitorij.findByusername(username.toLowerCase()) == null) {
            return false;
        }
        return korisnikRepozitorij.findByusername(username.toLowerCase()).imaPrivilegiju(privilegija.toLowerCase());
    }

    @RequestMapping(value = "/pretragaId/imaUlogu/{idKorisnika}/{uloga}", method = RequestMethod.GET)
    public boolean korisnikImaUlogu(@PathVariable Long idKorisnika, @PathVariable String uloga) {
        if (korisnikRepozitorij.findById(idKorisnika).equals(Optional.empty())) {
            return false;
        }
        return korisnikRepozitorij.findById(idKorisnika).get().imaUlogu(uloga);
    }

    @RequestMapping(value = "/pretragaUsername/imaUlogu/{username}/{uloga}", method = RequestMethod.GET)
    public boolean korisnikImaUloguUsername(@PathVariable String username, @PathVariable String uloga) {
        if (korisnikRepozitorij.findByusername(username.toLowerCase()) == null) {
            return false;
        }
        return korisnikRepozitorij.findByusername(username.toLowerCase()).imaUlogu(uloga);
    }

    @RequestMapping (value = "/pretragaId/{idKorisnika}/dajUlogu", method = RequestMethod.GET)
    public String vratiUlogu(@PathVariable Long idKorisnika) {
        if (korisnikRepozitorij.findById(idKorisnika).equals(Optional.empty())) {
            return null;
        }
        return korisnikRepozitorij.findById(idKorisnika).get().getUloga_id().getNazivUloge().toString();
    }

    @RequestMapping (value = "/pretragaUsername/{username}/dajUlogu", method = RequestMethod.GET)
    public String vratiUloguUsername(@PathVariable String username) {
        if(korisnikRepozitorij.findByusername(username.toLowerCase()) == null) {
            return null;
        }
        return korisnikRepozitorij.findByusername(username.toLowerCase()).getUloga_id().getNazivUloge().toString();
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

    @RequestMapping(value = "/pretragaPrivilegijeId/{idKorisnika}/{idPrivilegije}", method = RequestMethod.GET)
    public boolean korisnikImaPrivilegiju(@PathVariable Long idKorisnika, @PathVariable Long idPrivilegije) {
        if (korisnikRepozitorij.findById(idKorisnika).equals(Optional.empty()) || !privilegijaRepozitorij.findById(idPrivilegije).isPresent()) {
            return false;
        }
        return korisnikRepozitorij.findById(idKorisnika).get().imaPrivilegiju(privilegijaRepozitorij.findById(idPrivilegije).get().getNazivPrivilegije());
    }

    @RequestMapping(value = "/ pretragaUlogeId/{idKorisnika}/{idUloge}", method = RequestMethod.GET)
    public boolean korisnikImaUlogu(@PathVariable Long idKorisnika, @PathVariable Long idUloge) {
        if (korisnikRepozitorij.findById(idKorisnika).equals(Optional.empty()) || !ulogaRepozitorij.findById(idUloge).isPresent()) {
            return false;
        }
        return korisnikRepozitorij.findById(idKorisnika).get().imaUlogu(ulogaRepozitorij.findById(idUloge).get().getNazivUloge().toString());
    }
}