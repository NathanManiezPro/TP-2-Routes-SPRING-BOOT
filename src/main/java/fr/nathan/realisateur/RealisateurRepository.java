package fr.nathan.realisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

public interface RealisateurRepository extends JpaRepository<Realisateur, Integer> {

}
