package fr.nathan.seances;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.nathan.film.Film;
import fr.nathan.salle.Salle;
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
    @ManyToOne
    @JoinColumn(name ="film_id")
    private Film film;
    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;
    private Date date;
    private int placesDisponibles;
    private float prix;
}