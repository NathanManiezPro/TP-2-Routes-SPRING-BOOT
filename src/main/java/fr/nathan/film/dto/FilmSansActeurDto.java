package fr.nathan.film.dto;

import fr.nathan.realisateur.Realisateur;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmSansActeurDto {
    private Integer id;
    private String titre;
    private LocalDate dateSortie;
    private Realisateur realisateur;
}