package com.esprit.firstspringbootproject.controller;

import com.esprit.firstspringbootproject.entities.Foyer;
import com.esprit.firstspringbootproject.entities.Reservation;
import com.esprit.firstspringbootproject.services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    IReservationService reservationService;

    @GetMapping("/dispaly-reservations")
    public List<Reservation> displayreservation(){
        return reservationService.retrieveAllReservation();
    }

    @PutMapping("/update-reservation")
    public Reservation updatereservation(Reservation r){
        return reservationService.updateReservation(r);
    }
    @GetMapping("/dispaly-reservationbyid/{id}")
    public Reservation displayreservationbyid(@PathVariable("id") String idReservation){
        return reservationService.retrieveReservation(idReservation);
    }
    @GetMapping("/reservations/{anneeUniversitaire}/{nomUniversite}")
    public List<Reservation> getReservationsParAnneeUniversitaire(@PathVariable Date anneeUniversitaire, @PathVariable String nomUniversite) {
        return reservationService.getReservationParAnneeUniversitaireEtNomUniversite(anneeUniversitaire, nomUniversite);
    }
}