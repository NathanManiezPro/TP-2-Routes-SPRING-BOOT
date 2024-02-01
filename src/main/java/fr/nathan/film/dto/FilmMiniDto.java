package fr.nathan.film.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmMiniDto {
    private Integer id;
    private String titre;
    private LocalDate dateSortie;
    private List<FilmMiniDto> Films = new ArrayList<>();
}
