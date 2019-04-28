package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PrivilegijaTest {
    @Autowired
    private UlogaRepozitorij ulogaRepozitorij;
    @Autowired
    private KorisnikRepozitorij korisnikRepozitorij;
    @Autowired
    private OdsjekRepozitorij odsjekRepozitorij;

    @Test
	public void testAsistentImaMogucnostKreiranjaObavjestenja() {

        Long id=(long) (korisnikRepozitorij.count()+1);
        Korisnik korisnik = new Korisnik(id,odsjekRepozitorij.findBynazivOdsjeka("ri"),ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT), "Amila", "Borancic", new Date(1997,1 , 17), "0506997178963", "amila@unsa.ba", "Sarajevo", "KS", "BiH", "1234567", true, "Dzenana Borancic", "Borancic", "ISAKA SAMOKOVLIJE", "user", "pass", "amila@linkedin.com", "amila@website.com", null, "5281", "1", "6", "ASISTENT");
        korisnikRepozitorij.save(korisnik);
        assertEquals(true, (korisnik.imaPrivilegiju("kreiranje_obavjestenja")));
    }
 
 

}
