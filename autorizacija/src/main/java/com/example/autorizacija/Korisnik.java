package com.example.autorizacija;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.sql.Date;

@Entity
@Table(name = "korisnik")
public class Korisnik {

    @javax.persistence.Id
    @Column(name = "idKorisnik")
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
    private String Ime;

    @Column (name = "prezime", length = 50)
    @NotNull
    private String Prezime;

    @Column (name = "datumRodjenja")
    private Date DatumRodjenja;

    @Column (name = "JMBG", length = 13)
    private String Jmbg;

    @Column (name = "email", length = 50)
    private String Email;

    @Column (name = "mjestoRodjenja", length = 50)
    private String MjestoRodjenja;

    @Column (name = "kanton", length = 50)
    private String Kanton;

    @Column (name = "drzavljanstvo", length = 50)
    private String Drzavljanstvo;

    @Column (name = "telefon", length = 50)
    private String Telefon;

    @Column (name = "spol")
    private boolean Spol;

    @Column (name = "imePrezimeMajke", length = 100)
    private String ImePrezimeMajke;

    @Column (name = "imePrezimeOca", length = 100)
    private String ImePrezimeOca;

    @Column (name = "adresa", length = 50)
    private String Adresa;

    @Column (name = "username", length = 50)
    private String Username;

    @Column (name = "password", length = 50)
    private String Password;

    @Column (name = "linkedin", length = 50)
    private String Linkedin;

    @Column (name = "website", length = 50)
    private String Website;

    @Column (name = "fotografija")
    private Blob Fotografija;

    @Column (name = "indeks", length = 50)
    private String Indeks;

    @Column (name = "ciklus", length = 50)
    private String Ciklus;

    @Column (name = "semestar", length = 50)
    private String Semestar;

    @Column (name = "titula", length = 50)
    private String Titula;



    public Korisnik() {}
    public Korisnik(String ime, String prezime) {
        this.Ime = ime;
        this.Prezime = prezime;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        this.Ime = ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        this.Prezime = prezime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Uloga getRole_id() {
        return uloga_id;
    }

    public void setRole_id(Uloga role_id) {
        this.uloga_id = role_id;
    }

    public Date getDatumRodjenja() {
        return DatumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        DatumRodjenja = datumRodjenja;
    }

    public String getJmbg() {
        return Jmbg;
    }

    public void setJmbg(String jmbg) {
        Jmbg = jmbg;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMjestoRodjenja() {
        return MjestoRodjenja;
    }

    public void setMjestoRodjenja(String mjestoRodjenja) {
        MjestoRodjenja = mjestoRodjenja;
    }

    public String getKanton() {
        return Kanton;
    }

    public void setKanton(String kanton) {
        Kanton = kanton;
    }

    public String getDrzavljanstvo() {
        return Drzavljanstvo;
    }

    public void setDrzavljanstvo(String drzavljanstvo) {
        Drzavljanstvo = drzavljanstvo;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public boolean isSpol() {
        return Spol;
    }

    public void setSpol(boolean spol) {
        Spol = spol;
    }

    public String getImePrezimeMajke() {
        return ImePrezimeMajke;
    }

    public void setImePrezimeMajke(String imePrezimeMajke) {
        ImePrezimeMajke = imePrezimeMajke;
    }

    public String getImePrezimeOca() {
        return ImePrezimeOca;
    }

    public void setImePrezimeOca(String imePrezimeOca) {
        ImePrezimeOca = imePrezimeOca;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getLinkedin() {
        return Linkedin;
    }

    public void setLinkedin(String linkedin) {
        Linkedin = linkedin;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public Blob getFotografija() {
        return Fotografija;
    }

    public void setFotografija(Blob fotografija) {
        Fotografija = fotografija;
    }

    public String getIndeks() {
        return Indeks;
    }

    public void setIndeks(String indeks) {
        Indeks = indeks;
    }

    public String getCiklus() {
        return Ciklus;
    }

    public void setCiklus(String ciklus) {
        Ciklus = ciklus;
    }

    public String getSemestar() {
        return Semestar;
    }

    public void setSemestar(String semestar) {
        Semestar = semestar;
    }

    public String getTitula() {
        return Titula;
    }

    public void setTitula(String titula) {
        Titula = titula;
    }

    public Odsjek getOdsjek_id() {
        return odsjek_id;
    }

    public void setOdsjek_id(Odsjek odsjek_id) {
        this.odsjek_id = odsjek_id;
    }
}
