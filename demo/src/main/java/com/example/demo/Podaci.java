package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Podaci {

    private KorisnikRepozitorij korisnikRepozitorij;
    private UlogaRepozitorij ulogaRepozitorij;
    private PrivilegijaRepozitorij privilegijaRepozitorij;
    private OdsjekRepozitorij odsjekRepozitorij;

    @Autowired
    public Podaci(KorisnikRepozitorij korisnikRepozitorij, UlogaRepozitorij ulogaRepozitorij, PrivilegijaRepozitorij privilegijaRepozitorij, OdsjekRepozitorij odsjekRepozitorij) {
        this.korisnikRepozitorij = korisnikRepozitorij;
        this.ulogaRepozitorij = ulogaRepozitorij;
        this.privilegijaRepozitorij = privilegijaRepozitorij;
        this.odsjekRepozitorij = odsjekRepozitorij;
    }

    @EventListener
    public void dodaj (ApplicationReadyEvent event){
        dodajUloge();
        dodajPrivilegije();
        dodajOdsjek();
    }

    private void dodajUloge() {
        Uloga uloga1 = new Uloga();
        uloga1.setNazivUloge(ImenaUloga.ADMIN);
        if(!ulogaRepozitorij.existsBynazivUloge(ImenaUloga.ADMIN)) ulogaRepozitorij.save(uloga1);

        Uloga uloga2 = new Uloga();
        uloga2.setNazivUloge(ImenaUloga.STUDENT);
        if(!ulogaRepozitorij.existsBynazivUloge(ImenaUloga.STUDENT)) ulogaRepozitorij.save(uloga2);

        Uloga uloga3 = new Uloga();
        uloga3.setNazivUloge(ImenaUloga.PROFESOR);
        if(!ulogaRepozitorij.existsBynazivUloge(ImenaUloga.PROFESOR)) ulogaRepozitorij.save(uloga3);

        Uloga uloga4 = new Uloga();
        uloga4.setNazivUloge(ImenaUloga.ASISTENT);
        if(!ulogaRepozitorij.existsBynazivUloge(ImenaUloga.ASISTENT)) ulogaRepozitorij.save(uloga4);

        Uloga uloga5 = new Uloga();
        uloga5.setNazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        if(!ulogaRepozitorij.existsBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA)) ulogaRepozitorij.save(uloga5);
    }


    private void dodajPrivilegije() {
        List<Uloga> uloga = new ArrayList<>();
        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        Uloga uloga2 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        Uloga uloga3 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        Uloga uloga4 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        Uloga uloga5 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);

        uloga.add(uloga1);
        Privilegija privilegija1 = new Privilegija();
        privilegija1.setNazivPrivilegije("izmjena-bodova-zadace");
        privilegija1.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija1.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija1);
        }
        uloga.clear();

        uloga.add(uloga3);
        Privilegija privilegija_edit_zadace = new Privilegija();
        privilegija_edit_zadace.setNazivPrivilegije("editovanje-kreirane-zadace");
        privilegija_edit_zadace.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_edit_zadace.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_edit_zadace);
        }
        
        Privilegija privilegija_registrovanje_casa = new Privilegija();
        privilegija_registrovanje_casa.setNazivPrivilegije("registrovanje-casa");
        privilegija_registrovanje_casa.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_registrovanje_casa.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_registrovanje_casa);
        }
        
        uloga.clear();


    }


    private void dodajOdsjek() {
        Odsjek ri = new Odsjek();
        ri.setNazivOdsjeka("RI");
        if(!odsjekRepozitorij.existsBynazivOdsjeka("RI")) odsjekRepozitorij.save(ri);

        Odsjek tk = new Odsjek();
        tk.setNazivOdsjeka("TK");
        if(!odsjekRepozitorij.existsBynazivOdsjeka("TK")) odsjekRepozitorij.save(tk);

        Odsjek aie = new Odsjek();
        aie.setNazivOdsjeka("AIE");
        if(!odsjekRepozitorij.existsBynazivOdsjeka("AIE")) odsjekRepozitorij.save(aie);

        Odsjek ee = new Odsjek();
        ee.setNazivOdsjeka("EE");
        if(!odsjekRepozitorij.existsBynazivOdsjeka("EE")) odsjekRepozitorij.save(ee);
    }

}
