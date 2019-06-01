package com.example.autorizacija;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegijaRepozitorij extends JpaRepository<Privilegija, Long> {
    boolean existsBynazivPrivilegije(String privilegija);
}