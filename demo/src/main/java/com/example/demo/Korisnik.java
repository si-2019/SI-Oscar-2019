package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Korisnik")
public class Korisnik {

    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne()
    @JoinColumn (name = "idOdsjek")
    private Odsjek odsjek_id;

    @ManyToOne ()
    @JoinColumn (name = "idUloga")
    private Uloga uloga_id;

    @Column (name = "ime", length = 50)
    @NotNull
    private String ime;

    @Column (name = "prezime", length = 50)
    @NotNull
    private String prezime;

    @Column (name = "datumRodjenja")
    private Date datumRodjenja;

    @Column (name = "JMBG", length = 13)
    private String jmbg;

    @Column (name = "email", length = 50)
    private String email;

    @Column (name = "mjestoRodjenja", length = 50)
    private String mjestoRodjenja;

    @Column (name = "kanton", length = 50)
    private String kanton;

    @Column (name = "drzavljanstvo", length = 50)
    private String drzavljanstvo;

    @Column (name = "telefon", length = 50)
    private String telefon;

    @Column (name = "spol")
    private boolean spol;

    @Column (name = "imePrezimeMajke", length = 100)
    private String imePrezimeMajke;

    @Column (name = "imePrezimeOca", length = 100)
    private String imePrezimeOca;

    @Column (name = "adresa", length = 50)
    private String adresa;

    @Column (name = "username", length = 50)
    private String username;

    @Column (name = "password", length = 50)
    private String password;

    @Column (name = "linkedin", length = 50)
    private String linkedin;

    @Column (name = "website", length = 50)
    private String website;

    @Column (name = "fotografija")
    private Blob fotografija;

    @Column (name = "indeks", length = 50)
    private String indeks;

    @Column (name = "ciklus", length = 50)
    private String ciklus;

    @Column (name = "semestar", length = 50)
    private String semestar;

    @Column (name = "titula", length = 50)
    private String titula;

    public Korisnik() {}

    public Korisnik(Long id, Odsjek odsjek_id, Uloga uloga_id, @NotNull String ime, @NotNull String prezime, Date datumRodjenja, String jmbg, String email, String mjestoRodjenja, String kanton, String drzavljanstvo, String telefon, boolean spol, String imePrezimeMajke, String imePrezimeOca, String adresa, String username, String password, String linkedin, String website, Blob fotografija, String indeks, String ciklus, String semestar, String titula) {
        this.Id = id;
        this.odsjek_id = odsjek_id;
        this.uloga_id = uloga_id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.jmbg = jmbg;
        this.email = email;
        this.mjestoRodjenja = mjestoRodjenja;
        this.kanton = kanton;
        this.drzavljanstvo = drzavljanstvo;
        this.telefon = telefon;
        this.spol = spol;
        this.imePrezimeMajke = imePrezimeMajke;
        this.imePrezimeOca = imePrezimeOca;
        this.adresa = adresa;
        this.username = username;
        this.password = password;
        this.linkedin = linkedin;
        this.website = website;
        this.fotografija = fotografija;
        this.indeks = indeks;
        this.ciklus = ciklus;
        this.semestar = semestar;
        this.titula = titula;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        datumRodjenja = datumRodjenja;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMjestoRodjenja() {
        return mjestoRodjenja;
    }

    public void setMjestoRodjenja(String mjestoRodjenja) {
        this.mjestoRodjenja = mjestoRodjenja;
    }

    public String getKanton() {
        return kanton;
    }

    public void setKanton(String kanton) {
        this.kanton = kanton;
    }

    public String getDrzavljanstvo() {
        return drzavljanstvo;
    }

    public void setDrzavljanstvo(String drzavljanstvo) {
        this.drzavljanstvo = drzavljanstvo;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public boolean isSpol() {
        return spol;
    }

    public void setSpol(boolean spol) {
        this.spol = spol;
    }

    public String getImePrezimeMajke() {
        return imePrezimeMajke;
    }

    public void setImePrezimeMajke(String imePrezimeMajke) {
        this.imePrezimeMajke = imePrezimeMajke;
    }

    public String getImePrezimeOca() {
        return imePrezimeOca;
    }

    public void setImePrezimeOca(String imePrezimeOca) {
        this.imePrezimeOca = imePrezimeOca;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Blob getFotografija() {
        return fotografija;
    }

    public void setFotografija(Blob fotografija) {
        this.fotografija = fotografija;
    }

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public String getCiklus() {
        return ciklus;
    }

    public void setCiklus(String ciklus) {
        this.ciklus = ciklus;
    }

    public String getSemestar() {
        return semestar;
    }

    public void setSemestar(String semestar) {
        this.semestar = semestar;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    public Odsjek getOdsjek_id() {
        return odsjek_id;
    }

    public void setOdsjek_id(Odsjek odsjek_id) {
        this.odsjek_id = odsjek_id;
    }

    Uloga getUloga_id() {
        return uloga_id;
    }

    public void setUloga_id(Uloga uloga_id) {
        this.uloga_id = uloga_id;
    }

    boolean imaPrivilegiju(String privilegija){
        boolean ima = false;
        List<Privilegija> privilegije = this.getUloga_id().getPrivilegije();
        for (Privilegija p: privilegije) {
            if (p.getNazivPrivilegije().toLowerCase().equals(privilegija.toLowerCase())) {
                ima = true;
                break;
            }
        }
        return ima;
    }

    boolean imaUlogu(String uloga) {
        String ulogaKorisnika = this.getUloga_id().getNazivUloge().toString();
        return uloga.toLowerCase().equals(ulogaKorisnika.toLowerCase());
    }
}
