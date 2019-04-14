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

    @Autowired
    public DodavanjePodataka(KorisnikRepozitorij korisnikRepozitorij, UlogaRepozitorij ulogaRepozitorij, PrivilegijaRepozitorij privilegijaRepozitorij) {
        this.korisnikRepozitorij = korisnikRepozitorij;
        this.ulogaRepozitorij = ulogaRepozitorij;
        this.privilegijaRepozitorij = privilegijaRepozitorij;
    }

    @EventListener
    public void dodaj (ApplicationReadyEvent event){
        dodajUloge();
        dodajPrivilegije();
    }

    private boolean ulogaPostoji (List<Uloga> sveUloge, Uloga uloga){
        boolean postoji = false;
        for(Uloga u: sveUloge){
            if(u.getNazivUloge().equals(uloga.getNazivUloge())){
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
        }
        catch (Exception e){
            nemaUloga = true;
        }

        Uloga uloga1 = new Uloga();
        uloga1.setNazivUloge(ImenaUloga.ADMIN);
        if(nemaUloga){
            ulogaRepozitorij.save(uloga1);
        }
        else if (!ulogaPostoji(sveUloge, uloga1)){
            ulogaRepozitorij.save(uloga1);
        }

        Uloga uloga2 = new Uloga();
        uloga2.setNazivUloge(ImenaUloga.STUDENT);
        if(nemaUloga){
            ulogaRepozitorij.save(uloga2);
        }
        else if (!ulogaPostoji(sveUloge, uloga2)){
            ulogaRepozitorij.save(uloga2);
        }

        Uloga uloga3 = new Uloga();
        uloga3.setNazivUloge(ImenaUloga.PROFESOR);
        if(nemaUloga){
            ulogaRepozitorij.save(uloga3);
        }
        else if (!ulogaPostoji(sveUloge, uloga3)){
            ulogaRepozitorij.save(uloga3);
        }

        Uloga uloga4 = new Uloga();
        uloga4.setNazivUloge(ImenaUloga.ASISTENT);
        if(nemaUloga){
            ulogaRepozitorij.save(uloga4);
        }
        else if (!ulogaPostoji(sveUloge, uloga4)){
            ulogaRepozitorij.save(uloga4);
        }

        Uloga uloga5 = new Uloga();
        uloga5.setNazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        if(nemaUloga){
            ulogaRepozitorij.save(uloga5);
        }
        else if (!ulogaPostoji(sveUloge, uloga5)){
            ulogaRepozitorij.save(uloga5);
        }

    }

    private boolean privilegijaPostoji (List<Privilegija> svePrivilegije, Privilegija privilegija){
        boolean postoji = false;
        for(Privilegija p: svePrivilegije){
            if(p.getNazivPrivilegije().equals(privilegija.getNazivPrivilegije())){
                postoji = true;
                break;
            }
        }
        return  postoji;
    }

    private void dodajPrivilegije() {

        boolean nemaPrivilegija = false;
        List<Privilegija> svePrivilegije = new ArrayList<>();

        try{
            svePrivilegije = privilegijaRepozitorij.findAll();
        }
        catch (Exception e){
            nemaPrivilegija = true;
        }



        List<Uloga> uloga = new ArrayList<>();
        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        uloga.add(uloga1);

        Privilegija privilegija1 = new Privilegija();
        privilegija1.setNazivPrivilegije("editovanje-kreirane-zadace");
        privilegija1.setUloge(uloga);
        if(nemaPrivilegija){
            privilegijaRepozitorij.save(privilegija1);
        }
        else if(!privilegijaPostoji(svePrivilegije, privilegija1)){
            privilegijaRepozitorij.save(privilegija1);
        }

        List<Uloga> uloga2 = new ArrayList<>();
        Uloga uloga3 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        uloga2.add(uloga3);

        Privilegija privilegija2 = new Privilegija();
        privilegija2.setNazivPrivilegije("brisanje-kreirane-zadace");
        privilegija2.setUloge(uloga2);
        if(nemaPrivilegija){
            privilegijaRepozitorij.save(privilegija2);
        }
        else if(!privilegijaPostoji(svePrivilegije, privilegija2)){
            privilegijaRepozitorij.save(privilegija2);
        }

        List<Uloga> uloga4 = new ArrayList<>();
        Uloga uloga5 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        uloga4.add(uloga5);

        Privilegija privilegija3 = new Privilegija();
        privilegija3.setNazivPrivilegije("brisanje-korisnika");
        privilegija3.setUloge(uloga4);
        if(nemaPrivilegija){
            privilegijaRepozitorij.save(privilegija3);
        }
        else if(!privilegijaPostoji(svePrivilegije, privilegija3)){
            privilegijaRepozitorij.save(privilegija3);
        }


    }



}
