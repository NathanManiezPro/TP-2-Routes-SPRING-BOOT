package fr.nathan.ticket;


import fr.nathan.film.exceptions.BadRequestException;
import fr.nathan.salle.Salle;
import fr.nathan.salle.SalleRepository;
import fr.nathan.seances.SeanceService;
import fr.nathan.ticket.Ticket;
import fr.nathan.ticket.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeanceService seanceService;

    public TicketService(TicketRepository ticketRepository, SeanceService seanceService) {
        this.ticketRepository = ticketRepository;
        this.seanceService = seanceService;
    }


    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public Ticket save(Ticket ticket) {
        VerifyTicket(ticket);
        return ticketRepository.save(ticket);
    }

    private static void VerifyTicket(Ticket ticket) {
        List<String> erreurs = new ArrayList<>();
        if(ticket.getSeance() == null){
            erreurs.add("Il n'y a pas de ticket disponible pour cette séance");
        }
        if(ticket.getNombrePlaces() < 0){
            erreurs.add("Il n'y a plus de place");
        }
        if(ticket.getNombrePlaces()<= ticket.getSeance().getPlacesDisponibles()){
            erreurs.add("Le nombre de places demandé est supérieur au nombres de places restantes");
        }
        if(ticket.getNomClient() == null){
            erreurs.add("Il faut renseigner votre nom");
        }

        if(!erreurs.isEmpty()){
            throw new BadRequestException(erreurs);
        }
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