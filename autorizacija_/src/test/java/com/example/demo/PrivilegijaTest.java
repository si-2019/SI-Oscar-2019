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
	public void testAsistentImaMogucnostEditovanjaObavjestenja() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertEquals(true, (uloga.imaPrivilegiju("editovanje-komentara")));
        
    }
    @Test
	public void tesPostojiPrivilegija() {
        boolean postoji_privilegija=privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-komentara");
        assertEquals(true, postoji_privilegija);
        
    }
  
    

}