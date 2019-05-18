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
    public void testProfesorImaPrivilegijuPregledaKomentara() throws Exception {
        
        Uloga uloga = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        
        assertThat(uloga.imaPrivilegiju("pregled-komentara")).isEqualTo(true);
    }
}
