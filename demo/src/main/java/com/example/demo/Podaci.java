package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Podaci {

    private UlogaRepozitorij ulogaRepozitorij;
    private PrivilegijaRepozitorij privilegijaRepozitorij;

    @Autowired
    public Podaci(UlogaRepozitorij ulogaRepozitorij, PrivilegijaRepozitorij privilegijaRepozitorij) {
        this.ulogaRepozitorij = ulogaRepozitorij;
        this.privilegijaRepozitorij = privilegijaRepozitorij;
    }

    @EventListener
    public void dodaj (ApplicationReadyEvent event){
        dodajUloge();
        dodajPrivilegije();
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

    private void poveziUloguPrivilegiju(Privilegija privilegija, Uloga uloga) {
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija);
        }
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije(privilegija.getNazivPrivilegije()).getUloge();
            boolean potrebno_dodati = true;
            for (Uloga uloga1: noveUloge) {
                if(uloga1.getNazivUloge().equals(uloga.getNazivUloge())) potrebno_dodati = false;
            }
            if(potrebno_dodati) {
                noveUloge.add(uloga);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije(privilegija.getNazivPrivilegije()).getId());
                privilegija.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija);
            }
        }
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
        poveziUloguPrivilegiju(privilegija_editovanje_korisnika, admin);

        Privilegija privilegija_brisanje_obavjestenja = new Privilegija();
        privilegija_brisanje_obavjestenja.setNazivPrivilegije("brisanje-obavjestenja");
        privilegija_brisanje_obavjestenja.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_brisanje_obavjestenja, admin);
        uloga.clear();

        uloga.add(asistent);
        // PRIVILEGIJE ZA ASISTENTA

        Privilegija privilegija_registrovanje_casa = new Privilegija();
        privilegija_registrovanje_casa.setNazivPrivilegije("registrovanje-casa");
        privilegija_registrovanje_casa.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_registrovanje_casa, asistent);

        Privilegija privilegija_editovanje_zadace = new Privilegija();
        privilegija_editovanje_zadace.setNazivPrivilegije("editovanje-kreirane-zadace");
        privilegija_editovanje_zadace.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_editovanje_zadace, asistent);

        Privilegija privilegija_editovanje_obavjestenja = new Privilegija();
        privilegija_editovanje_obavjestenja.setNazivPrivilegije("editovanje-obavjestenja");
        privilegija_editovanje_obavjestenja.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_editovanje_obavjestenja, asistent);

        Privilegija privilegija_editovanje_teme_forum = new Privilegija();
        privilegija_editovanje_teme_forum.setNazivPrivilegije("editovanje-teme-na-forumu");
        privilegija_editovanje_teme_forum.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_editovanje_teme_forum, asistent);

        Privilegija privilegija_pristup_grupama = new Privilegija();
        privilegija_pristup_grupama.setNazivPrivilegije("pristup-grupama-saradnik");
        privilegija_pristup_grupama.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_pristup_grupama, asistent);

        Privilegija privilegija_uvid_u_komentare = new Privilegija();
        privilegija_uvid_u_komentare.setNazivPrivilegije("uvid-u-komentare-saradnik");
        privilegija_uvid_u_komentare.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_uvid_u_komentare, asistent);
        uloga.clear();

        uloga.add(profesor);
        // PRIVILEGIJE ZA PROFESORA

        Privilegija privilegija_izmjena_bodova_zadace = new Privilegija();
        privilegija_izmjena_bodova_zadace.setNazivPrivilegije("izmjena-bodova-za-zadace");
        privilegija_izmjena_bodova_zadace.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_izmjena_bodova_zadace, profesor);

        Privilegija privilegija_brisanje_kreiranog_casa = new Privilegija();
        privilegija_brisanje_kreiranog_casa.setNazivPrivilegije("brisanje-kreiranog-casa");
        privilegija_brisanje_kreiranog_casa.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_brisanje_kreiranog_casa, profesor);

        Privilegija privilegija_dodjela_bodova_zadace = new Privilegija();
        privilegija_dodjela_bodova_zadace.setNazivPrivilegije("dodjela-bodova-za-zadace");
        privilegija_dodjela_bodova_zadace.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_dodjela_bodova_zadace, profesor);

        Privilegija privilegija_kreiranje_kviza = new Privilegija();
        privilegija_kreiranje_kviza.setNazivPrivilegije("kreiranje-kviza");
        privilegija_kreiranje_kviza.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_kreiranje_kviza, profesor);

        Privilegija privilegija_pregled_predmeta = new Privilegija();
        privilegija_pregled_predmeta.setNazivPrivilegije("pregled-predmeta-saradnik");
        privilegija_pregled_predmeta.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_pregled_predmeta, profesor);

        Privilegija privilegija_nacin_slanja = new Privilegija();
        privilegija_nacin_slanja.setNazivPrivilegije("izbor-nacina-slanja-zadace");
        privilegija_nacin_slanja.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_nacin_slanja, profesor);
        uloga.clear();
    }
}
