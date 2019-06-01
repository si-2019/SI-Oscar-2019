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
    public void dodajPodatke(ApplicationReadyEvent event) {
        dodajUloge();
        privilegije();
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


    private void privilegije() {

        Uloga profesor=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        Uloga asistent=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        Uloga admin=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        Uloga studentska_sluzba=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        Uloga student=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);

        int counter=0;
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


    @EventListener
    public void dodaj (ApplicationReadyEvent event){
        dodajUloge();
        dodajPrivilegije();
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
      
      uloga = new ArrayList<>();
        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        uloga.add(uloga1);
        int counter=0;
        

        Privilegija privilegija1 = new Privilegija();
        privilegija1.setNazivPrivilegije("kreiranje-zadace");
        privilegija1.setUloge(uloga);


        if (privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-zadace") == null){
            privilegijaRepozitorij.save(privilegija1);
        }
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-zadace").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
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


        if (privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-korisnika") == null){
            privilegijaRepozitorij.save(privilegija2);
        }
        
         else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-korisnika").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
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


        if (privilegijaRepozitorij.findBynazivPrivilegije("brisanje-termina-ispita") == null){
            privilegijaRepozitorij.save(privilegija3);
        }
         else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("brisanje-termina-ispita").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
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
        if (privilegijaRepozitorij.findBynazivPrivilegije("izmjena-dodijeljenih-bodova") == null){
            privilegijaRepozitorij.save(privilegija4);
        }
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("izmjena-dodijeljenih-bodova").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("izmjena-dodijeljenih-bodova").getId());
                privilegija1.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija4);
            }
        }
        Privilegija privilegija5 = new Privilegija();
        privilegija5.setNazivPrivilegije("izmjena-kviza");
        privilegija5.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("izmjena-kviza") == null){
            privilegijaRepozitorij.save(privilegija5);
        }
         else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("izmjena-kviza").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
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


        if (privilegijaRepozitorij.findBynazivPrivilegije("izmjena-bodova-za-zadace") == null){

            privilegijaRepozitorij.save(privilegija6);
        }
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("izmjena-bodova-za-zadace").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
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
        if (privilegijaRepozitorij.findBynazivPrivilegije("povezivanje-privilegija-uloga") == null){
            privilegijaRepozitorij.save(privilegija7);
        }
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("povezivanje-privilegija-uloga").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
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
        if (privilegijaRepozitorij.findBynazivPrivilegije("evidencija-prijavljenih-studenata") == null){
            privilegijaRepozitorij.save(privilegija8);
        }
        else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("evidencija-prijavljenih-studenata").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("evidencija-prijavljenih-studenata").getId());
                privilegija8.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija8);
            }
        }


        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija9 = new Privilegija();
        privilegija9.setNazivPrivilegije("brisanje-teme");
        privilegija9.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("brisanje-teme") == null){
            privilegijaRepozitorij.save(privilegija9);
        }
          else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("brisanje-teme").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
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
        if (privilegijaRepozitorij.findBynazivPrivilegije("unos-rezultata") == null){
            privilegijaRepozitorij.save(privilegija10);
        }
   else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("unos-rezultata").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
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
        if (privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-grupa") == null){
            privilegijaRepozitorij.save(privilegija11);
        }
         else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-grupa").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-grupa").getId());
                privilegija11.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija11);
            }
        }
        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija12 = new Privilegija();
        privilegija12.setNazivPrivilegije("pristup-grupama");
        privilegija12.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pristup-grupama") == null){
            privilegijaRepozitorij.save(privilegija12);
        }

          else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pristup-grupama").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pristup-grupama").getId());
                privilegija12.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija12);
            }
        }
        uloga.clear();
        uloga.add(uloga1);
        Privilegija privilegija13 = new Privilegija();
        privilegija13.setNazivPrivilegije("pregled-komentara");
        privilegija13.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pregled-komentara") == null){
            privilegijaRepozitorij.save(privilegija13);
        }
          else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pregled-komentara").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga1);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pregled-komentara").getId());
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
        if (privilegijaRepozitorij.findBynazivPrivilegije("pristup-informacijama") == null){
            privilegijaRepozitorij.save(privilegija14);
        
        }
          else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pristup-informacijama").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga4);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pristup-informacijama").getId());
                privilegija14.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija14);
            }
        }

        uloga.clear();
        uloga.add(uloga4);
        Privilegija privilegija15 = new Privilegija();
        privilegija15.setNazivPrivilegije("pregled-termina");
        privilegija15.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pregled-termina") == null){
            privilegijaRepozitorij.save(privilegija15);
        
        }
          else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pregled-termina").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga4);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pregled-termina").getId());
                privilegija15.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija15);
            }
        }
        uloga.clear();
        uloga.add(uloga4);
        Privilegija privilegija16 = new Privilegija();
        privilegija16.setNazivPrivilegije("pregled-anketa");
        privilegija16.setUloge(uloga);
        if (privilegijaRepozitorij.findBynazivPrivilegije("pregled-anketa") == null){
            privilegijaRepozitorij.save(privilegija16);
        
        }
          else{
            List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("pregled-anketa").getUloge();
            boolean treba_dodati=true;
            for(Uloga u: noveUloge){
                for(Uloga u1:uloge){
                    if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
                }
            }
            if(treba_dodati){
                noveUloge.add(uloga4);
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("pregled-anketa").getId());
                privilegija15.setUloge(noveUloge);
                privilegijaRepozitorij.save(privilegija16);
            }
        }

    }
    uloga.clear();
    uloga.add(uloga3);
    Privilegija privilegija17 = new Privilegija();
    privilegija17.setNazivPrivilegije("ostavljanje-komentara");
    privilegija17.setUloge(uloga);
    if (privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara") == null){
        privilegijaRepozitorij.save(privilegija17);
    
    }
      else{
        List<Uloga> noveUloge = privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara").getUloge();
        boolean treba_dodati=true;
        for(Uloga u: noveUloge){
            for(Uloga u1:uloge){
                if(u.getNazivUloge.equals(u1.getNazivUloge())) treba_dodati=false;
            }
        }
        if(treba_dodati){
            noveUloge.add(uloga3);
            privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije("ostavljanje-komentara").getId());
            privilegija15.setUloge(noveUloge);
            privilegijaRepozitorij.save(privilegija17);
        }
    }

}

    private void dodajOdsjek() {

        if ((odsjekRepozitorij.findBynazivOdsjeka("RI")) == null) {
            Odsjek odsjek1 = new Odsjek();

            odsjek1.setNazivOdsjeka("RI");
            odsjekRepozitorij.save(odsjek1);

        }

       


    }
}
