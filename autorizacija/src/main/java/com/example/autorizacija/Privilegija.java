package com.example.autorizacija;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Privilegija")
public class Privilegija {

    @Id
    @Column(name = "idPrivilegija")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "privilegija", length = 100)
    @NotNull
    private String nazivPrivilegije;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "uloga_privilegija",
            joinColumns = {@JoinColumn(name = "idPrivilegija", referencedColumnName = "idPrivilegija")},
            inverseJoinColumns = {@JoinColumn(name = "idUloga", referencedColumnName = "idUloga")})
    private List<Uloga> uloge;

    public Privilegija () {}

    public Privilegija (Long id, String naziv_permisije){
        this.id = id;
        this.nazivPrivilegije = naziv_permisije;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivPrivilegije() {
        return nazivPrivilegije;
    }

    public void setNazivPrivilegije(String nazivPrivilegije) {
        this.nazivPrivilegije = nazivPrivilegije;
    }

    public List<Uloga> getUloge() {
        return uloge;
    }

    public void setUloge(List<Uloga> uloge) {
        this.uloge = uloge;
    }
}
