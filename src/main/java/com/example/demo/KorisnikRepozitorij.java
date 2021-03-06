package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepozitorij extends JpaRepository<Korisnik, Long> {
    Korisnik findByusername(String username);
}