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

        List<Uloga> uloga = new ArrayList<>();
        Uloga uloga1 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        uloga.add(uloga1);

        Privilegija privilegija1 = new Privilegija();
        privilegija1.setNazivPrivilegije("brisanje-kreirane-zadace");
        privilegija1.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-kreirane-zadace")==null) privilegijaRepozitorij.save(privilegija1);
        
        Privilegija privilegija2 = new Privilegija();
        privilegija2.setNazivPrivilegije("registrovanje-casa");
        privilegija2.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("registrovanje-casa")==null) privilegijaRepozitorij.save(privilegija2);

        Privilegija privilegija3 = new Privilegija();
        privilegija3.setNazivPrivilegije("editovanje-termina-ispita");
        privilegija3.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-termina-ispita")==null) privilegijaRepozitorij.save(privilegija3);

        uloga = new ArrayList<>();
        Uloga uloga2 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        uloga.add(uloga2);
        Privilegija privilegija4 = new Privilegija();
        privilegija4.setNazivPrivilegije("brisanje-teme");
        privilegija4.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-teme")==null) privilegijaRepozitorij.save(privilegija4);

        Privilegija privilegija5 = new Privilegija();
        privilegija5.setNazivPrivilegije("kreiranje-obavjestenja");
        privilegija5.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-obavjestenja")==null) privilegijaRepozitorij.save(privilegija5);
        
        Privilegija p=privilegijaRepozitorij.findBynazivPrivilegije("kreiranje-obavjestenja");
        uloga.add(uloga1);        
        p.setUloge(uloga);
        privilegijaRepozitorij.save(p);

        uloga=new ArrayList<>();
        uloga.add(uloga2);
        Privilegija privilegija6 = new Privilegija();
        privilegija6.setNazivPrivilegije("brisanje-obavjestenja");
        privilegija6.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("brisanje-obavjestenja")==null) privilegijaRepozitorij.save(privilegija6);

        p=privilegijaRepozitorij.findBynazivPrivilegije("brisanje-obavjestenja");
        uloga.add(uloga1);        
        p.setUloge(uloga);
        privilegijaRepozitorij.save(p);

        uloga=new ArrayList<>();
        Uloga uloga3=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        uloga.add(uloga3);
        Privilegija privilegija7=new Privilegija();
        privilegija7.setNazivPrivilegije("editovanje-postavljenih-obavjestenja");
        privilegija7.setUloge(uloga);
        if(privilegijaRepozitorij.findBynazivPrivilegije("editovanje-postavljenih-obavjestenja")==null) privilegijaRepozitorij.save(privilegija7);

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