package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Uloga")
public class Uloga {

    @Id
    @Column(name = "idUloga")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "naziv", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private ImenaUloga nazivUloge;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "uloga_privilegija",
            joinColumns = {@JoinColumn(name = "idUloga", referencedColumnName = "idUloga")},
            inverseJoinColumns = {@JoinColumn(name = "idPrivilegija", referencedColumnName = "idPrivilegija")})
    private List<Privilegija> privilegije;

    public Uloga () {}

    public Uloga (Long id, ImenaUloga naziv_role){
        this.id = id;
        this.nazivUloge = naziv_role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImenaUloga getNazivUloge() {
        return nazivUloge;
    }

    public void setNazivUloge(ImenaUloga nazivUloge) {
        this.nazivUloge = nazivUloge;
    }

    public List<Privilegija> getPrivilegije() {
        return privilegije;
    }

    public void setPrivilegije(List<Privilegija> privilegije) {
        this.privilegije = privilegije;
    }
        public boolean imaPrivilegiju (String privilegija){
        boolean ima = false;
        List<Privilegija> privilegije = this.getPrivilegije();
        for (Privilegija p: privilegije) {
            if (p.getNazivPrivilegije().equals(privilegija)) {
                ima = true;
                break;
            }
        }
        return ima;
    }
}
