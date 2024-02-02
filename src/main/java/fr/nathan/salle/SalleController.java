package fr.nathan.salle;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salles")
public class SalleController {
    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }
    @GetMapping
    public List<Salle> findAll() {
        return salleService.findAll();
    }
    @PostMapping
    public Salle save(@RequestBody Salle salle) {
        return salleService.save(salle);
    }
    @PostMapping ("/{id}")
    public Salle findById(@PathVariable Long id) {
        return salleService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        salleService.deleteById(id);
    }

    @PutMapping
    public Salle update(@RequestBody Salle salle) {
        return salleService.update(salle);
    }

    @GetMapping("/search") //   /salles/search?titre=toto
    public Salle findByNumero(@RequestParam String numero) {
        return salleService.findByNumero(numero);
    }
}
