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

        //Privilegije profesora

        uloga.add(profesor);

        Privilegija editovanje_kreirane_zadace = new Privilegija();
        editovanje_kreirane_zadace.setNazivPrivilegije("editovanje-kreirane-zadace");
        editovanje_kreirane_zadace.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-kreirane-zadace")) privilegijaRepozitorij.save(editovanje_kreirane_zadace);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("editovanje-kreirane-zadace").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-kreirane-zadace").getId());
                editovanje_kreirane_zadace.setUloge(noveUloge);
                privilegijaRepozitorij.save(editovanje_kreirane_zadace);
            }
        }
        uloga.clear();

        uloga.add(asistent);
        uloga.add(profesor);

        Privilegija pregled_zadace = new Privilegija();
        pregled_zadace.setNazivPrivilegije("pregled-zadace");
        pregled_zadace.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("pregled-zadace")) privilegijaRepozitorij.save(pregled_zadace);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pregled-zadace").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(asistent);
                noveUloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pregled-zadace").getId());
                pregled_zadace.setUloge(noveUloge);
                privilegijaRepozitorij.save(pregled_zadace);
            }
        }
        uloga.clear();

        uloga.add(profesor);

        Privilegija editovanje_obavjestenja = new Privilegija();
        editovanje_obavjestenja.setNazivPrivilegije("editovanje-obavjestenja");
        editovanje_obavjestenja.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-obavjestenja")) privilegijaRepozitorij.save(editovanje_obavjestenja);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("editovanje-obavjestenja").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-obavjestenja").getId());
                editovanje_obavjestenja.setUloge(noveUloge);
                privilegijaRepozitorij.save(editovanje_obavjestenja);
            }
        }
        uloga.clear();

        uloga.add(profesor);

        Privilegija editovanje_teme_na_forumu = new Privilegija();
        editovanje_teme_na_forumu.setNazivPrivilegije("editovanje-teme-na-forumu");
        editovanje_teme_na_forumu.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-teme-na-forumu")) privilegijaRepozitorij.save(editovanje_teme_na_forumu);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("editovanje-teme-na-forumu").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-teme-na-forumu").getId());
                editovanje_teme_na_forumu.setUloge(noveUloge);
                privilegijaRepozitorij.save(editovanje_teme_na_forumu);
            }
        }
        uloga.clear();

        uloga.add(profesor);
        uloga.add(asistent);

        Privilegija ostavljanje_komentara_na_rad_studenata = new Privilegija();
        ostavljanje_komentara_na_rad_studenata.setNazivPrivilegije("ostavljanje-komentara-na-rad-studenata");
        ostavljanje_komentara_na_rad_studenata.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("ostavljanje-komentara-na-rad-studenata")) privilegijaRepozitorij.save(ostavljanje_komentara_na_rad_studenata);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara-na-rad-studenata").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(profesor);
                noveUloge.add(asistent);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara-na-rad-studenata").getId());
                ostavljanje_komentara_na_rad_studenata.setUloge(noveUloge);
                privilegijaRepozitorij.save(ostavljanje_komentara_na_rad_studenata);
            }
        }
        uloga.clear();

        uloga.add(profesor);

        Privilegija preuzimanje_svih_zadaca = new Privilegija();
        preuzimanje_svih_zadaca.setNazivPrivilegije("preuzimanje-svih-zadaca");
        preuzimanje_svih_zadaca.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("preuzimanje-svih-zadaca")) privilegijaRepozitorij.save(preuzimanje_svih_zadaca);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("preuzimanje-svih-zadaca").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("preuzimanje-svih-zadaca").getId());
                preuzimanje_svih_zadaca.setUloge(noveUloge);
                privilegijaRepozitorij.save(preuzimanje_svih_zadaca);
            }
        }
        uloga.clear();

        uloga.add(profesor);
        uloga.add(asistent);

        Privilegija uvid_u_rezultate_anketa = new Privilegija();
        uvid_u_rezultate_anketa.setNazivPrivilegije("uvid-u-rezultate-anketa");
        uvid_u_rezultate_anketa.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("uvid-u-rezultate-anketa")) privilegijaRepozitorij.save(uvid_u_rezultate_anketa);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("uvid-u-rezultate-anketa").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(profesor);
                noveUloge.add(asistent);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("uvid-u-rezultate-anketa").getId());
                uvid_u_rezultate_anketa.setUloge(noveUloge);
                privilegijaRepozitorij.save(uvid_u_rezultate_anketa);
            }
        }
        uloga.clear();

        uloga.add(profesor);

        Privilegija uredjivanje_podataka_za_predmet = new Privilegija();
        uredjivanje_podataka_za_predmet.setNazivPrivilegije("uredjivanje-podataka-za-predmet");
        uredjivanje_podataka_za_predmet.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("uredjivanje-podataka-za-predmet")) privilegijaRepozitorij.save(uredjivanje_podataka_za_predmet);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("uredjivanje-podataka-za-predmet").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("uredjivanje-podataka-za-predmet").getId());
                uredjivanje_podataka_za_predmet.setUloge(noveUloge);
                privilegijaRepozitorij.save(uredjivanje_podataka_za_predmet);
            }
        }
        uloga.clear();

        uloga.add(profesor);
        Privilegija dodavanje_materijala = new Privilegija();
        dodavanje_materijala.setNazivPrivilegije("dodavanje-materijala");
        dodavanje_materijala.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("dodavanje-materijala")) privilegijaRepozitorij.save(dodavanje_materijala);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("dodavanje-materijala").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("dodavanje-materijala").getId());
                dodavanje_materijala.setUloge(noveUloge);
                privilegijaRepozitorij.save(dodavanje_materijala);
            }
        }
        uloga.clear();


        //Privilegije asistenta

        uloga.add(asistent);

        Privilegija brisanje_kreirane_zadace = new Privilegija();
        brisanje_kreirane_zadace.setNazivPrivilegije("brisanje-kreirane-zadace");
        brisanje_kreirane_zadace.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("brisanje-kreirane-zadace")) privilegijaRepozitorij.save(brisanje_kreirane_zadace);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("brisanje-kreirane-zadace").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(asistent);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-kreirane-zadace").getId());
                brisanje_kreirane_zadace.setUloge(noveUloge);
                privilegijaRepozitorij.save(brisanje_kreirane_zadace);
            }
        }
        uloga.clear();

        uloga.add(asistent);

        Privilegija pojedinacna_evidencija_prisustva = new Privilegija();
        pojedinacna_evidencija_prisustva.setNazivPrivilegije("pojedinacna-evidencija-prisustva");
        pojedinacna_evidencija_prisustva.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("pojedinacna-evidencija-prisustva")) privilegijaRepozitorij.save(pojedinacna_evidencija_prisustva);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pojedinacna-evidencija-prisustva").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(asistent);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pojedinacna-evidencija-prisustva").getId());
                pojedinacna_evidencija_prisustva.setUloge(noveUloge);
                privilegijaRepozitorij.save(pojedinacna_evidencija_prisustva);
            }
        }
        uloga.clear();

        uloga.add(asistent);

        Privilegija brisanje_materijala = new Privilegija();
        brisanje_materijala.setNazivPrivilegije("brisanje-materijala");
        brisanje_materijala.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("brisanje-materijala")) privilegijaRepozitorij.save(brisanje_materijala);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("brisanje-materijala").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(asistent);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-materijala").getId());
                brisanje_materijala.setUloge(noveUloge);
                privilegijaRepozitorij.save(brisanje_materijala);
            }
        }
        uloga.clear();


        //Privilegije admina

        uloga.add(admin);

        Privilegija brisanje_korisnika = new Privilegija();
        brisanje_korisnika.setNazivPrivilegije("brisanje-korisnika");
        brisanje_korisnika.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("brisanje-korisnika")) privilegijaRepozitorij.save(brisanje_korisnika);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("brisanje-korisnika").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(admin);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-korisnika").getId());
                brisanje_korisnika.setUloge(noveUloge);
                privilegijaRepozitorij.save(brisanje_korisnika);
            }
        }
        uloga.clear();

        uloga.add(admin);

        Privilegija obavjestavanje_korisnika_sistema = new Privilegija();
        obavjestavanje_korisnika_sistema.setNazivPrivilegije("obavjestavanje-korisnika-sistema");
        obavjestavanje_korisnika_sistema.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("obavjestavanje-korisnika-sistema")) privilegijaRepozitorij.save(obavjestavanje_korisnika_sistema);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("obavjestavanje-korisnika-sistema").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(admin);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("obavjestavanje-korisnika-sistema").getId());
                obavjestavanje_korisnika_sistema.setUloge(noveUloge);
                privilegijaRepozitorij.save(obavjestavanje_korisnika_sistema);
            }
        }
        uloga.clear();

        //Privilegije studenta - novo

        uloga.add(student);

        Privilegija popunjavanje_ankete = new Privilegija();
        popunjavanje_ankete.setNazivPrivilegije("popunjavanje-ankete");
        popunjavanje_ankete.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("popunjavanje-ankete")) privilegijaRepozitorij.save(popunjavanje_ankete);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("popunjavanje-ankete").getUloge();
            boolean trebaDodati = true;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) trebaDodati = false;
                }
            }
            if(trebaDodati){
                noveUloge.add(student);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("popunjavanje-ankete").getId());
                popunjavanje_ankete.setUloge(noveUloge);
                privilegijaRepozitorij.save(popunjavanje_ankete);
            }
        }
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
