package fr.nathan.seances;

import fr.nathan.salle.Salle;
import fr.nathan.salle.SalleService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/seances")
public class SeanceController {
    private final SeanceService seanceService;

    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }


    @GetMapping
    public List<Seance> findAll() {
        return seanceService.findAll();
    }
    @PostMapping
    public Seance save(@RequestBody Seance seance) {
        return seanceService.save(seance);
    }
    @PostMapping("/{id}")
    public Seance findById(@PathVariable Long id) {
        return seanceService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        seanceService.deleteById(id);
    }

    @PutMapping
    public Seance update(@RequestBody Seance seance) {
        return seanceService.update(seance);
    }

}