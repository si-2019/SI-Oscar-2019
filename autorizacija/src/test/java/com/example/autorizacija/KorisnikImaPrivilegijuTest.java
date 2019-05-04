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
        Korisnik korisnik = new Korisnik(id,odsjekRepozitorij.findBynazivOdsjeka("RI"),ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN), "Test", "N", new Date(1996,5, 16), "0202997178963", "Test6t@unsa.ba", "Sarajevo", "KS", "BiH", "1234567", true, "Kll", "Kll", "ISAKA SAMOKOVLIJE", "user", "pass", "Testt@linkedin.com", "Test6t@website.com", null, "5281", "1", "6", "Administrator");
        korisnikRepozitorij.save(korisnik);
        assertEquals(true, (korisnik.imaPrivilegiju("kreiranje-privilegija")));
    }



}