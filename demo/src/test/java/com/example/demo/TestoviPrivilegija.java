package com.example.demo;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import static org.junit.Assert.assertNotSame;

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
    public void testStudentImaPrivilegijuPregledaDostupnihTemaZaZavrsni(){
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertEquals(true, (uloga.imaPrivilegiju("pregled-dostupnih-tema-za-zavrsni-rad")));
    }

     @Test
    public void testStudentImaMogucnostPrikazaKalendara(){
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertEquals(true, (uloga.imaPrivilegiju("prikaz-kalendara")));
    }

    @Test
    public void testProfesorImaMogucnostOstavljanjaKomentaraNaZadace() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertEquals(true, (uloga.imaPrivilegiju("ostavljanje-komentara-na-zadace")));
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
     public void tesProfesorImaMogucnostBrisanjaObavjestenja() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR);
        assertEquals(true, (uloga.imaPrivilegiju("brisanje-obavjestenja")));
    }

    @Test
    public void testStudentImaPrivilegijuZaUvidUBodoveNaZadace(){
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENT);
        assertEquals(true, (uloga.imaPrivilegiju("uvid-u-azurirane-bodove-na-zadace")));
    }
    @Test
    public void testAsistentImaMogucnostBrisanjaObavjestenja() {
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.ASISTENT);
        assertEquals(true, (uloga.imaPrivilegiju("brisanje-obavjestenja")));
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
    public void testStudentskaImaPrivilegijuZaPromjenuIzbornogPredmeta(){
        Uloga uloga=ulogaRepozitorij.findBynazivUloge(ImenaUloga.STUDENTSKA_SLUZBA);
        assertEquals(true, (uloga.imaPrivilegiju("promjena-izbornog-predmeta")));
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
    public void testObrisiPrivilegiju() throws IOException{

        Privilegija p=privilegijaRepozitorij.findBynazivPrivilegije("registrovanje-casa");
        URL url = new URL("http://localhost:31915/privilegije/obrisi/"+p.getNazivPrivilegije());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("DELETE");
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        assertEquals("Privilegija je uspjesno obrisana!",body);
    }
    
    @Test
    public void obrisiPrivilegijuPoId() throws IOException{

        Privilegija p=privilegijaRepozitorij.findBynazivPrivilegije("registrovanje-casa");
        URL url = new URL("http://localhost:31915/privilegije/obrisiId/"+p.getId().toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("DELETE");
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        assertEquals("Privilegija je uspjesno obrisana!",body);
    }
    

    @Test
    public void testKorisnikImaUlogu() throws IOException {
        URL url = new URL("http://localhost:31915/pretragaUlogeId/1/12345/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        assertEquals("false", body);
    }

    @Test
    public void testPrivilegijeKorisnikaKorisnikPostoji() throws IOException {
        URL url = new URL("http://localhost:31915/pretragaId/1/dajPrivilegije");
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        assertEquals(false, body.isEmpty());
    }

    @Test
    public void testPrivilegijeKorisnikaKorisnikNePostoji() throws IOException {
        URL url = new URL("http://localhost:31915/pretragaId/10000/dajPrivilegije");
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        assertEquals(true, body.isEmpty());
    }

    @Test
    public void testPrivilegijeKorisnikaUsernameKorisnikPostoji() throws IOException {
        String username = korisnikRepozitorij.findById(Long.valueOf(1)).get().getUsername();
        URL url = new URL("http://localhost:31915/pretragaUsername/" + username + "/dajPrivilegije");
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        assertEquals(false, body.isEmpty());
    }

    @Test
    public void testPrivilegijeKorisnikaUsernameKorisnikNePostoji() throws IOException {
        URL url = new URL("http://localhost:31915/pretragaUsername/hdklahsldhalsld/dajPrivilegije");
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        assertEquals(true, body.isEmpty());
    }

    @Test
    public void testObrisiPrivilegijePrivilegijaPostoji() throws IOException, JSONException {
        Privilegija privilegija = new Privilegija();
        privilegija.setNazivPrivilegije("proba");
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("proba")) privilegijaRepozitorij.save(privilegija);
        URL url = new URL("http://localhost:31915/privilegije/obrisiPrivilegije");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("DELETE");
        httpCon.setRequestProperty("Content-Type", "application/json");
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());

        JSONObject jo = new JSONObject();
        jo.put("naziv", "proba");

        JSONArray ja = new JSONArray();
        ja.put(jo);

        out.write(ja.toString());
        out.close();
        httpCon.getInputStream();
        String body = IOUtils.toString(httpCon.getInputStream(), Charset.forName("UTF-8"));

        assertNotSame("Niti jedna od navedenih privilegija ne postoji u bazi!", body);
    }

    @Test
    public void testObrisiPrivilegijePrivilegijaNePostoji() throws IOException, JSONException {
        URL url = new URL("http://localhost:31915/privilegije/obrisiPrivilegije");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("DELETE");
        httpCon.setRequestProperty("Content-Type", "application/json");
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());

        JSONObject jo = new JSONObject();
        jo.put("naziv", "kshahslhSL");

        JSONArray ja = new JSONArray();
        ja.put(jo);

        out.write(ja.toString());
        out.close();
        httpCon.getInputStream();
        String body = IOUtils.toString(httpCon.getInputStream(), Charset.forName("UTF-8"));

        assertEquals("Niti jedna od navedenih privilegija ne postoji u bazi!", body);
    }

    @Test
    public void testDodajPrivilegijuPrivilegijaNePostoji() throws IOException {
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("dodaj-proba")) {
            URL url = new URL("http://localhost:31915/privilegije/dodajPrivilegiju/dodaj-proba");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            InputStream in = httpCon.getInputStream();
            String body = IOUtils.toString(in, Charset.forName("UTF-8"));
            assertNotSame("Privilegija vec postoji u sistemu!", body);
        }
    }
   URL url = new URL("http://localhost:31915/pretragaId/imaUlogu/1/admin");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            InputStream in = con.getInputStream();

    @Test
    public void testDodajPrivilegijuPrivilegijaPostoji() throws IOException{
        if(privilegijaRepozitorij.existsBynazivPrivilegije("editovanje-korisnika")) {
            URL url = new URL("http://localhost:31915/privilegije/dodajPrivilegiju/editovanje-korisnika");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            InputStream in = httpCon.getInputStream();

            String body = IOUtils.toString(in, Charset.forName("UTF-8"));
            assertEquals("Privilegija vec postoji u sistemu!", body);
        }
    }

    @Test
    public void testDodajPrivilegijePrivilegijaPostoji() throws IOException, JSONException {
        URL url = new URL("http://localhost:31915/privilegije/dodajPrivilegije");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("POST");
        httpCon.setRequestProperty("Content-Type", "application/json");
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());

        JSONObject jo = new JSONObject();
        jo.put("naziv", "editovanje-korisnika");

        JSONArray ja = new JSONArray();
        ja.put(jo);

        out.write(ja.toString());
        out.close();
        httpCon.getInputStream();
        String body = IOUtils.toString(httpCon.getInputStream(), Charset.forName("UTF-8"));

        assertEquals("Sve privilegije vec postoje u bazi!", body);
    }

    @Test
    public void testDodajPrivilegijePrivilegijaNePostoji() throws IOException, JSONException {
        if(!privilegijaRepozitorij.existsBynazivPrivilegije("dodaj-proba1")) {
            URL url = new URL("http://localhost:31915/privilegije/dodajPrivilegije");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());

            JSONObject jo = new JSONObject();
            jo.put("naziv", "dodaj-proba1");

            JSONArray ja = new JSONArray();
            ja.put(jo);

            out.write(ja.toString());
            out.close();
            httpCon.getInputStream();
            String body = IOUtils.toString(httpCon.getInputStream(), Charset.forName("UTF-8"));

            assertNotSame("Sve privilegije vec postoje u bazi!", body);
        }
    }

    @Test
    public void testPrivilegijeUlogeUlogaPostoji() throws IOException {
        Long idUloge = ulogaRepozitorij.findBynazivUloge(ImenaUloga.PROFESOR).getId();
        URL url = new URL("http://localhost:31915/uloga/" + idUloge + "/privilegije");
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        assertEquals(false, body.isEmpty());
    }



    @Test
    public void testPrivilegijeUlogeUlogaNePostoji() throws IOException {
        URL url = new URL("http://localhost:31915/uloga/10000/privilegije");
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        JSONArray ja = new JSONArray();
        assertEquals(ja.toString(), body);
    }

    @Test
    public void testprivilegijeUlogeNazivUlogaPostoji() throws IOException {
        URL url = new URL("http://localhost:31915/uloga/vratiPoNazivu/student/privilegije");
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        assertEquals(false, body.isEmpty());
    }

    @Test
    public void testprivilegijeUlogeNazivUlogaNePostoji() throws IOException {
        URL url = new URL("http://localhost:31915/uloga/vratiPoNazivu/yhkhdal/privilegije");
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        JSONArray ja = new JSONArray();
        assertEquals(ja.toString(), body);
    }
    @Test   
    public void testPovezivanjeUlogePrivilegijeUlogaNePostoji() throws IOException {
        String uloga = "nekaUloga", privilegija = "registrovanje-casa";
        URL url = new URL("http://localhost:31915/privilegije/poveziUloguPrivilegijuPoNazivu/" + uloga + "/" + privilegija);
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String body = IOUtils.toString(in, Charset.forName("UTF-8"));
        assertSame("Specificirana uloga ili privilegija ne postoje!", body);
    }


}
