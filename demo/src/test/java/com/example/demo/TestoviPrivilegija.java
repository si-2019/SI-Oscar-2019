package com.example.demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.nio.charset.Charset;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

public class TestoviPrivilegija {
    @Autowired
    private KorisnikRepozitorij korisnikRepozitorij;

    @Autowired
    private OdsjekRepozitorij odsjekRepozitorij;

    @Autowired
    private UlogaRepozitorij ulogaRepozitorij;

    @Autowired
    private PrivilegijaRepozitorij privilegijaRepozitorij;

    @Test
    public void testPostojiRegistrovanjeCasa() throws Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("registrovanje-casa")).isEqualTo(true);
    }

    @Test
    public void testAsistentRegistrovanjeCasa() throws Exception {
        Uloga asistent = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(asistent.imaPrivilegiju("registrovanje-casa")).isEqualTo(true);
    }

    @Test
    public void testStudentRegistrovanjeCasa() throws Exception {
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(student.imaPrivilegiju("registrovanje-casa")).isEqualTo(false);
    }

    @Test
    public void testPostojiEditovanjeZadace() throws Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-kreirane-zadace")).isEqualTo(true);
    }

    @Test
    public void testAsistentEditovanjeZadace() throws Exception {
        Uloga asistent = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(asistent.imaPrivilegiju("editovanje-kreirane-zadace")).isEqualTo(true);
    }

    @Test
    public void testStudentEditovanjeZadace() throws Exception {
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(student.imaPrivilegiju("editovanje-kreirane-zadace")).isEqualTo(false);
    }

    @Test
    public void testPostojiIzmjenaBodovaZadace() throws Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("izmjena-bodova-za-zadace")).isEqualTo(true);
    }

    @Test
    public void testProfesorIzmjenaBodovaZadace() throws Exception {
        Uloga profesor = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(profesor.imaPrivilegiju("izmjena-bodova-za-zadace")).isEqualTo(true);
    }

    @Test
    public void testStudentIzmjenaBodovaZadace() throws Exception {
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(student.imaPrivilegiju("izmjena-bodova-za-zadace")).isEqualTo(false);
    }

    @Test
    public void testPostojiBrisanjeKreiranogCasa() throws Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("brisanje-kreiranog-casa")).isEqualTo(true);
    }

    @Test
    public void testProfesorBrisanjeKreiranogCasa() throws Exception {
        Uloga profesor = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(profesor.imaPrivilegiju("brisanje-kreiranog-casa")).isEqualTo(true);
    }

    @Test
    public void testStudentBrisanjeKreiranogCasa() throws Exception {
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(student.imaPrivilegiju("brisanje-kreiranog-casa")).isEqualTo(false);
    }

    @Test
    public void testPostojiDodjelaBodovaZadace() throws Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("dodjela-bodova-za-zadace")).isEqualTo(true);
    }

    @Test
    public void testProfesorDodjelaBodovaZadace() throws Exception {
        Uloga profesor = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(profesor.imaPrivilegiju("dodjela-bodova-za-zadace")).isEqualTo(true);
    }

    @Test
    public void testStudentskaDodjelaBodovaZadace() throws Exception {
        Uloga studentska = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        assertThat(studentska.imaPrivilegiju("dodjela-bodova-za-zadace")).isEqualTo(false);
    }

    @Test
    public void testPostojiKreiranjeKviza() throws Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("kreiranje-kviza")).isEqualTo(true);
    }

    @Test
    public void testProfesorKreiranjeKviza() throws Exception {
        Uloga profesor = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(profesor.imaPrivilegiju("kreiranje-kviza")).isEqualTo(true);
    }

    @Test
    public void testStudentskaKreiranjeKviza() throws Exception {
        Uloga studentska = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        assertThat(studentska.imaPrivilegiju("kreiranje-kviza")).isEqualTo(false);
    }

    @Test
    public void testEditovanjeObavjestenja() throws Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-obavjestenja")).isEqualTo(true);
    }

    @Test
    public void testAsistentEditovanjeObavjestenja() throws Exception {
        Uloga asistent = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(asistent.imaPrivilegiju("editovanje-obavjestenja")).isEqualTo(true);
    }

    @Test
    public void testStudentEditovanjeObavjestenja() throws Exception {
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(student.imaPrivilegiju("editovanje-obavjestenja")).isEqualTo(false);
    }

    @Test
    public void testPostojiEditovanjeTemeForum() throws Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-teme-na-forumu")).isEqualTo(true);
    }

    @Test
    public void testAsistentEditovanjeTemeForum() throws Exception {
        Uloga asistent = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(asistent.imaPrivilegiju("editovanje-teme-na-forumu")).isEqualTo(true);
    }

    @Test
    public void testStudentEditovanjeTemeForum() throws Exception {
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(student.imaPrivilegiju("editovanje-teme-na-forumu")).isEqualTo(false);
    }

    @Test
    public void testPostojiEditovanjeKorisnika() throws Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-korisnika")).isEqualTo(true);
    }

    @Test
    public void testAdminEditovanjeKorisnika() throws Exception {
        Uloga admin = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        assertThat(admin.imaPrivilegiju("editovanje-korisnika")).isEqualTo(true);
    }

    @Test
    public void testProfesorEditovanjeKorisnika() throws Exception {
        Uloga profesor = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(profesor.imaPrivilegiju("editovanje-korisnika")).isEqualTo(false);
    }

    @Test
    public void testPostojiBrisanjeObavjestenja() throws  Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("brisanje-obavjestenja")).isEqualTo(true);
    }

    @Test
    public void testAdminBrisanjeObavjestenja() throws Exception {
        Uloga admin = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        assertThat(admin.imaPrivilegiju("brisanje-obavjestenja")).isEqualTo(true);
    }

    @Test
    public void testStudentBrisanjeObavjestenja() throws Exception {
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(student.imaPrivilegiju("brisanje-obavjestenja")).isEqualTo(false);
    }

    @Test
    public void testPostojiPregledPredmetaSaradnik() throws  Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("pregled-predmeta-saradnik")).isEqualTo(true);
    }

    @Test
    public void testProfesorPregledPredmeta() throws Exception {
        Uloga profesor = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(profesor.imaPrivilegiju("pregled-predmeta-saradnik")).isEqualTo(true);
    }

    @Test
    public void testStudentPregledKaoSaradnik() throws Exception {
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(student.imaPrivilegiju("pregled-predmeta-saradnik")).isEqualTo(false);
    }

    @Test
    public void testPostojiPregledGrupaSaradnik() throws  Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("pristup-grupama-saradnik")).isEqualTo(true);
    }

    @Test
    public void testAsistentPregledGrupa() throws Exception {
        Uloga asistent = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(asistent.imaPrivilegiju("pristup-grupama-saradnik")).isEqualTo(true);
    }

    @Test
    public void testStudentPregledGrupaKaoSaradnik() throws Exception {
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(student.imaPrivilegiju("pristup-grupama-saradnik")).isEqualTo(false);
    }

    @Test
    public void testPostojiUvidUKomentareSaradnik() throws  Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("uvid-u-komentare-saradnik")).isEqualTo(true);
    }

    @Test
    public void testAsistentUvidUKomentare() throws Exception {
        Uloga asistent = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(asistent.imaPrivilegiju("uvid-u-komentare-saradnik")).isEqualTo(true);
    }

    @Test
    public void testStudentUvidUKomentareKaoSaradnik() throws Exception {
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(student.imaPrivilegiju("uvid-u-komentare-saradnik")).isEqualTo(false);
    }

    @Test
    public void testPostojiIzborNacinaSlanja() throws  Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("izbor-nacina-slanja-zadace")).isEqualTo(true);
    }

    @Test
    public void testProfesorBiraNacinSlanja() throws Exception {
        Uloga profesor = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(profesor.imaPrivilegiju("izbor-nacina-slanja-zadace")).isEqualTo(true);
    }

    @Test
    public void testStudentBiraNacinSlanja() throws Exception {
        Uloga student = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(student.imaPrivilegiju("izbor-nacina-slanja-zadace")).isEqualTo(false);
    }

    @Test
    public void testPostojiUnosKonacneOcjene() throws  Exception {
        assertThat(privilegijaRepozitorij.existsBynazivPrivilegije("unos-konacne-ocjene")).isEqualTo(true);
    }

    @Test
    public void testProfesorUnosiKonacnuOcjenu() throws Exception {
        Uloga profesor = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(profesor.imaPrivilegiju("unos-konacne-ocjene")).isEqualTo(true);
    }

    @Test
    public void testAsistentUnosiKonacnuOcjenu() throws Exception {
        Uloga asistent = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(asistent.imaPrivilegiju("unos-konacne-ocjene")).isEqualTo(false);
    }

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
    public void testProfesorNemaPrivilegijuDizanjaBana() throws Exception {
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

    @Test
    public void testAsistentNemaPrivilegijuDodavanjaUloge() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("dodavanje-uloge")).isEqualTo(false);
    }


    @Test
    public void testStudentImaPrivilegijuPopunjavanjaAnkete() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(uloga.imaPrivilegiju("popunjavanje-ankete")).isEqualTo(true);
    }

    @Test
    public void testStudentNemaPrivilegijuDizanjaBana() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(uloga.imaPrivilegiju("dizanje-bana")).isEqualTo(false);
    }

    @Test
    public void testProfesorImaPrivilegijuDodavanjaMaterijala() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("dodavanje-materijala")).isEqualTo(true);
    }

    @Test
    public void testProfesorNemaPrivilegijuUnosaFinansijskihObaveza() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("unos-finansijskih-obaveza")).isEqualTo(false);
    }

    @Test
    public void testAsistentImaPrivilegijuBrisanjaMaterijala() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("brisanje-materijala")).isEqualTo(true);
    }

    @Test
    public void testAsistentNemaPrivilegijuUnosaFinansijskihObaveza() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga.imaPrivilegiju("unos-finansijskih-obaveza")).isEqualTo(false);
    }

    @Test
    public void testStudentskaSluzbaImaPrivilegijuUpisaStudenataUSemestar() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        assertThat(uloga.imaPrivilegiju("upis-studenata-u-semestar")).isEqualTo(true);
    }

    @Test
    public void testStudentskaSluzbaNemaPrivilegijuBrisanjaKorisnika() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        assertThat(uloga.imaPrivilegiju("brisanje-korisnika")).isEqualTo(false);
    }

    @Test
    public void testStudentskaSluzbaImaPrivilegijuUnosaFinansijskihObavezaStudenata() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        assertThat(uloga.imaPrivilegiju("unos-finansijskih-obaveza-studenata")).isEqualTo(true);
    }

    @Test
    public void testStudentskaSluzbaNemaPrivilegijuBrisanjaMaterijala() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        assertThat(uloga.imaPrivilegiju("brisanje-materijala")).isEqualTo(false);
    }

    @Test
    public void testStudentImaPrivilegijuPisanjaKomentaraNaForumu() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(uloga.imaPrivilegiju("pisanje-komentara-na-forumu")).isEqualTo(true);
    }

    @Test
    public void testStudentNemaPrivilegijuDodavanjaUloge() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertThat(uloga.imaPrivilegiju("dodavanje-uloge")).isEqualTo(false);
    }

    @Test
    public void testProfesorImaPrivilegijuUvidaUObavjestenja() {
        Uloga uloga39 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga39.imaPrivilegiju("uvid-u-obavjestenja")).isEqualTo(true);
    }

    @Test
    public void testAdmministratorImaUloguKreiranjaPrivilegija() {
        Uloga uloga147 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        assertThat(uloga147.imaPrivilegiju("kreiranje-privilegija")).isEqualTo(true);
    }

    @Test
    public void testAsistentImaPrivilegijuZaUvidUObavjestenja() {
        Uloga uloga222 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga222.imaPrivilegiju("uvid-u-obavjestenja")).isEqualTo(true);
    }

    @Test
    public void testAsistentImaPrivilegijuRegistrovanjaNoveZadace() {
        Uloga uloga897 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga897.imaPrivilegiju("registrovanje-nove-zadace")).isEqualTo(true);
    }

    @Test
    public void testAsistentImaPrivilegijuKreiranjaTemeNaForumu() {
        Uloga uloga45 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga45.imaPrivilegiju("kreiranje-teme-na-forumu")).isEqualTo(true);
    }

    @Test
    public void testStudentskasluzbapostavljanjaobavjestenja() {
        Uloga uloga123 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        assertThat(uloga123.imaPrivilegiju("postavljanje-obavjestenja")).isEqualTo(true);
    }

    @Test
    public void testProfesorImaPrivilegijuBrisanjaCasa() {
        Uloga uloga99 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertThat(uloga99.imaPrivilegiju("brisanje-kreiranog-casa")).isEqualTo(true);
    }

    @Test
    public void testProfesorImaPrivilegijuKreiranjaTeme() {
        Uloga uloga44 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga44.imaPrivilegiju("kreiranje-teme-na-forumu")).isEqualTo(true);
    }

    @Test
    public void testProfesorImaPrivilegijuKreiranjaTerminaIspita() {
        Uloga uloga2 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga2.imaPrivilegiju("kreiranje-termina-ispita")).isEqualTo(true);
    }

    @Test
    public void testProfesorImaPrivilegijuKreiranjaNovihZadaca() throws Exception {
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertThat(uloga.imaPrivilegiju("kreiranje-zadace")).isEqualTo(true);
    }

    @Test
    public void testPostojiPrivilegijaUvidUKomentare() {
        boolean postoji_privilegija=privilegijaRepozitorij.existsBynazivPrivilegije("uvid-u-komentare");
        assertEquals(true, postoji_privilegija);
    }

    @Test
    public void tesProfesorImaMogucnostUvidaUKomentare() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertEquals(true, (uloga.imaPrivilegiju("uvid-u-komentare")));
    }
    @Test
    public void testAsistentImaMogucnostEditovanjaKomentara() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertEquals(true, (uloga.imaPrivilegiju("editovanje-komentara")));

    }
    @Test
    public void testPostojiPrivilegijaEditovanjeKomentara() {
        boolean postoji_privilegija=privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-komentara");
        assertEquals(true, postoji_privilegija);
    }
    @Test
    public void testPostojiPrivilegijaPrikazKalendara() {
        boolean postoji_privilegija=privilegijaRepozitorij.existsBynazivPrivilegije("prikaz-kalendara");
        assertEquals(true, postoji_privilegija);
    }

    @Test
    public void testStudentImaMogucnostPrikazaKalendara() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertEquals(true, (uloga.imaPrivilegiju("prikaz-kalendara")));
    }
    @Test
    public void testProfesorImaMogucnostOstavljanjaKomentaraNaZadace() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertEquals(true, (uloga.imaPrivilegiju("ostavljanje-komentara-na-zadace")));

    }
    @Test
    public void testPostojiPrivilegijaOstavljanjeKomentaraNaZadace() {
        boolean nadji_privilegiju=privilegijaRepozitorij.existsBynazivPrivilegije("ostavljanje-komentara-na-zadace");
        assertEquals(true, nadji_privilegiju);
    }
    @Test
    public void testStudentskaSluzbaImaMogucnostBrisanjaObavjestenja() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        assertEquals(true, (uloga.imaPrivilegiju("brisanje-obavjestenja")));

    }
    @Test
    public void tesProfesorImaMogucnostBrisanjaObavjestenja() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertEquals(true, (uloga.imaPrivilegiju("brisanje-obavjestenja")));

    }
    @Test
    public void testAsistentImaMogucnostBrisanjaObavjestenja() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertEquals(true, (uloga.imaPrivilegiju("brisanje-obavjestenja")));

    }

    @Test
    public void testProfesorImaMogucnostUnosenjaBodovaIspita() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertEquals(true, (uloga.imaPrivilegiju("unosenje-bodova-ispita")));
    }
    @Test
    public void testAdministratorImaMogucnostEditovanjaPostavljenihObavjestenja() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ADMIN);
        assertEquals(true, (uloga.imaPrivilegiju("editovanje-postavljenih-obavjestenja")));
    }

    @Test
    public void testProfesorImaMogucnostEditovanjaTerminaIspita() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertEquals(true, (uloga.imaPrivilegiju("editovanje-termina-ispita")));
    }
    @Test
    public void testAsistentImaMogucnostBrisanjaTeme() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertEquals(true, (uloga.imaPrivilegiju("brisanje-teme")));
    }
    @Test
    public void testProfesorImaMogucnostBrisanjaZadace() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertEquals(true, (uloga.imaPrivilegiju("brisanje-kreirane-zadace")));
    }

    @Test
    public void testProfesorImaMogucnostZaKreiranjeObavjestenja() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertEquals(true, (uloga.imaPrivilegiju("kreiranje-obavjestenja")));
    }
    @Test
    public void testAsistentImaMogucnostKreiranjaObavjestenja() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertEquals(true, (uloga.imaPrivilegiju("kreiranje-obavjestenja")));
    }
    @Test
    public void testProfesorImaMogucnostRegistrovanjaCasa() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertEquals(true, (uloga.imaPrivilegiju("registrovanje-casa")));
    }
    @Test
    public void testKorisnikImaPrivilegiju() throws IOException{
       
            URL url = new URL("http://localhost:8080/pretragaPrivilegijeId/1/nesto");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(false);
            con.setRequestMethod("GET");
            InputStream in = con.getInputStream();
            String body = IOUtils.toString(in, Charset.forName("UTF-8"));
            assertEquals("false", body) ;
    }
    
}