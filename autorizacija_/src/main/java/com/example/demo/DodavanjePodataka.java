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

        
        Uloga profesor=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        Uloga asistent=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        Uloga admin=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        Uloga studentska_sluzba=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        Uloga student=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);

        boolean treba_dodati=true;
        List<Uloga> uloge = new ArrayList<>();
        List<Uloga> nove_uloge = new ArrayList<>();
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
                        treba_dodati=false;
                    }
                }
            }
            if(treba_dodati){
                nove_uloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-kreirane-zadace").getId());
                brisanje_kreirane_zadace.setUloge(nove_uloge);
                privilegijaRepozitorij.save(brisanje_kreirane_zadace);

            }
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        treba_dodati=true;

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
                        treba_dodati=false;
                    }
                }
            }
            if(treba_dodati){
                nove_uloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("registrovanje-casa").getId());
                registrovanje_casa.setUloge(nove_uloge);
                privilegijaRepozitorij.save(registrovanje_casa);

            }
            
        }

        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
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
                        treba_dodati=false;
                    }
                }
            }
            if(treba_dodati){
                nove_uloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-termina-ispita").getId());
                editovanje_termina_ispita.setUloge(nove_uloge);
                privilegijaRepozitorij.save(editovanje_termina_ispita);

            }
            
        }
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
                        treba_dodati=false;
                    }
                }
            }
            if(treba_dodati){
                nove_uloge.add(asistent);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-teme").getId());
                brisanje_teme.setUloge(nove_uloge);
                privilegijaRepozitorij.save(brisanje_teme);

            }
            
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
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
                        treba_dodati=false;
                    }
                }
            }
            if(treba_dodati){
                nove_uloge.add(asistent);
                nove_uloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-obavjestenja").getId());
                kreiranje_obavjestenja.setUloge(nove_uloge);
                privilegijaRepozitorij.save(kreiranje_obavjestenja);

            }
            
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        treba_dodati=true;

        Privilegija brisanje_obavjestenja=new Privilegija();
        uloge.add(asistent);
        uloge.add(profesor);
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
                        treba_dodati=false;
                    }
                }
            }
            if(treba_dodati){
                nove_uloge.add(asistent);
                nove_uloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-obavjestenja").getId());
                brisanje_obavjestenja.setUloge(nove_uloge);
                privilegijaRepozitorij.save(brisanje_obavjestenja);

            }
            
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
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
                        treba_dodati=false;
                    }
                }
            }
            if(treba_dodati){
                nove_uloge.add(admin);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-postavljenih-obavjestenja").getId());
                editovanje_postavljenih_obavjestenja.setUloge(nove_uloge);
                privilegijaRepozitorij.save(editovanje_postavljenih_obavjestenja);

            }
            
        }
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
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
                        treba_dodati=false;
                    }
                }
            }
            if(treba_dodati){
                nove_uloge.add(profesor);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("unosenje-bodova-ispita").getId());
                unosenje_bodova_ispita.setUloge(nove_uloge);
                privilegijaRepozitorij.save(unosenje_bodova_ispita);
            }
            
    }


        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
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
        treba_dodati=false;
        }
        }
        }
        if(treba_dodati){
        nove_uloge.add(profesor);
        privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara-na-zadace").getId());
        ostavljanje_komentara_na_zadace.setUloge(nove_uloge);
        privilegijaRepozitorij.save(ostavljanje_komentara_na_zadace);
        }
        }        
        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
        treba_dodati=true;

        Privilegija p=privilegijaRepozitorij.findBynazivPrivilegije("brisanje-obavjestenja");
        uloge=nove_uloge;
        nove_uloge.add(studentska_sluzba);
        for(Uloga u:nove_uloge){
            for(Uloga u1:uloge){
            if(u.getNazivUloge()==u1.getNazivUloge()){
            treba_dodati=false;
            }
        }
        }
            if(treba_dodati){
            p.setUloge(nove_uloge);
            privilegijaRepozitorij.save(p);
         }

        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
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
        treba_dodati=false;
        }
        }
        }
        if(treba_dodati){
        nove_uloge.add(asistent);
        privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-komentara").getId());
        editovanje_komentara.setUloge(nove_uloge);
        privilegijaRepozitorij.save(editovanje_komentara);
        }
        }    

        uloge = new ArrayList<>();
        nove_uloge = new ArrayList<>();
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
        treba_dodati=false;
        }
        }
        }
        if(treba_dodati){
        nove_uloge.add(student);
        privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("prikaz-kalendara").getId());
        prikaz_kalendara.setUloge(nove_uloge);
        privilegijaRepozitorij.save(prikaz_kalendara);
        }
        } 
}
       

     private void dodajOdsjek(){ 

        if((odsjekRepozitorij.findBynazivOdsjeka("RI"))==null){ 
            Odsjek odsjek1=new Odsjek();
            odsjek1.setNazivOdsjeka("RI"); odsjekRepozitorij.save(odsjek1); 
        } 
        if((odsjekRepozitorij.findBynazivOdsjeka("TK"))==null){ 
            Odsjek odsjek1=new Odsjek();
            odsjek1.setNazivOdsjeka("TK"); odsjekRepozitorij.save(odsjek1); 
        }
        if((odsjekRepozitorij.findBynazivOdsjeka("AIE"))==null){ 
            Odsjek odsjek1=new Odsjek();
            odsjek1.setNazivOdsjeka("AIE"); odsjekRepozitorij.save(odsjek1); 
        }
        if((odsjekRepozitorij.findBynazivOdsjeka("EE"))==null){ 
            Odsjek odsjek1=new Odsjek();
            odsjek1.setNazivOdsjeka("EE"); odsjekRepozitorij.save(odsjek1); 
        }
    }
}