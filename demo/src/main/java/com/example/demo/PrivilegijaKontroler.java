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
}
