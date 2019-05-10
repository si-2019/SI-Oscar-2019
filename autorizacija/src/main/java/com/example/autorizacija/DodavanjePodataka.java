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
        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        Uloga uloga2 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        Uloga uloga3 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        Uloga uloga4 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        Uloga uloga5 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);

        uloga.add(uloga1);

        Privilegija privilegija1 = new Privilegija();
        privilegija1.setNazivPrivilegije("editovanje-kreirane-zadace");
        privilegija1.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-kreirane-zadace")) privilegijaRepozitorij.save(privilegija1);

        uloga.clear();

        uloga.add(uloga3);

        Privilegija privilegija2 = new Privilegija();
        privilegija2.setNazivPrivilegije("brisanje-kreirane-zadace");
        privilegija2.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("brisanje-kreirane-zadace")) privilegijaRepozitorij.save(privilegija2);

        uloga.clear();

        uloga.add(uloga2);

        Privilegija privilegija3 = new Privilegija();
        privilegija3.setNazivPrivilegije("brisanje-korisnika");
        privilegija3.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("brisanje-korisnika")) privilegijaRepozitorij.save(privilegija3);

        uloga.clear();

        uloga.add(uloga3);
        uloga.add(uloga1);

        Privilegija privilegija4 = new Privilegija();
        privilegija4.setNazivPrivilegije("pregled-zadace");
        privilegija4.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("pregled-zadace")) privilegijaRepozitorij.save(privilegija4);

        uloga.clear();

        uloga.add(uloga1);

        Privilegija privilegija5 = new Privilegija();
        privilegija5.setNazivPrivilegije("editovanje-obavjestenja");
        privilegija5.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-obavjestenja")) privilegijaRepozitorij.save(privilegija5);

        uloga.clear();

        uloga.add(uloga1);

        Privilegija privilegija6 = new Privilegija();
        privilegija6.setNazivPrivilegije("editovanje-teme-na-forumu");
        privilegija6.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-teme-na-forumu")) privilegijaRepozitorij.save(privilegija6);

        uloga.clear();

        uloga.add(uloga2);

        Privilegija privilegija7 = new Privilegija();
        privilegija7.setNazivPrivilegije("obavjestavanje-korisnika-sistema");
        privilegija7.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("obavjestavanje-korisnika-sistema")) privilegijaRepozitorij.save(privilegija7);

        uloga.clear();

        uloga.add(uloga3);

        Privilegija privilegija8 = new Privilegija();
        privilegija8.setNazivPrivilegije("pojedinacna-evidencija-prisustva");
        privilegija8.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("pojedinacna-evidencija-prisustva")) privilegijaRepozitorij.save(privilegija8);

        uloga.clear();
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