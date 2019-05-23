package com.example.demo;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import java.util.List;
 

@RestController
public class PrivilegijaKontroler {

  private PrivilegijaRepozitorij privilegijaRepozitorij;

    @Autowired
    public PrivilegijaKontroler(PrivilegijaRepozitorij privilegijaRepozitorij) {
        this.privilegijaRepozitorij = privilegijaRepozitorij;
    }

    @RequestMapping(value = "/privilegije", method = RequestMethod.GET)
    public List<String> getAllPrivilegije() {
        List<Privilegija> privilegije = privilegijaRepozitorij.findAll();
        List<String> nazivi = new ArrayList<String>();
        for(Privilegija p : privilegije) {
            nazivi.add(p.getNazivPrivilegije());
        }
        return nazivi;
    }
}
