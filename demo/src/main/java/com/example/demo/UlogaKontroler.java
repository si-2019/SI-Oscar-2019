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
	@RequestMapping(value = "/dajSveUloge", method = RequestMethod.GET)
	public List<String> getAllUloge() {
    List<Uloga> uloge = ulogaRepozitorij.findAll();
    List<String> povratna = new ArrayList<String>();
    for(Uloga u : uloge) {
        povratna.add(u.getNazivUloge().toString());
    }
    return povratna;
	}
}
