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
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
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
}
