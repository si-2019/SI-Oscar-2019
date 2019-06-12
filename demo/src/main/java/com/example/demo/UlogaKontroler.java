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

    private int provjeriPostojanjeUloge(String nazivUloge) {
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

    @RequestMapping(value = "/{idUloge}/privilegije", method = RequestMethod.GET)
    public List<String> privilegijeUloge(@PathVariable Long idUloge) {
        List<Privilegija> privilegije = new ArrayList<Privilegija>();
        List<String> povratna = new ArrayList<String>();
        if(ulogaRepozitorij.findById(idUloge).isPresent()) {
            privilegije = ulogaRepozitorij.findById(idUloge).get().getPrivilegije();
            for (Privilegija p : privilegije) {
                povratna.add(p.getNazivPrivilegije());
            }
        }
        return povratna;
    }

    @RequestMapping(value = "/vratiPoNazivu/{naziv}/privilegije", method = RequestMethod.GET)
    public List<String> privilegijeUlogeNaziv(@PathVariable String naziv) {
        List<Privilegija> privilegije = new ArrayList<>();
        List<String> povratna = new ArrayList<>();
        ImenaUloga[] niz = ImenaUloga.values();
        int indeks = provjeriPostojanjeUloge(naziv);
        if(indeks != -1 && ulogaRepozitorij.existsBynazivUloge(niz[indeks])) {
            privilegije = ulogaRepozitorij.findBynazivUloge(niz[indeks]).getPrivilegije();
            for(Privilegija p : privilegije) {
                povratna.add(p.getNazivPrivilegije());
            }
        }
        return povratna;
    }
}
