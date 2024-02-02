package fr.nathan.ticket;

import fr.nathan.salle.Salle;
import fr.nathan.salle.SalleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }
    @PostMapping
    public Ticket save(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }
    @PostMapping ("/{id}")
    public Ticket findById(@PathVariable Long id) {
        return ticketService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        ticketService.deleteById(id);
    }

    @PutMapping
    public Ticket update(@RequestBody Ticket ticket) {
        return ticketService.update(ticket);
    }

}