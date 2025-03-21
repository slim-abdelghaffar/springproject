package com.esprit.firstspringbootproject.services;

import com.esprit.firstspringbootproject.entities.Bloc;
import com.esprit.firstspringbootproject.entities.Etudiant;
import com.esprit.firstspringbootproject.entities.Reservation;
import com.esprit.firstspringbootproject.repository.IBlocRepository;
import com.esprit.firstspringbootproject.repository.IChambreRepository;
import com.esprit.firstspringbootproject.repository.IEtudiantRepository;
import com.esprit.firstspringbootproject.repository.IReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService{

    @Autowired
    IReservationRepository reservationRepository;

    @Autowired
    IChambreRepository chambreRepository;

    @Autowired
    IEtudiantRepository etudiantRepository;

    @Autowired
    IBlocRepository blocRepository;
    @Override
    public List<Reservation> retrieveAllReservation() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversitaire, String nomUniversite) {
        return reservationRepository.findReservationsByAnneeUniversitaire(anneeUniversitaire);
    }

    @Override
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        // Vérifier si l'étudiant existe
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(cinEtudiant);
        if (!etudiantOpt.isPresent()) {
            throw new RuntimeException("Étudiant non trouvé avec CIN : " + cinEtudiant);
        }
        Etudiant etudiant = etudiantOpt.get();

        // Vérifier si le bloc existe
        Optional<Bloc> blocOpt = blocRepository.findById(idBloc);
        if (!blocOpt.isPresent()) {
            throw new RuntimeException("Bloc non trouvé avec ID : " + idBloc);
        }
        Bloc bloc = blocOpt.get();

        // Générer le numéro de réservation (numChambre-nomBloc-anneeUniversitaire)
        String numReservation = String.format("%d-%s-%tY",
                idBloc, // Utilisation de l'ID du bloc au lieu de la chambre
                bloc.getNomBloc(),
                new Date());

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setIdReservation(numReservation);
        reservation.setAnneeUniversitaire(new Date()); // Date actuelle
        reservation.setEstValide(true);  // L'ajout de la réservation est valide par défaut
        reservation.setEtudiants(new HashSet<>());  // Créer un ensemble d'étudiants

        // Ajouter l'étudiant à la réservation
        reservation.getEtudiants().add(etudiant);

        // Sauvegarder la réservation
        return reservationRepository.save(reservation);
    }



}