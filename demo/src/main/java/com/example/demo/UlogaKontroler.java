package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UlogaKontroler {

    private UlogaRepozitorij ulogaRepozitorij;


    @Autowired
    public UlogaKontroler(UlogaRepozitorij ulogaRepozitorij) {
        this.ulogaRepozitorij = ulogaRepozitorij;
    }

    @RequestMapping(value = "/{idUloge}/{privilegija}", method = RequestMethod.GET)
    public boolean ulogaImaPrivilegiju(@PathVariable Long idUloge, @PathVariable String privilegija) {
        if(ulogaRepozitorij.findById(idUloge).equals(Optional.empty())) {
            return false;
        }
        return ulogaRepozitorij.findById(idUloge).get().imaPrivilegiju(privilegija);
    }

    @RequestMapping(value = "/uloge", method = RequestMethod.GET)
    public List<String> getAllUloge() {
        List<Uloga> uloge = ulogaRepozitorij.findAll();
        List<String> povratna = new ArrayList<String>();
        for(Uloga u : uloge) {
            povratna.add(u.getNazivUloge().toString());
        }
        return povratna;
    }

    @RequestMapping(value = "/{idUloge}/privilegije", method = RequestMethod.GET)
    public List<String> privilegijeUloge (@PathVariable Long idUloge) {
        List<Privilegija> privilegije = ulogaRepozitorij.findById(idUloge).get().getPrivilegije();
        List<String> povratna = new ArrayList<String>();
        for (Privilegija p : privilegije){
            povratna.add(p.getNazivPrivilegije());
        }
        return povratna;
    }
}
