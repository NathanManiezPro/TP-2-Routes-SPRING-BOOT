package fr.nathan.eleve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

public interface EleveRepository extends JpaRepository<Eleve, Integer> {
}
