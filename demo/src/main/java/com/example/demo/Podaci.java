package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Podaci {
    private KorisnikRepozitorij korisnikRepozitorij;
    private UlogaRepozitorij ulogaRepozitorij;
    private PrivilegijaRepozitorij privilegijaRepozitorij;
    private OdsjekRepozitorij odsjekRepozitorij;

    @Autowired
    public Podaci(KorisnikRepozitorij korisnikRepozitorij, UlogaRepozitorij ulogaRepozitorij,
            PrivilegijaRepozitorij privilegijaRepozitorij,OdsjekRepozitorij odsjekRepozitorij) {
        this.korisnikRepozitorij = korisnikRepozitorij;
        this.ulogaRepozitorij = ulogaRepozitorij;
        this.privilegijaRepozitorij = privilegijaRepozitorij;
        this.odsjekRepozitorij=odsjekRepozitorij;
    }

    @EventListener
    public void dodajPodatke(ApplicationReadyEvent event) {
        dodajUloge();
        dodajPrivilegijeMahira();
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
     uloge.add(studentska_sluzba);
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
     


    }
}
