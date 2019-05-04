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
	public void testProfesorImaMogucnostRegistrovanjaCasa() {

        Long id=(long) (korisnikRepozitorij.count()+1);
        Korisnik korisnik = new Korisnik(id,odsjekRepozitorij.findBynazivOdsjeka("RI"),ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR), "Lejla", "Cardzic", new Date(1996,8, 22), "0506997178963", "lejla@unsa.ba", "Konjic", "HNK", "BiH", "1234567", true, "Nermina", "Almir", "Kolonija", "user", "pass", "lejla@linkedin.com", "lejla@website.com", null, "5281", "1", "6", "PROFESOR");
        korisnikRepozitorij.save(korisnik);
        assertEquals(true, (korisnik.imaPrivilegiju("registrovanje_casa")));
    }



}