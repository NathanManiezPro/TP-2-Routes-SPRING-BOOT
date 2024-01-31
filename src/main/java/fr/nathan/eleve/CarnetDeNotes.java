package fr.nathan.eleve;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarnetDeNotes {
    @Id
    @GeneratedValue
    private Integer id;
    private String appreciation;
    private double moyenne;

}
