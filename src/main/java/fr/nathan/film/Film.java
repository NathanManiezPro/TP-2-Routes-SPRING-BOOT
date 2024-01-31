package fr.nathan.film;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
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
@Table(name = "film") // est par défaut le nom de la classe mais il est toujours bien de l'ajouter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Film {


    @Id
    @GeneratedValue
    private Integer id;

  //  @Column(name = "Nom de la colonne") // nom de la colonne dans la base de donnée

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private LocalDate dateSortie;

    @Column(nullable = false)
    private  int duree;

    @Column(length = 500) // nb de caractere
    private  String synopsis;

    @ManyToOne(cascade = CascadeType.ALL) // Plusieurs Films vers 1 réalisateur
    @JoinColumn(name = "realisateur_id")
    private Realisateur realisateur;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "acteur_film",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "acteur_id")
    )
    private List<Acteur> acteurs= new ArrayList<>();


}
