package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void testAsistentImaPrivilegijuPregledaKreiraneZadace() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("pregled-zadace")).isEqualTo(true);
    }

    @Test
    public void testAsistentNemaPrivilegijuKreiranjaKorisnika() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("kreiranje-korisnika")).isEqualTo(false);
    }

    @Test
    public void testProfesorImaPrivilegijuEditovanjaObavjestenja() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("editovanje-obavjestenja")).isEqualTo(true);
    }

    @Test
    public void testProfesorNemaPrivilegijuKreiranjaKorisnika() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("kreiranje-korisnika")).isEqualTo(false);

    }

    @Test
    public void testProfesorImaPrivilegijuEditovanjaTemeNaForumu() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("editovanje-teme-na-forumu")).isEqualTo(true);
    }

    @Test
    public void testProfesorNemaPrivilegijuEditovanjeKorisnika() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("editovanje-korisnika")).isEqualTo(false);
    }

    @Test
    public void testProfesorImaPrivilegijuPregledaKreiraneZadace() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("pregled-zadace")).isEqualTo(true);
    }

    @Test
    public void testProfesorNemaPrivilegijuDodavanjaPrivilegija() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("dodaj-privilegiju")).isEqualTo(false);
    }

    @Test
    public void testAdminImaPrivilegijuObavjestavanjaKorisnikaSistema() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        assertThat(uloga.imaPrivilegiju("obavjestavanje-korisnika-sistema")).isEqualTo(true);
    }

    @Test
    public void testAdminNemaPrivilegijuEditovanjaTemeNaForumu() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        assertThat(uloga.imaPrivilegiju("editovanje-teme-na-forumu")).isEqualTo(false);
    }

    @Test
    public void testAsistentImaPrivilegijuPojedinacneEvidencijePrisustva() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("pojedinacna-evidencija-prisustva")).isEqualTo(true);
    }

    @Test
    public void testAsistentNemaPrivilegijuDodavanjaPrivilegije() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("dodaj-privilegiju")).isEqualTo(false);
    }

    @Test
    public void testProfesorImaPrivilegijuOstavljanjaKomentaraNaRadStudenata() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("ostavljanje-komentara-na-rad-studenata")).isEqualTo(true);
    }

    @Test
    public void testProfesorNemaPrivilegijuDodavanjaUloge() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("dodavanje-uloge")).isEqualTo(false);
    }

    @Test
    public void testProfesorImaPrivilegijuPreuzimanjaSvihZadaca() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("preuzimanje-svih-zadaca")).isEqualTo(true);
    }

    @Test
    public void testProfesorNemaPrivilegijuPovezivanjaUlogeSaPrivilegijom() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("povezivanje-uloge-sa-privilegijom")).isEqualTo(false);
    }

    @Test
    public void testProfesorImaPrivilegijuUvidaURezultateAnketa() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("uvid-u-rezultate-anketa")).isEqualTo(true);
    }

    @Test
    public void testProfesorNemaPrivilegijuPregledaSvihKorisnikaSistema() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("pregled-svih-korisnika-sistema")).isEqualTo(false);
    }

    @Test
    public void testProfesorImaPrivilegijuUredjivanjaPodatakaZaPredmet() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("uredjivanje-podataka-za-predmet")).isEqualTo(true);
    }

    @Test
    public void testProfesorNemaPrivilegijuDizanjaBama() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("dizanje-bana")).isEqualTo(false);
    }

    @Test
    public void testAsistentImaPrivilegijuUvidaURezultateAnketa() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("uvid-u-rezultate-anketa")).isEqualTo(true);
    }

    @Test
    public void testAsistentNemaPrivilegijuPregledaSvihKorisnikaSistema() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("pregled-svih-korisnika-sistema")).isEqualTo(false);
    }

    @Test
    public void testAsistentImaPrivilegijuOstavljanjaKomentaraNaRadStudenata() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("ostavljanje-komentara-na-rad-studenata")).isEqualTo(true);
    }

}
