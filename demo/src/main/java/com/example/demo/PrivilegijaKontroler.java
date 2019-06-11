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
	@RequestMapping(value = "/dajSvePrivilegije", method = RequestMethod.GET)
	public List<String> dajSvePrivilegije() {
    List<Privilegija> privilegije = privilegijaRepozitorij.findAll();
    List<String> nazivi = new ArrayList<String>();
    for (Privilegija p : privilegije) {
        nazivi.add(p.getNazivPrivilegije());
    }
    return nazivi;
	}
}
