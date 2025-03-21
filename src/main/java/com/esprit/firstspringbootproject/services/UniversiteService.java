package com.esprit.firstspringbootproject.services;

import com.esprit.firstspringbootproject.entities.Bloc;
import com.esprit.firstspringbootproject.entities.Foyer;
import com.esprit.firstspringbootproject.entities.Universite;
import com.esprit.firstspringbootproject.repository.IBlocRepository;
import com.esprit.firstspringbootproject.repository.IFoyerRepository;
import com.esprit.firstspringbootproject.repository.IUniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
public class UniversiteService implements IUniversiteService{

    @Autowired
    IUniversiteRepository universiteRepository;

    @Autowired
    IFoyerRepository foyerRepository;

    @Autowired
    IBlocRepository blocRepository;

    @Override
    public List<Universite> retrieveAllUniversities() {
        return (List<Universite>) universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversite(long idUniversite) {
        return universiteRepository.findById(idUniversite).orElse(null);
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).get();
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);

        if (foyer == null || universite == null) {
            throw new RuntimeException("Foyer ou Université introuvable !");
        }
        universite.setFoyer(foyer);

        return universiteRepository.save(universite);
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        if (universite == null) {
            throw new RuntimeException("Université introuvable !");
        }
        universite.setFoyer(null);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        Foyer savedFoyer = foyerRepository.save(foyer);

        universite.setFoyer(savedFoyer);
        universiteRepository.save(universite);
        Set<Bloc> blocs = foyer.getBlocs();
        if (blocs != null) {
            for (Bloc bloc : blocs) {
                bloc.setFoyer(savedFoyer);
                blocRepository.save(bloc);
            }
        }

        savedFoyer.setBlocs(blocs);
        foyerRepository.save(savedFoyer);

        return universite;

    }
}