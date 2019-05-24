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
        Uloga uloga39 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
		assertThat(uloga39.imaPrivilegiju("uvid-u-obavjestenja")).isEqualTo(true);
    }
    

}

	public void testAsistentImaPrivilegijuZaUvidUObavjestenja() {
		Uloga uloga222 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
		assertThat(uloga222.imaPrivilegiju("uvid-u-obavjestenja")).isEqualTo(true);
    }

    

}

	@Test

	public void testAsistentImaPrivilegijuRegistrovanjaNoveZadace() {
        Uloga uloga897 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
		assertThat(uloga897.imaPrivilegiju("registrovanje-nove-zadace")).isEqualTo(true);

	public void testAsistentImaPrivilegijuKreiranjaTemeNaForumu() {
		Uloga uloga45 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
		assertThat(uloga45.imaPrivilegiju("kreiranje-teme-na-forumu")).isEqualTo(true);

    }
    

}



    
	@Test
	public void testStudentskasluzbapostavljanjaobavjestenja() {

       Uloga uloga123 = ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
	   assertThat(uloga123.imaPrivilegiju("postavljanje-obavjestenja")).isEqualTo(true);
    }

}

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



