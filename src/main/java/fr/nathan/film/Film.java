package fr.nathan.film;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.nathan.acteur.Acteur;
import fr.nathan.realisateur.Realisateur;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "film")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Film {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private LocalDate dateSortie;

    @Column(nullable = false)
    private int duree;

    @Column(length = 500)
    private String synopsis;

    @ManyToOne // One Realisateur to Many Film
    @JoinColumn(name = "realisateur_id")
    private Realisateur realisateur;

    @ManyToMany
    @JoinTable(
            name = "acteur_film",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "acteur_id")
    )
    private List<Acteur> acteurs = new ArrayList<>();
}