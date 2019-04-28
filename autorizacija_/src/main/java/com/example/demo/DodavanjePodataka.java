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
            PrivilegijaRepozitorij privilegijaRepozitorij, OdsjekRepozitorij odsjekRepozitorij){
        this.korisnikRepozitorij = korisnikRepozitorij;
        this.ulogaRepozitorij = ulogaRepozitorij;
        this.privilegijaRepozitorij = privilegijaRepozitorij;
        this.odsjekRepozitorij = odsjekRepozitorij;

    }

    @EventListener
    public void dodaj(ApplicationReadyEvent event) {
        dodajUloge();
        dodajPrivilegije();
        dodajOdsjek();
    }

    private void dodajOdsjek(){
        Odsjek odsjek1=odsjekRepozitorij.findBynazivOdsjeka("ri");
        if(odsjek1==null){
            odsjekRepozitorij.save(odsjek1);
        }
    }

    private boolean ulogaPostoji(List<Uloga> sveUloge, Uloga uloga) {
        boolean postoji = false;
        for (Uloga u : sveUloge) {
            if (u.getNazivUloge().equals(uloga.getNazivUloge())) {
                postoji = true;
                break;
            }
        }
        return postoji;
    }

    private void dodajUloge() {

        boolean nemaUloga = false;
        List<Uloga> sveUloge = new ArrayList<>();
        try {
            sveUloge = ulogaRepozitorij.findAll();
        } catch (Exception e) {
            nemaUloga = true;
        }

        Uloga uloga1 = new Uloga();
        uloga1.setNazivUloge(ImenaUloga.ADMIN);
        if (nemaUloga) {
            ulogaRepozitorij.save(uloga1);
        } else if (!ulogaPostoji(sveUloge, uloga1)) {
            ulogaRepozitorij.save(uloga1);
        }

        Uloga uloga2 = new Uloga();
        uloga2.setNazivUloge(ImenaUloga.STUDENT);
        if (nemaUloga) {
            ulogaRepozitorij.save(uloga2);
        } else if (!ulogaPostoji(sveUloge, uloga2)) {
            ulogaRepozitorij.save(uloga2);
        }

        Uloga uloga3 = new Uloga();
        uloga3.setNazivUloge(ImenaUloga.PROFESOR);
        if (nemaUloga) {
            ulogaRepozitorij.save(uloga3);
        } else if (!ulogaPostoji(sveUloge, uloga3)) {
            ulogaRepozitorij.save(uloga3);
        }

        Uloga uloga4 = new Uloga();
        uloga4.setNazivUloge(ImenaUloga.ASISTENT);
        if (nemaUloga) {
            ulogaRepozitorij.save(uloga4);
        } else if (!ulogaPostoji(sveUloge, uloga4)) {
            ulogaRepozitorij.save(uloga4);
        }

        Uloga uloga5 = new Uloga();
        uloga5.setNazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        if (nemaUloga) {
            ulogaRepozitorij.save(uloga5);
        } else if (!ulogaPostoji(sveUloge, uloga5)) {
            ulogaRepozitorij.save(uloga5);
        }

    }

    private boolean privilegijaPostoji(List<Privilegija> svePrivilegije, Privilegija privilegija) {
        boolean postoji = false;
        for (Privilegija p : svePrivilegije) {
            if (p.getNazivPrivilegije().equals(privilegija.getNazivPrivilegije())) {
                postoji = true;
                break;
            }
        }
        return postoji;
    }

    private void dodajPrivilegije() {

        boolean nemaPrivilegija = false;
        List<Privilegija> svePrivilegije = new ArrayList<>();

        try {
            svePrivilegije = privilegijaRepozitorij.findAll();
        } catch (Exception e) {
            nemaPrivilegija = true;
        }

        List<Uloga> uloga = new ArrayList<>();
        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        uloga.add(uloga1);

        Privilegija privilegija1 = new Privilegija();
        privilegija1.setNazivPrivilegije("brisanje_kreirane_zadace");
        privilegija1.setUloge(uloga);
        if (nemaPrivilegija) {
            privilegijaRepozitorij.save(privilegija1);
        } else if (!privilegijaPostoji(svePrivilegije, privilegija1)) {
            privilegijaRepozitorij.save(privilegija1);
        }
        
        Privilegija privilegija2 = new Privilegija();
        privilegija2.setNazivPrivilegije("registrovanje_casa");
        privilegija2.setUloge(uloga);
        if (nemaPrivilegija) {
            privilegijaRepozitorij.save(privilegija2);
        } else if (!privilegijaPostoji(svePrivilegije, privilegija2)) {
            privilegijaRepozitorij.save(privilegija2);
        }

	    Privilegija privilegija3 = new Privilegija();
        privilegija3.setNazivPrivilegije("editovanje_termina_ispita");
        privilegija3.setUloge(uloga);
        if (nemaPrivilegija) {
            privilegijaRepozitorij.save(privilegija3);
        } else if (!privilegijaPostoji(svePrivilegije, privilegija3)) {
            privilegijaRepozitorij.save(privilegija3);
        }

        Privilegija privilegija4 = new Privilegija();
        privilegija4.setNazivPrivilegije("kreiranje_obavjestenja");
        privilegija4.setUloge(uloga);
        if (nemaPrivilegija) {
            privilegijaRepozitorij.save(privilegija4);
        } else if (!privilegijaPostoji(svePrivilegije, privilegija4)) {
            privilegijaRepozitorij.save(privilegija4);
        }
        
        Uloga uloga2 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        uloga = new ArrayList<>();
        uloga.add(uloga2);
        Privilegija privilegija5 = new Privilegija();
        privilegija5.setNazivPrivilegije("brisanje_teme");
        privilegija5.setUloge(uloga);
        if (nemaPrivilegija) {
            privilegijaRepozitorij.save(privilegija5);
        } else if (!privilegijaPostoji(svePrivilegije, privilegija5)) {
            privilegijaRepozitorij.save(privilegija5);
        }
        Privilegija p=privilegijaRepozitorij.findBynazivPrivilegije("kreiranje_obavjestenja");
        List<Uloga> uloge=p.getUloge();
       
        if(!(uloge.contains(uloga2))){
            uloge.add(uloga2);
            privilegijaRepozitorij.save(p);
       }
    }
}
