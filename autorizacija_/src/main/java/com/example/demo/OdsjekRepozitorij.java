package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdsjekRepozitorij extends JpaRepository<Odsjek, Long> {
    Odsjek findBynazivOdsjeka(String naziv);
    boolean existsBynazivOdsjeka(String naziv);
}
 