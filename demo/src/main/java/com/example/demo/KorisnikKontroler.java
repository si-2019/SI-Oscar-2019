package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping (value = "/pretragaId/{idKorisnika}/dajUlogu", method = RequestMethod.GET)
	public String vratiUlogu(@PathVariable Long idKorisnika) {
    if (korisnikRepozitorij.findById(idKorisnika).equals(Optional.empty())) {
        return null;
    }
    return korisnikRepozitorij.findById(idKorisnika).get().getUloga_id().getNazivUloge().toString();
	}
}