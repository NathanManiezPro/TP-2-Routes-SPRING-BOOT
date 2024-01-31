package fr.nathan.acteur;

import fr.nathan.film.Film;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Acteur {
    @Id
    @GeneratedValue
    private Integer id;
    private String nom;
    private String prenom;

    @ManyToMany(
            mappedBy = "acteurs",
            cascade = CascadeType.PERSIST)
    private List<Film> films = new ArrayList<>();

}
