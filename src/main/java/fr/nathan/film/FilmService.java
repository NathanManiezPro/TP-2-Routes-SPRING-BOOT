package fr.nathan.film;

import fr.nathan.acteur.Acteur;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }

    public Film findById(Integer id) {
        return filmRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Film Non trouvé"
                        )
                );
    }

    public void deleteById(Integer id) {
        Film film = this.findById(id);
        filmRepository.delete(film);
    }

    public Film update(Film film) {
        return filmRepository.save(film);
    }

    public Film findByTitre(String titre) {
        return filmRepository.findByTitre(titre)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Aucun film avec le titre : " + titre
                        )
                );
    }

    public List<Film> findAllByRealisateurId(Integer id) {
        return filmRepository.findAllByRealisateurId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun film ayant ce réalisateur"
                ));
    }

    public List<Acteur> findActeursByFilm(Integer id) {
        Film film = this.findById(id);
        return film.getActeurs();
    }
}