package com.example.autorizacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DodavanjePodataka {

    private KorisnikRepozitorij korisnikRepozitorij;
    private UlogaRepozitorij ulogaRepozitorij;
    private PrivilegijaRepozitorij privilegijaRepozitorij;
    private OdsjekRepozitorij odsjekRepozitorij;

    @Autowired
    public DodavanjePodataka(KorisnikRepozitorij korisnikRepozitorij, UlogaRepozitorij ulogaRepozitorij, PrivilegijaRepozitorij privilegijaRepozitorij, OdsjekRepozitorij odsjekRepozitorij) {
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

        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        uloga.add(uloga1);


        Privilegija privilegija112 = new Privilegija();
        privilegija112.setNazivPrivilegije("registrovanje-nove-zadace");
        privilegija112.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("registrovanje-nove-zadace")) privilegijaRepozitorij.save(privilegija112);

        Privilegija privilegija45 = new Privilegija();
        privilegija45.setNazivPrivilegije("kreiranje-teme-na-forumu");
        privilegija45.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("kreiranje-teme-na-forumu")) privilegijaRepozitorij.save(privilegija45);




        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        uloga.add(uloga1);

        Privilegija privilegija19 = new Privilegija();
        privilegija19.setNazivPrivilegije("registrovanje-nove-zadace");
        privilegija19.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("registrovanje-nove-zadace")) privilegijaRepozitorij.save(privilegija19);


        uloga.clear();
        Uloga uloga2 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        uloga.add(uloga2);

        Privilegija privilegija222 = new Privilegija();
        privilegija222.setNazivPrivilegije("kreiranje-privilegija");
        privilegija222.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("kreiranje-privilegija")) privilegijaRepozitorij.save(privilegija222);

        uloga.clear();
        Uloga uloga3 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        uloga.add(uloga3);

        Privilegija privilegija3 = new Privilegija();
        privilegija3.setNazivPrivilegije("postavljanje-obavjestenja");
        privilegija3.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("postavljanje-obavjestenja")) privilegijaRepozitorij.save(privilegija3);




        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        uloga.add(uloga1);

        Privilegija privilegija27 = new Privilegija();
        privilegija27.setNazivPrivilegije("brisanje-kreiranog-casa");
        privilegija27.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("brisanje-kreiranog-casa")) privilegijaRepozitorij.save(privilegija27);

        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        uloga.add(uloga1);


        Privilegija privilegija23 = new Privilegija();
        privilegija23.setNazivPrivilegije("kreiranje-teme-na-forumu");
        privilegija23.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("kreiranje-teme-na-forumu")) privilegijaRepozitorij.save(privilegija23);

        Privilegija privilegija22 = new Privilegija();

        privilegija22.setNazivPrivilegije("kreiranje-termina-ispita");
        privilegija22.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("kreiranje-termina-ispita")) privilegijaRepozitorij.save(privilegija22);

        privilegija2.setNazivPrivilegije("izmjena-bodova-za-ispite");
        privilegija2.setUloge(uloga);
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("izmjena-bodova-za-ispite")) privilegijaRepozitorij.save(privilegija2);



		


    }


    private void dodajOdsjek() {
        Odsjek odsjek1 = new Odsjek();
        odsjek1.setNazivOdsjeka("RI");
        if(!odsjekRepozitorij.existsBynazivOdsjeka("RI")) odsjekRepozitorij.save(odsjek1);

        Odsjek odsjek2 = new Odsjek();
        odsjek2.setNazivOdsjeka("TK");
        if(!odsjekRepozitorij.existsBynazivOdsjeka("TK")) odsjekRepozitorij.save(odsjek2);
    }

}
