package com.example.demo;

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
        Uloga profesor = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        Uloga admin = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        Uloga asistent = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        Uloga studentska_sluzba = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);


        uloga.add(student);

        Privilegija prijava_odjava_sa_ispita = new Privilegija();
        prijava_odjava_sa_ispita.setNazivPrivilegije("prijava-odjava-sa-ispita");
        editovanje_kreirane_zadace.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("prijava-odjava-sa-ispita")) privilegijaRepozitorij.save(editovanje_kreirane_zadace);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("prijava-odjava-sa-ispita").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(student);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("prijava-odjava-sa-ispita").getId());
                prijava_odjava_sa_ispita.setUloge(noveUloge);
                privilegijaRepozitorij.save(prijava_odjava_sa_ispita);
            }
        }

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
