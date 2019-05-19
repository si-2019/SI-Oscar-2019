package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UlogaRepozitorij extends JpaRepository<Uloga, Long> {
    Uloga findBynazivUloge (ImenaUloga naziv);
boolean existsBynazivUloge(ImenaUloga naziv);

}
