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
        privilegija1.setNazivPrivilegije("kreiranje_zadace");
        privilegija1.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("kreiranje_zadace") == null)
            privilegijaRepozitorij.save(privilegija1);

        uloga = new ArrayList<>();
        Uloga uloga2 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        uloga.add(uloga2);
        Privilegija privilegija2 = new Privilegija();
        privilegija2.setNazivPrivilegije("kreiranje_korisnika");
        privilegija2.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("kreiranje_korisnika") == null)
            privilegijaRepozitorij.save(privilegija2);

        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija3 = new Privilegija();
        privilegija3.setNazivPrivilegije("brisanje_termina_ispita");
        privilegija3.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("brisanje_termina_ispita") == null)
            privilegijaRepozitorij.save(privilegija3);

        Privilegija privilegija4 = new Privilegija();
        privilegija4.setNazivPrivilegije("izmjena_dodijeljenih_bodova");
        privilegija4.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("izmjena_dodijeljenih_bodova") == null)
            privilegijaRepozitorij.save(privilegija4);

        Privilegija privilegija5 = new Privilegija();
        privilegija5.setNazivPrivilegije("izmjena_kviza");
        privilegija5.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("izmjena_kviza") == null)
            privilegijaRepozitorij.save(privilegija5);

        uloga.clear();
        Uloga uloga3 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        uloga.add(uloga3);
        Privilegija privilegija6 = new Privilegija();
        privilegija6.setNazivPrivilegije("izmjena_bodova_za_zadace");
        privilegija6.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("izmjena_bodova_za_zadace") == null)
            privilegijaRepozitorij.save(privilegija6);

        uloga.clear();
        uloga.add(uloga2);
        Privilegija privilegija7 = new Privilegija();
        privilegija7.setNazivPrivilegije("povezivanje_privilegija_uloga");
        privilegija7.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("povezivanje_privilegija_uloga") == null)
            privilegijaRepozitorij.save(privilegija7);

        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija8 = new Privilegija();
        privilegija8.setNazivPrivilegije("evidencija_studenata");
        privilegija8.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("evidencija_studenata") == null)
            privilegijaRepozitorij.save(privilegija8);

        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija9 = new Privilegija();
        privilegija9.setNazivPrivilegije("brisanje_teme");
        privilegija9.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("brisanje_teme") == null)
            privilegijaRepozitorij.save(privilegija9);

        uloga.clear();
        uloga.add(uloga3);
        Privilegija privilegija10 = new Privilegija();
        privilegija10.setNazivPrivilegije("unos_rezultata");
        privilegija10.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("unos_rezultata") == null)
            privilegijaRepozitorij.save(privilegija10);

    }

    private void dodajOdsjek() {

        if ((odsjekRepozitorij.findBynazivOdsjeka("RI")) == null) {
            Odsjek odsjek1 = new Odsjek();
            odsjek1.setId((long) (odsjekRepozitorij.count() + 1));
            odsjek1.setNazivOdsjeka("RI");
            odsjekRepozitorij.save(odsjek1);
        }
    }
}