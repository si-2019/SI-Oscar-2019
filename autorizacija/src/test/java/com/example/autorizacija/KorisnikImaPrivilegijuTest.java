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
	public void testProfesorImaPrivilegijuUvidaUObavjestenja() {

        Uloga uloga99 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
		assertThat(uloga99.imaPrivilegiju("brisanje-kreiranog-casa")).isEqualTo(true);


        Uloga uloga44 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
		assertThat(uloga44.imaPrivilegiju("kreiranje-teme-na-forumu")).isEqualTo(true);
        Uloga uloga2 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);

		assertThat(uloga2.imaPrivilegiju("kreiranje-termina-ispita")).isEqualTo(true);

		assertThat(uloga.imaPrivilegiju("izmjena-bodova-za-ispite")).isEqualTo(true);


    }
    

}