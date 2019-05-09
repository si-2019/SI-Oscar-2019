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
    public void testProfesorImaPrivilegijuEditovanjaKreiraneZadace() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("editovanje-kreirane-zadace")).isEqualTo(true);
    }

    @Test
    public void testProfesorNemaPrivilegijuBrisanjaKorisnika() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("brisanje-korisnika")).isEqualTo(false);
    }

    @Test
    public void testAsistentImaPrivilegijuBrisanjaKreiraneZadace() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("brisanje-kreirane-zadace")).isEqualTo(true);

    }

    @Test
    public void testAsistentNemaPrivilegijuBrisanjaKorisnika() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("brisanje-korisnika")).isEqualTo(false);

    }

    @Test
    public void testAdminImaPrivilegijuBrisanjaKorisnika() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        assertThat(uloga.imaPrivilegiju("brisanje-korisnika")).isEqualTo(true);
    }

    @Test
    public void testAdminNemaPrivilegijuBrisanjaKreiraneZadace() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        assertThat(uloga.imaPrivilegiju("brisanje-kreirane-zadace")).isEqualTo(false);
    }


}