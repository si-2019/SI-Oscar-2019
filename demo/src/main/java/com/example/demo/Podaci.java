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

        uloga.add(admin);
        // PRIVILEGIJE ZA ADMINA

        Privilegija privilegija_editovanje_korisnika = new Privilegija();
        privilegija_editovanje_korisnika.setNazivPrivilegije("editovanje-korisnika");
        privilegija_editovanje_korisnika.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_editovanje_korisnika.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_editovanje_korisnika);
        }
        uloga.clear();

        uloga.add(asistent);
        // PRIVILEGIJE ZA ASISTENTA

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

        Privilegija privilegija_editovanje_obavjestenja = new Privilegija();
        privilegija_editovanje_obavjestenja.setNazivPrivilegije("editovanje-obavjestenja");
        privilegija_editovanje_obavjestenja.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_editovanje_obavjestenja.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_editovanje_obavjestenja);
        }

        Privilegija privilegija_editovanje_teme_forum = new Privilegija();
        privilegija_editovanje_teme_forum.setNazivPrivilegije("editovanje-teme-na-forumu");
        privilegija_editovanje_teme_forum.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_editovanje_teme_forum.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_editovanje_teme_forum);
        }
        else {

        }
        uloga.clear();

        uloga.add(profesor);
        // PRIVILEGIJE ZA PROFESORA

        Privilegija privilegija_izmjena_bodova_zadace = new Privilegija();
        privilegija_izmjena_bodova_zadace.setNazivPrivilegije("izmjena-bodova-za-zadace");
        privilegija_izmjena_bodova_zadace.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_izmjena_bodova_zadace.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_izmjena_bodova_zadace);
        }

        Privilegija privilegija_brisanje_kreiranog_casa = new Privilegija();
        privilegija_brisanje_kreiranog_casa.setNazivPrivilegije("brisanje-kreiranog-casa");
        privilegija_brisanje_kreiranog_casa.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_brisanje_kreiranog_casa.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_brisanje_kreiranog_casa);
        }

        Privilegija privilegija_dodjela_bodova_zadace = new Privilegija();
        privilegija_dodjela_bodova_zadace.setNazivPrivilegije("dodjela-bodova-za-zadace");
        privilegija_dodjela_bodova_zadace.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_dodjela_bodova_zadace.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_dodjela_bodova_zadace);
        }

        Privilegija privilegija_kreiranje_kviza = new Privilegija();
        privilegija_kreiranje_kviza.setNazivPrivilegije("kreiranje-kviza");
        privilegija_kreiranje_kviza.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija_kreiranje_kviza.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija_kreiranje_kviza);
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
