package fr.nathan.salle;


import fr.nathan.salle.Salle;
import fr.nathan.salle.SalleRepository;
import fr.nathan.seances.SeanceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SalleService {
    private final SalleRepository salleRepository;

    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }


    public List<Salle> findAll(){
        return salleRepository.findAll();
    }

    public Salle save(Salle salle) {
        return salleRepository.save(salle);
    }

    public Salle findById(Long id) {
        return salleRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Film non trouvé"
                        )
                );
    }

    public void deleteById(Long id) {
        Salle salle = this.findById(id);
        salleRepository.delete(salle);
    }

    public Salle update(Salle salle) {
        return salleRepository.save(salle);
    }

}