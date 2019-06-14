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
public class TestoviPrivilegija {
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
    @Test
    public void testPostojiPrivilegijaZaGenerisanjePoruka(){
        boolean nadji_privilegiju=privilegijaRepozitorij.existsBynazivPrivilegije("slanje-generisanih-poruka");
        assertEquals(true, nadji_privilegiju);
    }
    @Test
    public void testStudentskaSluzbaImaPrivilegijuGenerisanjaPoruka(){
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        assertEquals(true, (uloga.imaPrivilegiju("slanje-generisanih-poruka")));
    }
    @Test
    public void testPostojiPrivilegijaZaUvidUBodoveNaZadace(){
        boolean nadji_privilegiju=privilegijaRepozitorij.existsBynazivPrivilegije("uvid-u-azurirane-bodove-na-zadace");
        assertEquals(true, nadji_privilegiju);
    }
    @Test
    public void testStudentImaPrivilegijuZaUvidUBodoveNaZadace(){
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertEquals(true, (uloga.imaPrivilegiju("uvid-u-azurirane-bodove-na-zadace")));
    }
    @Test
    public void testPostojiPrivilegijaZaPrijavuNaVjezbe(){
        boolean nadji_privilegiju=privilegijaRepozitorij.existsBynazivPrivilegije("prijava-na-vjezbe");
        assertEquals(true, nadji_privilegiju);
    }
    @Test
    public void testStudentImaPrivilegijuZaPrijavuNaVjezbe(){
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertEquals(true, (uloga.imaPrivilegiju("prijava-na-vjezbe")));
    }
    @Test
    public void testPostojiPrivilegijaZaDodavanjeNovihMaterijala(){
        boolean nadji_privilegiju=privilegijaRepozitorij.existsBynazivPrivilegije("dodavanje-novih-materijala");
        assertEquals(true, nadji_privilegiju);
    }
    @Test
    public void testAsistentImaPrivilegijuZaDodavanjeNovihMaterijala(){
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertEquals(true, (uloga.imaPrivilegiju("dodavanje-novih-materijala")));
    }
    @Test
    public void testPostojiPrivilegijaZaPromjenuIzbornogPredmeta(){
        boolean nadji_privilegiju=privilegijaRepozitorij.existsBynazivPrivilegije("promjena-izbornog-predmeta");
        assertEquals(true, nadji_privilegiju);
    }

    @Test
    public void testStudentImaPrivilegijuZaPromjenuIzbornogPredmeta(){
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertEquals(true, (uloga.imaPrivilegiju("promjena-izbornog-predmeta")));
    }

}
