package com.example.autorizacija;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class KorisnikImaPrivilegijuTest {

    @Autowired
    private KorisnikRepozitorij korisnikRepozitorij;

    @Autowired
    private OdsjekRepozitorij odsjekRepozitorij;

    @Autowired
    private UlogaRepozitorij ulogaRepozitorij;

	@Test
	public void testStudentskaSluzbaImaMogucnostoPostavljanjaObavjestenja() {

        Long id=(long) (korisnikRepozitorij.count()+1);
        Korisnik korisnik = new Korisnik(id,odsjekRepozitorij.findBynazivOdsjeka("RI"),ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA), "Test2", "N2", new Date(1996,5, 16), "0806997178963", "Test2t@unsa.ba", "Sarajevo", "KS", "BiH", "1234567", true, "Kll", "Kll", "ISAKA SAMOKOVLIJE", "user", "pass", "Test2t@linkedin.com", "Test2t@website.com", null, "5281", "1", "6", "Studentska Sluzba");
        korisnikRepozitorij.save(korisnik);
        assertEquals(true, (korisnik.imaPrivilegiju("postavljanje-obavjestenja")));
    }
    

}