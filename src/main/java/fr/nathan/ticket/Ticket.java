package fr.nathan.ticket;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.nathan.film.Film;
import fr.nathan.realisateur.Realisateur;
import fr.nathan.seances.Seance;
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
public class Ticket {
    @Id
    @GeneratedValue
    private long id;
    private Seance seance;
    private String nomClient;
    private int nombrePlaces;
}
