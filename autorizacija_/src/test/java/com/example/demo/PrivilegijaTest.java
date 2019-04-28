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
	public void testProfesorImaMogucnostEditovanjaTerminaIspita() {

        Long id=(long) (korisnikRepozitorij.count()+1);
        Korisnik korisnik = new Korisnik(id,odsjekRepozitorij.findBynazivOdsjeka("ri"),ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR), "Vedran", "Simunovic", new Date(1993,8, 22), "0506997178963", "vedran@unsa.ba", "Konjic", "HNK", "BiH", "1234567", true, "Lejla", "Ivica", "Kolonija", "user", "pass", "vedran@linkedin.com", "vedran@website.com", null, "5281", "1", "6", "PROFESOR");
        korisnikRepozitorij.save(korisnik);
        assertEquals(true, (korisnik.imaPrivilegiju("editovanje_termina_ispita")));
    }



}