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
    public void testAdminImaPrivilegijuBrisanjaKorisnika() throws Exception {
        Odsjek odsjek = odsjekRepozitorij.findById(Long.valueOf(1)).get();
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        Korisnik korisnik = new Korisnik(Long.valueOf(2), odsjek, uloga, "Edina", "Kovac", new Date(1997, 2, 4), "0506997178963", "ekovac2@etf.unsa.ba", "Sarajevo", "Sarajevo", "BiH", "062589632", true, "Nakma Kovac", "Fudo Kovac", "Zupca", "ekovac2", "789456", "ekovac@linkedin.com", "ekovac@website.com", null, "17933", "1", "6", "admin");
        korisnikRepozitorij.save(korisnik);
        assertThat(korisnik.imaPrivilegiju("brisanje-korisnika")).isEqualTo(true);

    }

    @Test
    public void testAsistentImaPrivilegijuBrisanjaKreiraneZadace() throws Exception {
        Odsjek odsjek = odsjekRepozitorij.findById(Long.valueOf(1)).get();
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        Korisnik korisnik = new Korisnik(Long.valueOf(3), odsjek, uloga, "Damir", "Pozderac", new Date(1997, 2, 4), "0506997178963", "ekovac2@etf.unsa.ba", "Sarajevo", "Sarajevo", "BiH", "062589632", true, "Nakma Kovac", "Fudo Kovac", "Zupca", "ekovac2", "789456", "ekovac@linkedin.com", "ekovac@website.com", null, "17933", "1", "6", "admin");
        korisnikRepozitorij.save(korisnik);
        assertThat(korisnik.imaPrivilegiju("brisanje-kreirane-zadace")).isEqualTo(true);

    }

    @Test
    public void testProfesorImaPrivilegijuEditovanjaKreiraneZadace() throws Exception {
        Odsjek odsjek = odsjekRepozitorij.findById(Long.valueOf(1)).get();
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        Korisnik korisnik = new Korisnik(Long.valueOf(4), odsjek, uloga, "Ermin", "Sabotic", new Date(1997, 2, 4), "0506997178963", "ekovac2@etf.unsa.ba", "Sarajevo", "Sarajevo", "BiH", "062589632", true, "Nakma Kovac", "Fudo Kovac", "Zupca", "ekovac2", "789456", "ekovac@linkedin.com", "ekovac@website.com", null, "17933", "1", "6", "admin");
        korisnikRepozitorij.save(korisnik);
        assertThat(korisnik.imaPrivilegiju("editovanje-kreirane-zadace")).isEqualTo(true);

    }

    @Test
    public void testAsistentImaPrivilegijuPregledaKreiraneZadace() throws Exception {
        Odsjek odsjek = odsjekRepozitorij.findById(Long.valueOf(1)).get();
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        Korisnik korisnik = new Korisnik(Long.valueOf(4), odsjek, uloga, "Damir", "Pozderac", new Date(1997, 2, 4), "0506997178963", "ekovac2@etf.unsa.ba", "Sarajevo", "Sarajevo", "BiH", "062589632", true, "Nakma Kovac", "Fudo Kovac", "Zupca", "ekovac2", "789456", "ekovac@linkedin.com", "ekovac@website.com", null, "17933", "1", "6", "admin");
        korisnikRepozitorij.save(korisnik);
        assertThat(korisnik.imaPrivilegiju("pregled-zadace")).isEqualTo(true);

    }
}
