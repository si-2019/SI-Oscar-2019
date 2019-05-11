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
}
