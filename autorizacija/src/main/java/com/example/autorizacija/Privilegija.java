package com.example.autorizacija;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "privilegija")
public class Privilegija {

    @Id
    @Column(name = "idPrivilegija")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "privilegija", length = 100)
    @NotNull
    private String nazivPermisije;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "uloga_privilegija",
            joinColumns = {@JoinColumn(name = "idPrivilegija", referencedColumnName = "idPrivilegija")},
            inverseJoinColumns = {@JoinColumn(name = "idUloga", referencedColumnName = "idUloga")})
    private List<Uloga> uloge;

    public Privilegija () {}

    public Privilegija (Long id, String naziv_permisije){
        this.id = id;
        this.nazivPermisije = naziv_permisije;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv_permisije() {
        return nazivPermisije;
    }

    public void setNaziv_permisije(String naziv_permisije) {
        this.nazivPermisije = naziv_permisije;
    }

    public List<Uloga> getRoles() {
        return uloge;
    }

    public void setRoles(List<Uloga> uloge) {
        this.uloge = uloge;
    }

}
