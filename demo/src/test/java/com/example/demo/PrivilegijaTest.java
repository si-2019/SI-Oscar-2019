package com.example.demo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PrivilegijaTest {
    @Autowired
    private PrivilegijaRepozitorij privilegijaRepozitorij;
    @Autowired
    private KorisnikRepozitorij korisnikRepozitorij;

    @Autowired
    private OdsjekRepozitorij odsjekRepozitorij;

    @Autowired
    private UlogaRepozitorij ulogaRepozitorij;

    @Test
    public void testPostojiPrivilegijaZaPregledDostupnihTemaZaZavrsni(){
        boolean nadji_privilegiju=privilegijaRepozitorij.existsBynazivPrivilegije("pregled-dostupnih-tema-za-zavrsni-rad");
        assertEquals(true, nadji_privilegiju);
    }
    @Test
    public void testStudentImaPrivilegijuPregledaDostupnihTemaZaZavrsni(){
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertEquals(true, (uloga.imaPrivilegiju("pregled-dostupnih-tema-za-zavrsni-rad")));
    }

}
