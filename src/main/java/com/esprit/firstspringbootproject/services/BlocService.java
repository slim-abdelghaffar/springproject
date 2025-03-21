package com.esprit.firstspringbootproject.services;

import com.esprit.firstspringbootproject.entities.Bloc;
import com.esprit.firstspringbootproject.entities.Chambre;
import com.esprit.firstspringbootproject.repository.IBlocRepository;
import com.esprit.firstspringbootproject.repository.IChambreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BlocService implements IBlocService{

    @Autowired
    IBlocRepository blocRepository;
    @Autowired
    IChambreRepository chambreRepository;
    @Override
    public List<Bloc> retrieveBlocs() {
        return (List<Bloc>)blocRepository.findAll();
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc retrieveBloc(long idBloc) {
        return blocRepository.findById(idBloc).orElse(null);
    }

    @Override
    public void removeBloc(long idBloc) {
        blocRepository.deleteById(idBloc);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambres, long idBloc) {

        Bloc bloc = blocRepository.findById(idBloc).orElse(null);

        List<Chambre> chambres = chambreRepository.findByNumeroChambreIn(numChambres);

        if (chambres.isEmpty()) {
            throw new RuntimeException("Aucune chambre trouvée avec les numéros fournis.");
        }

        for (Chambre chambre : chambres) {
            chambre.setBloc(bloc);
        }

        chambreRepository.saveAll(chambres);

        Set<Chambre> updatedChambres = chambres.stream().collect(Collectors.toSet());
        bloc.setChambres(updatedChambres);

        return blocRepository.save(bloc);
    }
}