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
    public DodavanjePodataka(KorisnikRepozitorij korisnikRepozitorij, UlogaRepozitorij ulogaRepozitorij,
            PrivilegijaRepozitorij privilegijaRepozitorij,OdsjekRepozitorij odsjekRepozitorij) {
        this.korisnikRepozitorij = korisnikRepozitorij;
        this.ulogaRepozitorij = ulogaRepozitorij;
        this.privilegijaRepozitorij = privilegijaRepozitorij;
        this.odsjekRepozitorij=odsjekRepozitorij;
    }

    @EventListener
    public void dodaj(ApplicationReadyEvent event) {
        dodajUloge();
        dodajPrivilegije();
        dodajOdsjek();
    }
    private void dodajUloge() {

        Uloga uloga1 = new Uloga();
        uloga1.setNazivUloge(ImenaUloga.ADMIN);
        if (ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN)==null) ulogaRepozitorij.save(uloga1);

        Uloga uloga2 = new Uloga();
        uloga2.setNazivUloge(ImenaUloga.STUDENT);
        if (ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT)==null) ulogaRepozitorij.save(uloga2);

        Uloga uloga3 = new Uloga();
        uloga3.setNazivUloge(ImenaUloga.PROFESOR);
        if (ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR)==null) ulogaRepozitorij.save(uloga3);

        Uloga uloga4 = new Uloga();
        uloga4.setNazivUloge(ImenaUloga.ASISTENT);
        if (ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT)==null) ulogaRepozitorij.save(uloga4);

        Uloga uloga5 = new Uloga();
        uloga5.setNazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        if (ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA)==null) ulogaRepozitorij.save(uloga5);
    }

    private void dodajPrivilegije() {

        List<Uloga> uloga = new ArrayList<>();
        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        uloga.add(uloga1);

        Privilegija privilegija1 = new Privilegija();
        privilegija1.setNazivPrivilegije("kreiranje-zadace");
        privilegija1.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-zadace")==null) privilegijaRepozitorij.save(privilegija1);
        
        uloga = new ArrayList<>();
        Uloga uloga2 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        uloga.add(uloga2);
        Privilegija privilegija2 = new Privilegija();
        privilegija2.setNazivPrivilegije("kreiranje-korisnika");
        privilegija2.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-korisnika")==null) privilegijaRepozitorij.save(privilegija2);


       }
       

     private void dodajOdsjek(){ 

        if((odsjekRepozitorij.findBynazivOdsjeka("RI"))==null){ 
            Odsjek odsjek1=new Odsjek();
            odsjek1.setId((long)(odsjekRepozitorij.count()+1)); 
            odsjek1.setNazivOdsjeka("RI"); odsjekRepozitorij.save(odsjek1); 
        } 
    }
}
