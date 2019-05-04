package com.example.autorizacija;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "odsjek")
public class Odsjek {

    @Id
    @Column(name = "idOdsjek")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "naziv", length = 25)
    @NotNull
    private String nazivOdsjeka;

    public Odsjek() {}

    public Odsjek(Long id, String nazivOdsjeka) {
        this.id = id;
        this.nazivOdsjeka = nazivOdsjeka;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivOdsjeka() {
        return nazivOdsjeka;
    }

    public void setNazivOdsjeka(String nazivOdsjeka) {
        this.nazivOdsjeka = nazivOdsjeka;
    }
}
