package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepozitorij extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findById(Long id);
}
