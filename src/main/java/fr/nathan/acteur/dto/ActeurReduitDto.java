package fr.nathan.acteur.dto;

import fr.nathan.film.Film;
import fr.nathan.film.dto.FilmCompletDto;
import fr.nathan.film.dto.FilmSansActeurDto;
import fr.nathan.realisateur.Realisateur;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ActeurReduitDto {
    private Integer id;
    private String nom;
    private String prenom;
    private List<FilmSansActeurDto> films = new ArrayList<>();


}
