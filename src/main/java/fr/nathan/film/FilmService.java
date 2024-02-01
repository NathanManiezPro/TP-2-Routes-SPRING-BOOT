package fr.nathan.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.nathan.acteur.Acteur;
import fr.nathan.acteur.ActeurService;
import fr.nathan.acteur.dto.ActeurSansFilmDto;
import fr.nathan.film.dto.FilmAjoutActeurAFilmDto;
import fr.nathan.film.dto.FilmCompletDto;
import fr.nathan.film.exceptions.BadRequestException;
import fr.nathan.film.exceptions.FilmNotFoundException;
import fr.nathan.realisateur.Realisateur;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final ActeurService acteurService;
    private final ObjectMapper objectMapper;

    public FilmService(FilmRepository filmRepository, ActeurService acteurService, ObjectMapper objectMapper) {
        this.filmRepository = filmRepository;
        this.acteurService = acteurService;
        this.objectMapper = objectMapper;
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film save(Film film) {

        verifyFilm(film);

        return filmRepository.save(film);
    }

    private static void verifyFilm(Film film) {
        List<String> erreurs = new ArrayList<>();
        if(film.getTitre() == null){
           erreurs.add("Le titre est obligatoire");
        }
        if(film.getDateSortie() == null){
            erreurs.add("La date de sortie est obligatoire");
        }
        if(film.getRealisateur() == null){
           erreurs.add("Le Réalisateur est obligatoire");
        }
        if(!erreurs.isEmpty()){
            throw new BadRequestException(erreurs);
        }
    }

    public Film findById(Integer id) {
        return filmRepository.findById(id)
                .orElseThrow(
                        FilmNotFoundException::new

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

    public FilmAjoutActeurAFilmDto AddActeurByFilmId(Integer id, Acteur acteur){
        Film film = this.findById(id);
        Acteur acteurid = acteurService.findById(acteur.getId());
        film.getActeurs().add(acteurid);
        this.save(film);
        FilmAjoutActeurAFilmDto filmAjoutActeurAFilmDto = new FilmAjoutActeurAFilmDto();
        filmAjoutActeurAFilmDto.setId(film.getId());
        filmAjoutActeurAFilmDto.setTitre(film.getTitre());
        filmAjoutActeurAFilmDto.setDateSortie(film.getDateSortie());
        filmAjoutActeurAFilmDto.setRealisateur(film.getRealisateur());
        filmAjoutActeurAFilmDto.setActeurs(
                film.getActeurs().stream().map(
                        acteur1 -> objectMapper.convertValue(acteur1, ActeurSansFilmDto.class)
                ).toList()
        );
        return filmAjoutActeurAFilmDto;
    }

}