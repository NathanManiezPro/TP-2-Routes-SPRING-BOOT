package fr.nathan.film;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    // SELECT * FROM FILM WHERE tire like ...

    Optional<List<Film>> findAllByTitre(String titre);
}
