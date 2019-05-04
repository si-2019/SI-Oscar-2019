package com.example.autorizacija;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdsjekRepozitorij extends JpaRepository<Odsjek, Long> {
    boolean existsBynazivOdsjeka(String naziv);
}
