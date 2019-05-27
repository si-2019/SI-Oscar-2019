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
            PrivilegijaRepozitorij privilegijaRepozitorij, OdsjekRepozitorij odsjekRepozitorij) {
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

    private void dodajUloge() {

        Uloga uloga1 = new Uloga();
        uloga1.setNazivUloge(ImenaUloga.ADMIN);
        if (ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN) == null)
            ulogaRepozitorij.save(uloga1);

        Uloga uloga2 = new Uloga();
        uloga2.setNazivUloge(ImenaUloga.STUDENT);
        if (ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT) == null)
            ulogaRepozitorij.save(uloga2);

        Uloga uloga3 = new Uloga();
        uloga3.setNazivUloge(ImenaUloga.PROFESOR);
        if (ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR) == null)
            ulogaRepozitorij.save(uloga3);

        Uloga uloga4 = new Uloga();
        uloga4.setNazivUloge(ImenaUloga.ASISTENT);
        if (ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT) == null)
            ulogaRepozitorij.save(uloga4);

        Uloga uloga5 = new Uloga();
        uloga5.setNazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        if (ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA) == null)
            ulogaRepozitorij.save(uloga5);
    }

    private void dodajPrivilegije() {

        List<Uloga> uloga = new ArrayList<>();
        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        uloga.add(uloga1);

        Privilegija privilegija1 = new Privilegija();
        privilegija1.setNazivPrivilegije("kreiranje-zadace");
        privilegija1.setUloge(uloga);

        if (privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-zadace") == null)
            privilegijaRepozitorij.save(privilegija1);

        uloga = new ArrayList<>();
        Uloga uloga2 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        uloga.add(uloga2);
        Privilegija privilegija2 = new Privilegija();
        privilegija2.setNazivPrivilegije("kreiranje-korisnika");
        privilegija2.setUloge(uloga);

        if (privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-korisnika") == null)
            privilegijaRepozitorij.save(privilegija2);

        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija3 = new Privilegija();
        privilegija3.setNazivPrivilegije("brisanje-termina-ispita");
        privilegija3.setUloge(uloga);

        if (privilegijaRepozitorij.findBynazivPrivilegije("brisanje-termina-ispita") == null)
            privilegijaRepozitorij.save(privilegija3);

        Privilegija privilegija4 = new Privilegija();
        privilegija4.setNazivPrivilegije("izmjena-dodijeljenih-bodova");
        privilegija4.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("izmjena-dodijeljenih-bodova") == null)
            privilegijaRepozitorij.save(privilegija4);

        Privilegija privilegija5 = new Privilegija();
        privilegija5.setNazivPrivilegije("izmjena-kviza");
        privilegija5.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("izmjena-kviza") == null)
            privilegijaRepozitorij.save(privilegija5);

        uloga.clear();
         Uloga uloga3 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        uloga.add(uloga3);
        Privilegija privilegija6 = new Privilegija();
        privilegija6.setNazivPrivilegije("izmjena-bodova-za-zadace");
        privilegija6.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("izmjena-bodova-za-zadace") == null)
            privilegijaRepozitorij.save(privilegija6);

        uloga.clear();
        uloga.add(uloga2);
        Privilegija privilegija7 = new Privilegija();
        privilegija7.setNazivPrivilegije("povezivanje-privilegija-uloga");
        privilegija7.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("povezivanje-privilegija-uloga") == null)
            privilegijaRepozitorij.save(privilegija7);

        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija8 = new Privilegija();
        privilegija8.setNazivPrivilegije("evidencija-prijavljenih-studenata");
        privilegija8.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("evidencija-prijavljenih-studenata") == null)
            privilegijaRepozitorij.save(privilegija8);

        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija9 = new Privilegija();
        privilegija9.setNazivPrivilegije("brisanje-teme");
        privilegija9.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("brisanje-teme") == null)
            privilegijaRepozitorij.save(privilegija9);

        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija10 = new Privilegija();
        privilegija10.setNazivPrivilegije("kreiranje-grupa");
        privilegija10.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-grupa") == null)
            privilegijaRepozitorij.save(privilegija10);

        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija11 = new Privilegija();
        privilegija11.setNazivPrivilegije("pristup-grupama");
        privilegija11.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pristup-grupama") == null)
            privilegijaRepozitorij.save(privilegija11);

        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija12 = new Privilegija();
        privilegija12.setNazivPrivilegije("pregled-komentara");
        privilegija12.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pregled-komentara") == null)
            privilegijaRepozitorij.save(privilegija12);

    }

    private void dodajOdsjek() {

        if ((odsjekRepozitorij.findBynazivOdsjeka("RI")) == null) {
            Odsjek odsjek1 = new Odsjek();

            odsjek1.setNazivOdsjeka("RI");
            odsjekRepozitorij.save(odsjek1);

        }

    }
}
