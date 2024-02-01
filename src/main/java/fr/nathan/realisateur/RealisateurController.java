package fr.nathan.realisateur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.nathan.film.Film;
import fr.nathan.film.dto.FilmSansActeursNiRealisateurDto;
import fr.nathan.realisateur.dto.RealisateurAvecFilmsDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realisateurs")
public class RealisateurController {
    private final RealisateurService realisateurService;

    private final ObjectMapper objectMapper;

    public RealisateurController(RealisateurService realisateurService, ObjectMapper objectMapper) {
        this.realisateurService = realisateurService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Realisateur> findAll() {
        return realisateurService.findAll();
    }

    @GetMapping("/{id}")
    public RealisateurAvecFilmsDto findById(@PathVariable int id) {
        return realisateurService.findRealisateurWithFilm(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        realisateurService.deleteById(id);
    }

    @PostMapping
    public Realisateur save(@RequestBody Realisateur realisateur) {
        return realisateurService.save(realisateur);
    }

    @PutMapping
    public Realisateur update(@RequestBody Realisateur realisateur) {
        return realisateurService.save(realisateur);
    }

    @GetMapping("/{id}/films")
    public List<FilmSansActeursNiRealisateurDto> findFilmsByRealisateurId(@PathVariable Integer id) {
        List<Film> filmsDuRealisateur = realisateurService.findFilmsByRealisateurId(id);

        return filmsDuRealisateur.stream().map(
                film -> objectMapper.convertValue(film, FilmSansActeursNiRealisateurDto.class)
        ).toList();
    }

}


