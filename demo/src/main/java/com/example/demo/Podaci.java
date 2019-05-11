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
        Uloga admin = new Uloga();
        admin.setNazivUloge(ImenaUloga.ADMIN);
        if(!ulogaRepozitorij.existsBynazivUloge(ImenaUloga.ADMIN)) ulogaRepozitorij.save(admin);

        Uloga student = new Uloga();
        student.setNazivUloge(ImenaUloga.STUDENT);
        if(!ulogaRepozitorij.existsBynazivUloge(ImenaUloga.STUDENT)) ulogaRepozitorij.save(student);

        Uloga profesor = new Uloga();
        profesor.setNazivUloge(ImenaUloga.PROFESOR);
        if(!ulogaRepozitorij.existsBynazivUloge(ImenaUloga.PROFESOR)) ulogaRepozitorij.save(profesor);

        Uloga asistent = new Uloga();
        asistent.setNazivUloge(ImenaUloga.ASISTENT);
        if(!ulogaRepozitorij.existsBynazivUloge(ImenaUloga.ASISTENT)) ulogaRepozitorij.save(asistent);

        Uloga studentska_sluzba = new Uloga();
        studentska_sluzba.setNazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        if(!ulogaRepozitorij.existsBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA)) ulogaRepozitorij.save(studentska_sluzba);
    }

    private void dodajPrivilegije() {
        List<Uloga> uloga = new ArrayList<>();
        Uloga profesor = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        Uloga admin = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        Uloga asistent = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        Uloga studentska_sluzba = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);

        uloga.add(asistent);
        Privilegija privilegija_registrovanje_casa = new Privilegija();
        privilegija_registrovanje_casa.setNazivPrivilegije("registrovanje-casa");
        privilegija_registrovanje_casa.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_registrovanje_casa.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_registrovanje_casa);
        }

        Privilegija privilegija_editovanje_zadace = new Privilegija();
        privilegija_editovanje_zadace.setNazivPrivilegije("editovanje-kreirane-zadace");
        privilegija_editovanje_zadace.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_editovanje_zadace.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_editovanje_zadace);
        }
        uloga.clear();

        uloga.add(profesor);
        Privilegija privilegija_izmjena_bodova_zadace = new Privilegija();
        privilegija_izmjena_bodova_zadace.setNazivPrivilegije("izmjena-bodova-za-zadace");
        privilegija_izmjena_bodova_zadace.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_izmjena_bodova_zadace.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_izmjena_bodova_zadace);
        }
        uloga.clear();
    }

    private void dodajOdsjek() {
        Odsjek ri = new Odsjek();
        ri.setNazivOdsjeka("Racunarstvo i informatika");
        if(!odsjekRepozitorij.existsBynazivOdsjeka(ri.getNazivOdsjeka())) odsjekRepozitorij.save(ri);

        Odsjek tk = new Odsjek();
        tk.setNazivOdsjeka("Telekomunikacije");
        if(!odsjekRepozitorij.existsBynazivOdsjeka(tk.getNazivOdsjeka())) odsjekRepozitorij.save(tk);

        Odsjek aie = new Odsjek();
        aie.setNazivOdsjeka("Automatika i elektronika");
        if(!odsjekRepozitorij.existsBynazivOdsjeka(aie.getNazivOdsjeka())) odsjekRepozitorij.save(aie);

        Odsjek ee = new Odsjek();
        ee.setNazivOdsjeka("Elektroenergetika");
        if(!odsjekRepozitorij.existsBynazivOdsjeka(ee.getNazivOdsjeka())) odsjekRepozitorij.save(ee);
    }

}