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
	public void testProfesorImaMogucnostZaObavjestenje() {

        Long id=(long) (korisnikRepozitorij.count()+1);
        Korisnik korisnik = new Korisnik(id,odsjekRepozitorij.findBynazivOdsjeka("RI"),ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT), "Test", "N", new Date(1996,5, 16), "1212997178963", "Testt@unsa.ba", "Sarajevo", "KS", "BiH", "1234567", true, "Kll", "Kll", "ISAKA SAMOKOVLIJE", "user", "pass", "Testt@linkedin.com", "Testt@website.com", null, "5281", "1", "6", "ASISTENT");
        korisnikRepozitorij.save(korisnik);
        assertEquals(true, (korisnik.imaPrivilegiju("kreiranje-teme-na-forumu")));
    }



}