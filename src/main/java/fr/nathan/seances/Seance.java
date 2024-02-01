package fr.nathan.seances;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.nathan.film.Film;
import fr.nathan.realisateur.Realisateur;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Seance {
    @Id
    @GeneratedValue

    private long id;
//    @OneToMany
// @JoinColumn(name ="film_id")
//    private Film film;
// private Salle salle;
    private Date date;
    private int placesDisponibles;
    private float prix;
//    private List<Film> films = new ArrayList<>();




    @ManyToOne // One Realisateur to Many Film
    @JoinColumn(name = "realisateur_id")
    private Realisateur realisateur;
}