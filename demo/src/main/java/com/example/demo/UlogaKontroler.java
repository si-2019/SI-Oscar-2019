package com.example.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value = "/uloga")
public class UlogaKontroler {
    private UlogaRepozitorij ulogaRepozitorij;
    private PrivilegijaRepozitorij privilegijaRepozitorij;

    @Autowired
    public UlogaKontroler(UlogaRepozitorij ulogaRepozitorij, PrivilegijaRepozitorij privilegijaRepozitorij) {
        this.privilegijaRepozitorij = privilegijaRepozitorij;
        this.ulogaRepozitorij = ulogaRepozitorij;
    }

    private List<String> dajVrijednostiZaKljuc(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
    }

    int provjeriPostojanjeUloge(String nazivUloge) {
        ImenaUloga[] niz = ImenaUloga.values();
        boolean ima = false;
        int indeks = 0;
        for(ImenaUloga imeUloge : niz) {
            if(nazivUloge.toLowerCase().equals(imeUloge.toString().toLowerCase())) {
                ima = true;
                break;
            }
            indeks++;
        }
        if(ima) return indeks;
        return -1;
    }

    @RequestMapping(value = "/pretragaId/{idUloge}/{idPrivilegije}", method = RequestMethod.GET)
    public boolean ulogaIdImaPrivilegiju(@PathVariable Long idUloge, @PathVariable Long idPrivilegije) {
        if(!ulogaRepozitorij.findById(idUloge).isPresent() || !privilegijaRepozitorij.findById(idPrivilegije).isPresent()) {
            return false;
        }
        return ulogaRepozitorij.findById(idUloge).get().imaPrivilegiju(privilegijaRepozitorij.findById(idPrivilegije).get().getNazivPrivilegije());
    }

    @RequestMapping(value = "/pretragaNaziv/{naziv}/{privilegija}", method = RequestMethod.GET)
    public boolean ulogaNazivImaPrivilegiju(@PathVariable String naziv, @PathVariable String privilegija) {
        ImenaUloga[] niz = ImenaUloga.values();
        int indeks = provjeriPostojanjeUloge(naziv);
        if(indeks != -1) {
            privilegija = privilegija.toLowerCase();
            return ulogaRepozitorij.findBynazivUloge(niz[indeks]).imaPrivilegiju(privilegija);
        }
        return false;
    }

    @RequestMapping(value = "/obrisiId/{idUloge}", method = RequestMethod.DELETE)
    public String obrisiUlogu(@PathVariable Long idUloge) {
        if(ulogaRepozitorij.findById(idUloge).equals(Optional.empty())) {
            return "Uloga ne postoji!";
        }
        else {
            ulogaRepozitorij.deleteById(idUloge);
            return "Uloga je uspjesno obrisana!";
        }
    }
}
