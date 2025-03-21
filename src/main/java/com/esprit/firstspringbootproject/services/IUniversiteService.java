package com.esprit.firstspringbootproject.services;

import com.esprit.firstspringbootproject.entities.Foyer;
import com.esprit.firstspringbootproject.entities.Universite;
import com.esprit.firstspringbootproject.repository.IFoyerRepository;
import com.esprit.firstspringbootproject.repository.IUniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversities();
    Universite addUniversite (Universite u);
    Universite updateUniversite (Universite u);
    Universite retrieveUniversite (long idUniversite);

    Universite affecterFoyerAUniversite (long idFoyer, String nomUniversite) ;
    Universite desaffecterFoyerAUniversite (long idUniversite) ;
    Universite ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite);

}