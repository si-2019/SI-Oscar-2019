package com.example.demo;

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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class KorisnikPrivilegija {

    @Autowired
    private KorisnikRepozitorij korisnikRepozitorij;

    @Autowired
    private OdsjekRepozitorij odsjekRepozitorij;

    @Autowired
    private UlogaRepozitorij ulogaRepozitorij;

    @Test
    public void testProfesorImaPrivilegijuKreiranjaNovihZadaca() throws Exception {
        Odsjek odsjek = odsjekRepozitorij.findById(Long.valueOf(1)).get();
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        Korisnik korisnik = new Korisnik(Long.valueOf(4), odsjek, uloga, "Nedim", "Panovic", new Date(1997, 2, 4),
                "0506997178963", "nedim2@etf.unsa.ba", "Sarajevo", "Sarajevo", "BiH", "062589632", true,
                "Nahida Manovic", "Fudo Manovic", "Zupca", "mkovac5", "789456", "nedim@linkedin.com",
                "nedim@website.com", null, "17933", "1", "6", "asistent");
        korisnikRepozitorij.save(korisnik);
        assertThat(korisnik.imaPrivilegiju("kreiranje_zadace")).isEqualTo(true);

    }
}