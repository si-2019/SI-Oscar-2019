package com.example.demo;
import org.springframework.web.bind.annotation.RestController;

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
