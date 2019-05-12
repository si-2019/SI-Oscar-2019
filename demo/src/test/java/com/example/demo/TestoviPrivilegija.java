package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


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
}
