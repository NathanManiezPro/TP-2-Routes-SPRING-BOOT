package fr.nathan.salle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    Optional<Salle> findByCapacite(int capacite);
}
