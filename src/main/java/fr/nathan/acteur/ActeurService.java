package fr.nathan.acteur;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class ActeurService {
    private final ActeurRepository acteurRepository;

    public ActeurService(ActeurRepository acteurRepository) {
        this.acteurRepository = acteurRepository;
    }

    public Acteur save(Acteur entity) {
        return acteurRepository.save(entity);
    }

    public Acteur findById(Integer integer) {
        return acteurRepository.findById(integer).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Acteur non trouvé"
                )
        );
    }

    public void delete(Acteur acteur) {
        this.findById(acteur.getId());
        acteurRepository.delete(acteur);
    }

    public List<Acteur> findAll() {
        return acteurRepository.findAll();
    }
}