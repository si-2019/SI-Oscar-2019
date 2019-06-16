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
        dodajPrivilegijeHana();
        dodajPrivilegijeSulejman();
        dodajPrivilegijeMahira();
        dodajPrivilegijeZerina();
        dodajPrivilegijeAmina();
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

    void poveziUloguPrivilegiju(Privilegija privilegija, Uloga uloga) {
        if (!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija.getNazivPrivilegije())) {
            privilegijaRepozitorij.save(privilegija);
        } else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije(privilegija.getNazivPrivilegije()).getUloge();
            boolean potrebno_dodati = true;
            for (Uloga uloga1 : noveUloge) {
                if (uloga1.getNazivUloge().equals(uloga.getNazivUloge())) potrebno_dodati = false;
            }
            if (potrebno_dodati) {
                noveUloge.add(uloga);
                Privilegija p = privilegijaRepozitorij.findById(privilegijaRepozitorij.findBynazivPrivilegije(privilegija.getNazivPrivilegije()).getId()).get();
                p.setUloge(noveUloge);
                privilegijaRepozitorij.save(p);
            }
        }
    }

    private void dodajPrivilegijeHana() {
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

        Privilegija privilegija_unos_konacne_ocjene = new Privilegija();
        privilegija_unos_konacne_ocjene.setNazivPrivilegije("unos-konacne-ocjene");
        privilegija_unos_konacne_ocjene.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija_unos_konacne_ocjene, profesor);
        uloga.clear();
    }

    private void dodajPrivilegijeSulejman() {
        List<Uloga> uloga = new ArrayList<>();
        Uloga profesor = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        Uloga admin = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        Uloga asistent = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        Uloga studentska_sluzba = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);

        // Privilegije za profesora
        uloga.add(profesor);
        Privilegija privilegija39 = new Privilegija();
        privilegija39.setNazivPrivilegije("uvid-u-obavjestenja");
        privilegija39.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija39, profesor);

        Privilegija privilegija23 = new Privilegija();
        privilegija23.setNazivPrivilegije("kreiranje-teme-na-forumu");
        privilegija23.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija23, profesor);

        Privilegija privilegija22 = new Privilegija();
        privilegija22.setNazivPrivilegije("kreiranje-termina-ispita");
        privilegija22.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija22, profesor);

        Privilegija nova = new Privilegija();
        nova.setNazivPrivilegije("izmjena-bodova-za-ispite");
        nova.setUloge(uloga);
        poveziUloguPrivilegiju(nova, profesor);
        uloga.clear();

        // Privilegije za asistenta
        uloga.add(asistent);
        Privilegija privilegija147 = new Privilegija();
        privilegija147.setNazivPrivilegije("registrovanje-nove-zadace");
        privilegija147.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija147, asistent);

        Privilegija privilegija112 = new Privilegija();
        privilegija112.setNazivPrivilegije("uvid-u-obavjestenja");
        privilegija112.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija112, asistent);

        Privilegija privilegija45 = new Privilegija();
        privilegija45.setNazivPrivilegije("kreiranje-teme-na-forumu");
        privilegija45.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija45, asistent);

        Privilegija privilegija27 = new Privilegija();
        privilegija27.setNazivPrivilegije("brisanje-kreiranog-casa");
        privilegija27.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija27, asistent);
        uloga.clear();
		
		//Privilegije za studenta
		uloga.add(student);
        Privilegija privilegija1478 = new Privilegija();
        privilegija1478.setNazivPrivilegije("izmjena-licnih-informacija");
        privilegija1478.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija1478, student);
		uloga.clear();
		
        // Privilegije za admina
        uloga.add(admin);
        Privilegija privilegija2 = new Privilegija();
        privilegija2.setNazivPrivilegije("kreiranje-privilegija");
        privilegija2.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija2, admin);

        // Privilegije za studentsku sluzbu
        uloga.add(studentska_sluzba);
        Privilegija privilegija3 = new Privilegija();
        privilegija3.setNazivPrivilegije("postavljanje-obavjestenja");
        privilegija3.setUloge(uloga);
        poveziUloguPrivilegiju(privilegija3, studentska_sluzba);
    }


    private void dodajPrivilegijeMahira() {
        Uloga profesor=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        Uloga asistent=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        Uloga admin=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        Uloga studentska_sluzba=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        Uloga student=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);

        int counter=0;
        boolean treba_dodati=true;
        List<Uloga> uloge = new ArrayList<>();
        List<Uloga> nove_uloge = new ArrayList<>();

        Privilegija pregled_dostupnih_tema_za_zavrsni_rad=new Privilegija();
        uloge.add(student);
        pregled_dostupnih_tema_za_zavrsni_rad.setNazivPrivilegije("pregled-dostupnih-tema-za-zavrsni-rad");
        pregled_dostupnih_tema_za_zavrsni_rad.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("pregled-dostupnih-tema-za-zavrsni-rad")==null){
            privilegijaRepozitorij.save(pregled_dostupnih_tema_za_zavrsni_rad);
        }
        else{
            pregled_dostupnih_tema_za_zavrsni_rad=privilegijaRepozitorij.findBynazivPrivilegije("pregled-dostupnih-tema-za-zavrsni-rad");
            nove_uloge=pregled_dostupnih_tema_za_zavrsni_rad.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pregled-dostupnih-tema-za-zavrsni-rad").getId());
                pregled_dostupnih_tema_za_zavrsni_rad.setUloge(nove_uloge);
                privilegijaRepozitorij.save(pregled_dostupnih_tema_za_zavrsni_rad);

            }
        }


        treba_dodati=true;
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();

        Privilegija slanje_generisanih_poruka=new Privilegija();
        uloge.add(studentska_sluzba);
        slanje_generisanih_poruka.setNazivPrivilegije("slanje-generisanih-poruka");
        slanje_generisanih_poruka.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("slanje-generisanih-poruka")==null){
            privilegijaRepozitorij.save(slanje_generisanih_poruka);
        }
        else{
            slanje_generisanih_poruka=privilegijaRepozitorij.findBynazivPrivilegije("slanje-generisanih-poruka");
            nove_uloge=slanje_generisanih_poruka.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("slanje-generisanih-poruka").getId());
                slanje_generisanih_poruka.setUloge(nove_uloge);
                privilegijaRepozitorij.save(slanje_generisanih_poruka);

            }
        }


        treba_dodati=true;
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();

        Privilegija uvid_u_bodove_na_zadace=new Privilegija();
        uloge.add(student);
        uvid_u_bodove_na_zadace.setNazivPrivilegije("uvid-u-azurirane-bodove-na-zadace");
        uvid_u_bodove_na_zadace.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("uvid-u-azurirane-bodove-na-zadace")==null){
            privilegijaRepozitorij.save(uvid_u_bodove_na_zadace);
        }
        else{
            uvid_u_bodove_na_zadace=privilegijaRepozitorij.findBynazivPrivilegije("uvid-u-azurirane-bodove-na-zadace");
            nove_uloge=uvid_u_bodove_na_zadace.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("uvid-u-azurirane-bodove-na-zadace").getId());
                uvid_u_bodove_na_zadace.setUloge(nove_uloge);
                privilegijaRepozitorij.save(uvid_u_bodove_na_zadace);

            }
        }

        uloge=new ArrayList<>();
        nove_uloge=new ArrayList<>();
        counter=0;

        Privilegija prijava_na_vjezbe=new Privilegija();
        uloge.add(student);
        prijava_na_vjezbe.setNazivPrivilegije("prijava-na-vjezbe");
        prijava_na_vjezbe.setUloge(uloge);
        if(privilegijaRepozitorij.findBynazivPrivilegije("prijava-na-vjezbe")==null){
            privilegijaRepozitorij.save(prijava_na_vjezbe);
        }
        else{
            nove_uloge=privilegijaRepozitorij.findBynazivPrivilegije("prijava-na-vjezbe").getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge())
                            treba_dodati=false;
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("prijava-na-vjezbe").getId());
                prijava_na_vjezbe.setUloge(nove_uloge);
                privilegijaRepozitorij.save(prijava_na_vjezbe);

            }
        }

        uloge=new ArrayList<>();
        nove_uloge=new ArrayList<>();
        counter=0;

        Privilegija dodavanje_novih_materijala=new Privilegija();
        uloge.add(asistent);
        dodavanje_novih_materijala.setNazivPrivilegije("dodavanje-novih-materijala");
        dodavanje_novih_materijala.setUloge(uloge);
        if(privilegijaRepozitorij.findBynazivPrivilegije("dodavanje-novih-materijala")==null){
            privilegijaRepozitorij.save(dodavanje_novih_materijala);
        }
        else{
            nove_uloge=privilegijaRepozitorij.findBynazivPrivilegije("dodavanje-novih-materijala").getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge())
                            treba_dodati=false;
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("dodavanje-novih-materijala").getId());
                dodavanje_novih_materijala.setUloge(nove_uloge);
                privilegijaRepozitorij.save(dodavanje_novih_materijala);

            }
        }

        uloge=new ArrayList<>();
        nove_uloge=new ArrayList<>();
        counter=0;

        Privilegija promjena_izbornog_predmeta=new Privilegija();
        uloge.add(student);
        promjena_izbornog_predmeta.setNazivPrivilegije("promjena-izbornog-predmeta");
        promjena_izbornog_predmeta.setUloge(uloge);
        if(privilegijaRepozitorij.findBynazivPrivilegije("promjena-izbornog-predmeta")==null){
            privilegijaRepozitorij.save(promjena_izbornog_predmeta);
        }
        else{
            nove_uloge=privilegijaRepozitorij.findBynazivPrivilegije("promjena-izbornog-predmeta").getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge())
                            treba_dodati=false;
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("promjena-izbornog-predmeta").getId());
                promjena_izbornog_predmeta.setUloge(nove_uloge);
                privilegijaRepozitorij.save(promjena_izbornog_predmeta);

            }
        }

        Privilegija brisanje_kreirane_zadace=new Privilegija();
        uloge.add(profesor);
        brisanje_kreirane_zadace.setNazivPrivilegije("brisanje-kreirane-zadace");
        brisanje_kreirane_zadace.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-kreirane-zadace")==null){
            privilegijaRepozitorij.save(brisanje_kreirane_zadace);
        }
        else{
            brisanje_kreirane_zadace=privilegijaRepozitorij.findBynazivPrivilegije("brisanje-kreirane-zadace");
            nove_uloge=brisanje_kreirane_zadace.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-kreirane-zadace").getId());
                brisanje_kreirane_zadace.setUloge(nove_uloge);
                privilegijaRepozitorij.save(brisanje_kreirane_zadace);

            }
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        treba_dodati=true;
        counter=0;

        Privilegija registrovanje_casa=new Privilegija();
        uloge.add(profesor);
        registrovanje_casa.setNazivPrivilegije("registrovanje-casa");
        registrovanje_casa.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("registrovanje-casa")==null){
            privilegijaRepozitorij.save(registrovanje_casa);
        }
        else{
            registrovanje_casa=privilegijaRepozitorij.findBynazivPrivilegije("registrovanje-casa");
            nove_uloge=registrovanje_casa.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;

                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("registrovanje-casa").getId());
                registrovanje_casa.setUloge(nove_uloge);
                privilegijaRepozitorij.save(registrovanje_casa);
            }
        }

        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        counter=0;
        treba_dodati=true;

        Privilegija editovanje_termina_ispita=new Privilegija();
        uloge.add(profesor);
        editovanje_termina_ispita.setNazivPrivilegije("editovanje-termina-ispita");
        editovanje_termina_ispita.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-termina-ispita")==null){
            privilegijaRepozitorij.save(editovanje_termina_ispita);
        }
        else{
            editovanje_termina_ispita=privilegijaRepozitorij.findBynazivPrivilegije("editovanje-termina-ispita");
            nove_uloge=editovanje_termina_ispita.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-termina-ispita").getId());
                editovanje_termina_ispita.setUloge(nove_uloge);
                privilegijaRepozitorij.save(editovanje_termina_ispita);
            }
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        counter=0;
        treba_dodati=true;

        Privilegija brisanje_teme=new Privilegija();
        uloge.add(asistent);
        brisanje_teme.setNazivPrivilegije("brisanje-teme");
        brisanje_teme.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-teme")==null){
            privilegijaRepozitorij.save(brisanje_teme);
        }
        else{
            brisanje_teme=privilegijaRepozitorij.findBynazivPrivilegije("brisanje-teme");
            nove_uloge=brisanje_teme.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-teme").getId());
                brisanje_teme.setUloge(nove_uloge);
                privilegijaRepozitorij.save(brisanje_teme);

            }

        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        counter=0;
        treba_dodati=true;

        Privilegija kreiranje_obavjestenja=new Privilegija();
        uloge.add(asistent);
        uloge.add(profesor);
        kreiranje_obavjestenja.setNazivPrivilegije("kreiranje-obavjestenja");
        kreiranje_obavjestenja.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-obavjestenja")==null){
            privilegijaRepozitorij.save(kreiranje_obavjestenja);
        }
        else{
            kreiranje_obavjestenja=privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-obavjestenja");
            nove_uloge=kreiranje_obavjestenja.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }

                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-obavjestenja").getId());
                kreiranje_obavjestenja.setUloge(nove_uloge);
                privilegijaRepozitorij.save(kreiranje_obavjestenja);

            }

        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        counter=0;
        treba_dodati=true;

        Privilegija brisanje_obavjestenja=new Privilegija();
        uloge.add(asistent);
        uloge.add(profesor);
        uloge.add(studentska_sluzba);
        brisanje_obavjestenja.setNazivPrivilegije("brisanje-obavjestenja");
        brisanje_obavjestenja.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-obavjestenja")==null){
            privilegijaRepozitorij.save(brisanje_obavjestenja);
        }
        else{
            brisanje_obavjestenja=privilegijaRepozitorij.findBynazivPrivilegije("brisanje-obavjestenja");
            nove_uloge=brisanje_obavjestenja.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }

                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-obavjestenja").getId());
                brisanje_obavjestenja.setUloge(nove_uloge);
                privilegijaRepozitorij.save(brisanje_obavjestenja);
            }
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        counter=0;
        treba_dodati=true;

        Privilegija editovanje_postavljenih_obavjestenja=new Privilegija();
        uloge.add(admin);
        editovanje_postavljenih_obavjestenja.setNazivPrivilegije("editovanje-postavljenih-obavjestenja");
        editovanje_postavljenih_obavjestenja.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-postavljenih-obavjestenja")==null){
            privilegijaRepozitorij.save(editovanje_postavljenih_obavjestenja);
        }
        else{
            editovanje_postavljenih_obavjestenja=privilegijaRepozitorij.findBynazivPrivilegije("editovanje-postavljenih-obavjestenja");
            nove_uloge=editovanje_postavljenih_obavjestenja.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-postavljenih-obavjestenja").getId());
                editovanje_postavljenih_obavjestenja.setUloge(nove_uloge);
                privilegijaRepozitorij.save(editovanje_postavljenih_obavjestenja);

            }

        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        counter=0;
        treba_dodati=true;

        Privilegija unosenje_bodova_ispita=new Privilegija();
        uloge.add(profesor);
        unosenje_bodova_ispita.setNazivPrivilegije("unosenje-bodova-ispita");
        unosenje_bodova_ispita.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("unosenje-bodova-ispita")==null){
            privilegijaRepozitorij.save(unosenje_bodova_ispita);
        }
        else{
            unosenje_bodova_ispita=privilegijaRepozitorij.findBynazivPrivilegije("unosenje-bodova-ispita");
            nove_uloge=unosenje_bodova_ispita.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("unosenje-bodova-ispita").getId());
                unosenje_bodova_ispita.setUloge(nove_uloge);
                privilegijaRepozitorij.save(unosenje_bodova_ispita);
            }
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        counter=0;
        treba_dodati=true;

        Privilegija ostavljanje_komentara_na_zadace=new Privilegija();
        uloge.add(profesor);
        ostavljanje_komentara_na_zadace.setNazivPrivilegije("ostavljanje-komentara-na-zadace");
        ostavljanje_komentara_na_zadace.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara-na-zadace")==null){
            privilegijaRepozitorij.save(ostavljanje_komentara_na_zadace);
        }
        else{
            ostavljanje_komentara_na_zadace=privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara-na-zadace");
            nove_uloge=ostavljanje_komentara_na_zadace.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }

            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }

                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara-na-zadace").getId());
                ostavljanje_komentara_na_zadace.setUloge(nove_uloge);
                privilegijaRepozitorij.save(ostavljanje_komentara_na_zadace);
            }
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        counter=0;
        treba_dodati=true;

        Privilegija editovanje_komentara=new Privilegija();
        uloge.add(asistent);
        editovanje_komentara.setNazivPrivilegije("editovanje-komentara");
        editovanje_komentara.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-komentara")==null){
            privilegijaRepozitorij.save(editovanje_komentara);
        }
        else{
            editovanje_komentara=privilegijaRepozitorij.findBynazivPrivilegije("editovanje-komentara");
            nove_uloge=editovanje_komentara.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }

            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-komentara").getId());
                editovanje_komentara.setUloge(nove_uloge);
                privilegijaRepozitorij.save(editovanje_komentara);
            }
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        counter=0;
        treba_dodati=true;

        Privilegija prikaz_kalendara=new Privilegija();
        uloge.add(student);
        prikaz_kalendara.setNazivPrivilegije("prikaz-kalendara");
        prikaz_kalendara.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("prikaz-kalendara")==null){
            privilegijaRepozitorij.save(prikaz_kalendara);
        }
        else{
            prikaz_kalendara=privilegijaRepozitorij.findBynazivPrivilegije("prikaz-kalendara");
            nove_uloge=prikaz_kalendara.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("prikaz-kalendara").getId());
                prikaz_kalendara.setUloge(nove_uloge);
                privilegijaRepozitorij.save(prikaz_kalendara);

            }
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        counter=0;
        treba_dodati=true;

        Privilegija uvid_u_komentare=new Privilegija();
        uloge.add(profesor);
        uvid_u_komentare.setNazivPrivilegije("uvid-u-komentare");
        uvid_u_komentare.setUloge(uloge);

        if(privilegijaRepozitorij.findBynazivPrivilegije("uvid-u-komentare")==null){
            privilegijaRepozitorij.save(uvid_u_komentare);
        }
        else{
            uvid_u_komentare=privilegijaRepozitorij.findBynazivPrivilegije("uvid-u-komentare");
            nove_uloge=uvid_u_komentare.getUloge();
            for(Uloga u:nove_uloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge()==u1.getNazivUloge()){
                        counter++;
                    }
                }
            }
            if(counter!=uloge.size()){
                for(Uloga u:uloge){
                    for(Uloga u1:nove_uloge){
                        if(u.getNazivUloge()==u1.getNazivUloge()){
                            treba_dodati=false;
                        }
                    }
                    if(treba_dodati)    nove_uloge.add(u);
                    treba_dodati=true;
                }

                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("uvid-u-komentare").getId());
                uvid_u_komentare.setUloge(nove_uloge);
                privilegijaRepozitorij.save(uvid_u_komentare);
            }
        }
    }

    private void dodajPrivilegijeZerina() {
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
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
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("obavjestavanje-korisnika-sistema").getId());
                obavjestavanje_korisnika_sistema.setUloge(noveUloge);
                privilegijaRepozitorij.save(obavjestavanje_korisnika_sistema);
            }
        }
        uloga.clear();

        //Privilegije studenta

        uloga.add(student);

        Privilegija popunjavanje_ankete = new Privilegija();
        popunjavanje_ankete.setNazivPrivilegije("popunjavanje-ankete");
        popunjavanje_ankete.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("popunjavanje-ankete")) privilegijaRepozitorij.save(popunjavanje_ankete);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("popunjavanje-ankete").getUloge();
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("popunjavanje-ankete").getId());
                popunjavanje_ankete.setUloge(noveUloge);
                privilegijaRepozitorij.save(popunjavanje_ankete);
            }
        }
        uloga.clear();

        uloga.add(student);

        Privilegija pisanje_komentara_na_forumu = new Privilegija();
        pisanje_komentara_na_forumu.setNazivPrivilegije("pisanje-komentara-na-forumu");
        pisanje_komentara_na_forumu.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("pisanje-komentara-na-forumu")) privilegijaRepozitorij.save(pisanje_komentara_na_forumu);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pisanje-komentara-na-forumu").getUloge();
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pisanje-komentara-na-forumu").getId());
                pisanje_komentara_na_forumu.setUloge(noveUloge);
                privilegijaRepozitorij.save(pisanje_komentara_na_forumu);
            }
        }
        uloga.clear();

        //Privilegije studentske sluzbe

        uloga.add(studentska_sluzba);

        Privilegija upis_studenata_u_semestar = new Privilegija();
        upis_studenata_u_semestar.setNazivPrivilegije("upis-studenata-u-semestar");
        upis_studenata_u_semestar.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("upis-studenata-u-semestar")) privilegijaRepozitorij.save(upis_studenata_u_semestar);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("upis-studenata-u-semestar").getUloge();
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("upis-studenata-u-semestar").getId());
                upis_studenata_u_semestar.setUloge(noveUloge);
                privilegijaRepozitorij.save(upis_studenata_u_semestar);
            }
        }
        uloga.clear();

        uloga.add(studentska_sluzba);

        Privilegija unos_finansijskih_obaveza_studenata = new Privilegija();
        unos_finansijskih_obaveza_studenata.setNazivPrivilegije("unos-finansijskih-obaveza-studenata");
        unos_finansijskih_obaveza_studenata.setUloge(uloga);

        if(!privilegijaRepozitorij.existsBynazivPrivilegije("unos-finansijskih-obaveza-studenata")) privilegijaRepozitorij.save(unos_finansijskih_obaveza_studenata);
        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("unos-finansijskih-obaveza-studenata").getUloge();
            int brojac = 0;
            for (Uloga u: noveUloge){
                for (Uloga u1: uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) {
                        brojac++;
                    }
                }
            }
            if(brojac != uloga.size()){
                boolean trebaDodati = true;
                for (Uloga ulogaKod : uloga){
                    for (Uloga ulogaBaza : noveUloge){
                        if(ulogaKod.getNazivUloge().equals(ulogaBaza.getNazivUloge())) trebaDodati = false;
                    }
                    if(trebaDodati) {
                        noveUloge.add(ulogaKod);
                    }
                    trebaDodati = true;
                }
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("unos-finansijskih-obaveza-studenata").getId());
                unos_finansijskih_obaveza_studenata.setUloge(noveUloge);
                privilegijaRepozitorij.save(unos_finansijskih_obaveza_studenata);
            }
        }
        uloga.clear();
    }

    private void dodajPrivilegijeAmina() {
        List<Uloga> uloga = new ArrayList<>();
        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        uloga.add(uloga1);

        Privilegija privilegija1 = new Privilegija();
        privilegija1.setNazivPrivilegije("kreiranje-zadace");
        privilegija1.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-zadace")==null)
            privilegijaRepozitorij.save(privilegija1);
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-zadace").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-zadace").getId());
                privilegija1.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija1);
            }
        }

        uloga = new ArrayList<>();
        Uloga uloga2 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        uloga.add(uloga2);
        Privilegija privilegija2 = new Privilegija();
        privilegija2.setNazivPrivilegije("kreiranje-korisnika");
        privilegija2.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-korisnika") == null)
            privilegijaRepozitorij.save(privilegija2);
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-korisnika").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga2);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-korisnika").getId());
                privilegija2.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija2);
            }
        }
        uloga.clear();


        uloga.add(uloga1);
        Privilegija privilegija3 = new Privilegija();
        privilegija3.setNazivPrivilegije("brisanje-termina-ispita");
        privilegija3.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-termina-ispita")==null) privilegijaRepozitorij.save(privilegija3);
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("brisanje-termina-ispita").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-termina-ispita").getId());
                privilegija3.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija3);
            }
        }

        Privilegija privilegija4 = new Privilegija();
        privilegija4.setNazivPrivilegije("izmjena-dodijeljenih-bodova");
        privilegija4.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("izmjena-dodijeljenih-bodova")==null) privilegijaRepozitorij.save(privilegija4);
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("izmjena-dodijeljenih-bodova").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("izmjena-dodijeljenih-bodova").getId());
                privilegija4.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija4);
            }
        }

        Privilegija privilegija5 = new Privilegija();
        privilegija5.setNazivPrivilegije("izmjena-kviza");
        privilegija5.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("izmjena-kviza")==null) privilegijaRepozitorij.save(privilegija5);
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("izmjena-kviza").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("izmjena-kviza").getId());
                privilegija5.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija5);
            }
        }
        uloga.clear();


        Uloga uloga3 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        uloga.add(uloga3);
        Privilegija privilegija6 = new Privilegija();
        privilegija6.setNazivPrivilegije("izmjena-bodova-za-zadace");
        privilegija6.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("izmjena-bodova-za-zadace") == null)
            privilegijaRepozitorij.save(privilegija6);
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("izmjena-bodova-za-zadace").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga3);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("izmjena-bodova-za-zadace").getId());
                privilegija6.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija6);
            }
        }
        uloga.clear();


        uloga.add(uloga2);
        Privilegija privilegija7 = new Privilegija();
        privilegija7.setNazivPrivilegije("povezivanje-privilegija-uloga");
        privilegija7.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("povezivanje-privilegija-uloga") == null)
            privilegijaRepozitorij.save(privilegija7);
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("povezivanje-privilegija-uloga").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga2);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("povezivanje-privilegija-uloga").getId());
                privilegija7.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija7);
            }
        }
        uloga.clear();


        uloga.add(uloga1);
        Privilegija privilegija8 = new Privilegija();
        privilegija8.setNazivPrivilegije("evidencija-prijavljenih-studenata");
        privilegija8.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("evidencija-prijavljenih-studenata") == null)
            privilegijaRepozitorij.save(privilegija8);
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("evidencija-prijavljenih-studenata").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("evidencija-prijavljenih-studenata").getId());
                privilegija8.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija8);
            }
        }

        Privilegija privilegija9 = new Privilegija();
        privilegija9.setNazivPrivilegije("brisanje-teme");
        privilegija9.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("brisanje-teme") == null)
            privilegijaRepozitorij.save(privilegija9);
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("brisanje-teme").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-teme").getId());
                privilegija9.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija9);
            }
        }
        uloga.clear();


        uloga.add(uloga3);
        Privilegija privilegija10 = new Privilegija();
        privilegija10.setNazivPrivilegije("unos-rezultata");
        privilegija10.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("unos-rezultata") == null)
            privilegijaRepozitorij.save(privilegija10);
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("unos-rezultata").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloga){
                    if(u.getNazivUloge().equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga3);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("unos-rezultata").getId());
                privilegija10.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija10);
            }
        }

        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija11 = new Privilegija();
        privilegija11.setNazivPrivilegije("kreiranje-grupa");
        privilegija11.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-grupa") == null) {
            privilegijaRepozitorij.save(privilegija11);
        } else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-grupa").getUloge();
            boolean treba_dodati = true;
            for (Uloga u : noveUloge) {
                for (Uloga u1 : uloga) {
                    if (u.getNazivUloge().equals(u1.getNazivUloge()))
                        treba_dodati = false;
                }
            }
            if (treba_dodati) {
                noveUloge.add(uloga1);
                privilegijaRepozitorij
                        .deleteById(privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-grupa").getId());
                privilegija11.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija11);
            }
        }
        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija12 = new Privilegija();
        privilegija12.setNazivPrivilegije("pristup-grupama");
        privilegija12.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pristup-grupama") == null) {
            privilegijaRepozitorij.save(privilegija12);
        }

        else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pristup-grupama").getUloge();
            boolean treba_dodati = true;
            for (Uloga u : noveUloge) {
                for (Uloga u1 : uloga) {
                    if (u.getNazivUloge().equals(u1.getNazivUloge()))
                        treba_dodati = false;
                }
            }
            if (treba_dodati) {
                noveUloge.add(uloga1);
                privilegijaRepozitorij
                        .deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pristup-grupama").getId());
                privilegija12.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija12);
            }
        }
        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija13 = new Privilegija();
        privilegija13.setNazivPrivilegije("pregled-komentara");
        privilegija13.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pregled-komentara") == null) {
            privilegijaRepozitorij.save(privilegija13);
        } else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pregled-komentara").getUloge();
            boolean treba_dodati = true;
            for (Uloga u : noveUloge) {
                for (Uloga u1 : uloga) {
                    if (u.getNazivUloge().equals(u1.getNazivUloge()))
                        treba_dodati = false;
                }
            }
            if (treba_dodati) {
                noveUloge.add(uloga1);
                privilegijaRepozitorij
                        .deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pregled-komentara").getId());
                privilegija13.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija13);
            }
        }

        uloga.clear();

        Uloga uloga4 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        uloga.add(uloga4);
        Privilegija privilegija14 = new Privilegija();
        privilegija14.setNazivPrivilegije("pristup-informacijama");
        privilegija14.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pristup-informacijama") == null) {
            privilegijaRepozitorij.save(privilegija14);

        } else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pristup-informacijama").getUloge();
            boolean treba_dodati = true;
            for (Uloga u : noveUloge) {
                for (Uloga u1 : uloga) {
                    if (u.getNazivUloge().equals(u1.getNazivUloge()))
                        treba_dodati = false;
                }
            }
            if (treba_dodati) {
                noveUloge.add(uloga4);
                privilegijaRepozitorij
                        .deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pristup-informacijama").getId());
                privilegija14.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija14);
            }
        }

        uloga.clear();
        uloga.add(uloga4);
        Privilegija privilegija15 = new Privilegija();
        privilegija15.setNazivPrivilegije("pregled-termina");
        privilegija15.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pregled-termina") == null) {
            privilegijaRepozitorij.save(privilegija15);

        } else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pregled-termina").getUloge();
            boolean treba_dodati = true;
            for (Uloga u : noveUloge) {
                for (Uloga u1 : uloga) {
                    if (u.getNazivUloge().equals(u1.getNazivUloge()))
                        treba_dodati = false;
                }
            }
            if (treba_dodati) {
                noveUloge.add(uloga4);
                privilegijaRepozitorij
                        .deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pregled-termina").getId());
                privilegija15.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija15);
            }
        }
        uloga.clear();
        uloga.add(uloga4);
        Privilegija privilegija16 = new Privilegija();
        privilegija16.setNazivPrivilegije("pregled-anketa");
        privilegija16.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pregled-anketa") == null) {
            privilegijaRepozitorij.save(privilegija16);

        } else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pregled-anketa").getUloge();
            boolean treba_dodati = true;
            for (Uloga u : noveUloge) {
                for (Uloga u1 : uloga) {
                    if (u.getNazivUloge().equals(u1.getNazivUloge()))
                        treba_dodati = false;
                }
            }
            if (treba_dodati) {
                noveUloge.add(uloga4);
                privilegijaRepozitorij
                        .deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pregled-anketa").getId());
                privilegija15.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija16);
            }
        }

        uloga.clear();
        uloga.add(uloga3);
        Privilegija privilegija17 = new Privilegija();
        privilegija17.setNazivPrivilegije("ostavljanje-komentara");
        privilegija17.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara") == null) {
            privilegijaRepozitorij.save(privilegija17);

        } else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara").getUloge();
            boolean treba_dodati = true;
            for (Uloga u : noveUloge) {
                for (Uloga u1 : uloga) {
                    if (u.getNazivUloge().equals(u1.getNazivUloge()))
                        treba_dodati = false;
                }
            }
            if (treba_dodati) {
                noveUloge.add(uloga3);
                privilegijaRepozitorij
                        .deleteById(privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara").getId());
                privilegija15.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija17);
            }
        }
        uloga.clear();
        uloga.add(uloga4);
        Privilegija privilegija18 = new Privilegija();
        privilegija18.setNazivPrivilegije("digitalno-slanje-zadace");
        privilegija18.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("digitalno-slanje-zadace") == null) {
            privilegijaRepozitorij.save(privilegija18);

        } else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("digitalno-slanje-zadace").getUloge();
            boolean treba_dodati = true;
            for (Uloga u : noveUloge) {
                for (Uloga u1 : uloga) {
                    if (u.getNazivUloge().equals(u1.getNazivUloge()))
                        treba_dodati = false;
                }
            }
            if (treba_dodati) {
                noveUloge.add(uloga4);
                privilegijaRepozitorij
                        .deleteById(privilegijaRepozitorij.findBynazivPrivilegije("digitalno-slanje-zadace").getId());
                privilegija15.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija18);
            }
        }

        uloga.clear();
        uloga.add(uloga4);
        Privilegija privilegija19 = new Privilegija();
        privilegija19.setNazivPrivilegije("pregled-materijala");
        privilegija19.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pregled-materijala") == null) {
            privilegijaRepozitorij.save(privilegija19);

        } else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pregled-materijala").getUloge();
            boolean treba_dodati = true;
            for (Uloga u : noveUloge) {
                for (Uloga u1 : uloga) {
                    if (u.getNazivUloge().equals(u1.getNazivUloge()))
                        treba_dodati = false;
                }
            }
            if (treba_dodati) {
                noveUloge.add(uloga4);
                privilegijaRepozitorij
                        .deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pregled-materijala").getId());
                privilegija15.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija19);
            }
        }
        uloga.clear();
        uloga.add(uloga4);
        Privilegija privilegija20 = new Privilegija();
        privilegija20.setNazivPrivilegije("pristup-izvjestaju");
        privilegija20.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pristup-izvjestaju") == null) {
            privilegijaRepozitorij.save(privilegija20);

        } else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pristup-izvjestaju").getUloge();
            boolean treba_dodati = true;
            for (Uloga u : noveUloge) {
                for (Uloga u1 : uloga) {
                    if (u.getNazivUloge().equals(u1.getNazivUloge()))
                        treba_dodati = false;
                }
            }
            if (treba_dodati) {
                noveUloge.add(uloga4);
                privilegijaRepozitorij
                        .deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pristup-izvjestaju").getId());
                privilegija15.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija20);
            }
        }

        uloga.clear();
        Uloga uloga5 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        uloga.add(uloga5);
        Privilegija privilegija21 = new Privilegija();
        privilegija21.setNazivPrivilegije("izdavanje-uvjerenja");
        privilegija21.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("izdavanje-uvjerenja") == null) {
            privilegijaRepozitorij.save(privilegija21);

        } else {
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("izdavanje-uvjerenja").getUloge();
            boolean treba_dodati = true;
            for (Uloga u : noveUloge) {
                for (Uloga u1 : uloga) {
                    if (u.getNazivUloge().equals(u1.getNazivUloge()))
                        treba_dodati = false;
                }
            }
            if (treba_dodati) {
                noveUloge.add(uloga5);
                privilegijaRepozitorij
                        .deleteById(privilegijaRepozitorij.findBynazivPrivilegije("izdavanje-uvjerenja").getId());
                privilegija15.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija21);
            }
        }
    }
}
