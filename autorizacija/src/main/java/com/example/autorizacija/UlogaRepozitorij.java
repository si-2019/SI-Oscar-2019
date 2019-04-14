package com.example.autorizacija;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UlogaRepozitorij extends JpaRepository<Uloga, Long> {
}
