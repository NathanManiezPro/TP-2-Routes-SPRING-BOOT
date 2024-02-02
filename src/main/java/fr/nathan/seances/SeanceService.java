package fr.nathan.seances;


import fr.nathan.film.FilmService;
import fr.nathan.film.exceptions.BadRequestException;
import fr.nathan.salle.Salle;
import fr.nathan.salle.SalleService;
import fr.nathan.seances.dto.SeanceReserverTicketPourSeanceDto;
import fr.nathan.ticket.Ticket;
import fr.nathan.ticket.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeanceService {
    private final SeanceRepository seanceRepository;
    private final SalleService salleService;
    private final TicketService ticketService;

    public SeanceService(SeanceRepository seanceRepository, SalleService salleService, FilmService filmService, TicketService ticketService) {
        this.seanceRepository = seanceRepository;
        this.salleService = salleService;
        this.ticketService = ticketService;
    }


    public List<Seance> findAll(){
        return seanceRepository.findAll();
    }

    public Seance save(Seance seance) {

        // recup le nb de place ds la salle
        Salle salleDispo = salleService.findById(seance.getSalle().getId());
        // donner ce nb de place a placesDisponible, un set
        seance.setPlacesDisponibles(salleDispo.getCapacite());
        VerifySeance(seance);
        return seanceRepository.save(seance);
    }

    private static void VerifySeance(Seance seance) {
        List<String> erreurs = new ArrayList<>();
        if(seance.getSalle() == null){
            erreurs.add("La salle n'existe pas pour cette seance");
        }
        if(seance.getFilm() == null){
            erreurs.add("Le film n'existe pas pour cette séance");
        }
        if(seance.getDate().before(new Date())){
            erreurs.add("La date n'est pas disponible car elle est dans le passé");
        }
        if(seance.getPrix() < 0){
            erreurs.add("Le prix n'est pas le bon, il faut qu'il soit supérieur a 0");
        }

        if(!erreurs.isEmpty()){
            throw new BadRequestException(erreurs);
        }
    }

//    public SeanceReserverTicketPourSeanceDto ReservTicket(Long id, Ticket ticket){
 //       Seance seance = this.findById(id);
    //    Ticket ticketid = ticketService.findById(ticket.getId());
      //  seance.getTicket().add(ticketid);
//SeanceReserverTicketPourSeanceDto seanceReserverTicketPourSeanceDto =  new SeanceReserverTicketPourSeanceDto();
       // seanceReserverTicketPourSeanceDto.setTicket(
    //   seance.getTicket().stream().map(
               //     ticket1
       // );


   // }

    public Seance findById(Long id) {
        return seanceRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Film non trouvé"
                        )
                );
    }

    public void deleteById(Long id) {
        Seance seance = this.findById(id);
        seanceRepository.delete(seance);
    }

    public Seance update(Seance seance) {
        return seanceRepository.save(seance);
    }


}