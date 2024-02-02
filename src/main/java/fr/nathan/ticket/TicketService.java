package fr.nathan.ticket;


import fr.nathan.salle.Salle;
import fr.nathan.salle.SalleRepository;
import fr.nathan.ticket.Ticket;
import fr.nathan.ticket.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Film non trouvé"
                        )
                );
    }

    public void deleteById(Long id) {
        Ticket ticket = this.findById(id);
        ticketRepository.delete(ticket);
    }

    public Ticket update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

}