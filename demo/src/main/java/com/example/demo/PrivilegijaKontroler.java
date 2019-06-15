package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RestController
@RequestMapping(value = "/privilegije")
public class PrivilegijaKontroler {

    private PrivilegijaRepozitorij privilegijaRepozitorij;
    private UlogaRepozitorij ulogaRepozitorij;

    @Autowired
    public PrivilegijaKontroler(PrivilegijaRepozitorij privilegijaRepozitorij, UlogaRepozitorij ulogaRepozitorij) {
        this.privilegijaRepozitorij = privilegijaRepozitorij;
        this.ulogaRepozitorij = ulogaRepozitorij;
    }

    private List<String> dajVrijednostiZaKljuc(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject) jsonArray.get(index)).optString(key)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/obrisi/{privilegija}", method = RequestMethod.DELETE)
    public String obrisiPrivilegiju(@PathVariable String privilegija){
        privilegija=privilegija.toLowerCase();
        if(privilegijaRepozitorij.findBynazivPrivilegije(privilegija).equals(Optional.empty())){
            return "Privilegija ne postoji!";
         }

        else{
            privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije(privilegija).getId());
            return "Privilegija je uspjesno obrisana!";
        }
    }
    
    @RequestMapping(value = "/obrisiId/{idPrivilegija}", method = RequestMethod.DELETE)
    public String obrisiPrivilegijuPoId(@PathVariable Long idPrivilegija){
        if (privilegijaRepozitorij.findById(idPrivilegija).equals(Optional.empty())) {
            return "Privilegija ne postoji!";
        }
        else {
            privilegijaRepozitorij.deleteById(idPrivilegija);
            return "Privilegija je uspjesno obrisana!";
        }
    }
    

    @RequestMapping(value = "/obrisiSvePrivilegije", method = RequestMethod.DELETE)
    public String obrisiSvePrivilegije() {
        privilegijaRepozitorij.deleteAll();
        return "Sve privilegije su uspjesno obrisane!";
    }

    @RequestMapping(value = "/obrisiPrivilegije", method = RequestMethod.DELETE)
    public String obrisiPrivilegije(@RequestBody String nizPrivilegija) {
        List<String> privilegije = dajVrijednostiZaKljuc(nizPrivilegija, "naziv");
        int brojac = 0;
        String povratni = "Uspjesno su obrisane privilegije: \n";
        for(String privilegija : privilegije) {
            if(privilegijaRepozitorij.existsBynazivPrivilegije(privilegija.toLowerCase())) {
                brojac++;
                privilegijaRepozitorij.deleteById(privilegijaRepozitorij.findBynazivPrivilegije(privilegija.toLowerCase()).getId());
                povratni += (privilegija.toLowerCase() + '\n');
            }
        }
        if(brojac != 0) return povratni;
        else return "Niti jedna od navedenih privilegija ne postoji u bazi!";
    }

    @RequestMapping(value = "/dodajPrivilegiju/{privilegija}", method = RequestMethod.POST)
    public String dodajPrivilegiju(@PathVariable String privilegija) {
        if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija.toLowerCase())) {
            Privilegija privilegijaNova = new Privilegija();
            privilegijaNova.setNazivPrivilegije(privilegija.toLowerCase());
            privilegijaRepozitorij.save(privilegijaNova);
            return "Uspjesno dodana privilegija " + privilegija.toLowerCase() + "!";
        }
        else {
            return "Privilegija vec postoji u sistemu!";
        }
    }

    @RequestMapping(value = "/dodajPrivilegije", method = RequestMethod.POST)
    public String dodajPrivilegije(@RequestBody String nizPrivilegija) {
        List<String> privilegije = dajVrijednostiZaKljuc(nizPrivilegija, "naziv");
        int brojac = 0;
        String povratni = "Uspjesno su dodane privilegije: \n";
        for(String privilegija : privilegije) {
            if(!privilegijaRepozitorij.existsBynazivPrivilegije(privilegija.toLowerCase())) {
                brojac++;
                Privilegija privilegijaNova = new Privilegija();
                privilegijaNova.setNazivPrivilegije(privilegija.toLowerCase());
                privilegijaRepozitorij.save(privilegijaNova);
                povratni += (privilegija.toLowerCase() + '\n');
            }
        }
        if(brojac != 0) return povratni;
        else return "Sve privilegije vec postoje u bazi!";
    }

    @RequestMapping(value = "/poveziUloguPrivilegijuPoNazivu/{nazivUloge}/{privilegija}", method = RequestMethod.PUT)
    public String poveziUloguPrivilegijuId(@PathVariable String nazivUloge, @PathVariable String privilegija) {
        UlogaKontroler uk = new UlogaKontroler(ulogaRepozitorij, privilegijaRepozitorij);
        int indeks = uk.provjeriPostojanjeUloge(nazivUloge);
        privilegija = privilegija.toLowerCase();
        if(indeks != -1 && privilegijaRepozitorij.existsBynazivPrivilegije(privilegija)) {
            ImenaUloga[] niz = ImenaUloga.values();
            Uloga u = ulogaRepozitorij.findById(ulogaRepozitorij.findBynazivUloge(niz[indeks]).getId()).get();
            Privilegija p = privilegijaRepozitorij.findById(privilegijaRepozitorij.findBynazivPrivilegije(privilegija).getId()).get();
            Podaci pod = new Podaci(ulogaRepozitorij, privilegijaRepozitorij);
            pod.poveziUloguPrivilegiju(p, u);
            return "Uloga i privilegija su povezane!";
        }
        return "Specificirana uloga ili privilegija ne postoje!";
    }

    @RequestMapping(value = "/editujPrivilegiju/{stara}/{nova}", method = RequestMethod.PUT)
    public String editujPrivilegiju(@PathVariable String stara, @PathVariable String nova) {
        stara = stara.toLowerCase();
        nova = nova.toLowerCase();
        if(privilegijaRepozitorij.existsBynazivPrivilegije(stara) && !privilegijaRepozitorij.existsBynazivPrivilegije(nova)) {
            Long idPrivilegije = privilegijaRepozitorij.findBynazivPrivilegije(stara).getId();
            Privilegija p = privilegijaRepozitorij.findById(idPrivilegije).get();
            p.setNazivPrivilegije(nova);
            privilegijaRepozitorij.save(p);
            return "Privilegija uspjesno editovana!";
        }
        else if(privilegijaRepozitorij.existsBynazivPrivilegije(nova)) {
            return "Privilegija sa nazivom koji zelite postaviti vec postoji!";
        }
        else {
            return "Privilegija koju zelite editovati ne postoji u sistemu!";
        }
    }

    @RequestMapping(value = "/editujPrivilegije", method = RequestMethod.PUT)
    public String editujPrivilegije(@RequestBody String nizPrivilegija) {
        List<String> starePrivilegije = dajVrijednostiZaKljuc(nizPrivilegija, "nazivStare");
        List<String> novePrivilegije = dajVrijednostiZaKljuc(nizPrivilegija, "nazivNove");
        int brojac = 0, indeks = 0;
        String povratni = "Uspjesno su editovane privilegije: \n";
        for(String privilegija : starePrivilegije) {
            privilegija = privilegija.toLowerCase();
            novePrivilegije.set(indeks, novePrivilegije.get(indeks).toLowerCase());
            if(privilegijaRepozitorij.existsBynazivPrivilegije(privilegija) && !privilegijaRepozitorij.existsBynazivPrivilegije(novePrivilegije.get(indeks))) {
                Long idPrivilegije = privilegijaRepozitorij.findBynazivPrivilegije(privilegija).getId();
                Privilegija p = privilegijaRepozitorij.findById(idPrivilegije).get();
                p.setNazivPrivilegije(novePrivilegije.get(indeks));
                privilegijaRepozitorij.save(p);
                brojac++;
                povratni += (privilegija + '\n');
            }
            indeks++;
        }
        if(brojac != 0) return povratni;
        else return "Niti jedna od privilegija koju zelite editovati ne postoji u sistemu!";
    }

}