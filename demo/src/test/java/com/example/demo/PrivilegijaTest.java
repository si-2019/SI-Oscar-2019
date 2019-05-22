package com.example.demo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PrivilegijaTest {
    @Autowired
    private UlogaRepozitorij ulogaRepozitorij;
    @Autowired
    private KorisnikRepozitorij korisnikRepozitorij;
    @Autowired
    private OdsjekRepozitorij odsjekRepozitorij;
    @Autowired
    private PrivilegijaRepozitorij privilegijaRepozitorij;

    
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









 

}

