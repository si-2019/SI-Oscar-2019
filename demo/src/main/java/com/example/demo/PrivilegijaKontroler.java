package com.example.demo;

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

    @Autowired
    public PrivilegijaKontroler(PrivilegijaRepozitorij privilegijaRepozitorij) {
        this.privilegijaRepozitorij = privilegijaRepozitorij;
    }

    private List<String> dajVrijednostiZaKljuc(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
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
}
