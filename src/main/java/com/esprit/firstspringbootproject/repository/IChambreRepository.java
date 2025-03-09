package com.esprit.firstspringbootproject.repository;

import com.esprit.firstspringbootproject.entities.Chambre;
import com.esprit.firstspringbootproject.entities.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface IChambreRepository extends JpaRepository<Chambre,Long> {
    @Query("SELECT c FROM Chambre c WHERE c.bloc.foyer.universite.nomUniversite = :nomUniversite AND c.typeC = :type AND c.reservations IS EMPTY")
    List<Chambre> findChambresNonReservees(String nomUniversite, TypeChambre type);

    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> findChambresParBlocEtTypeJPQL(long idBloc, TypeChambre typeC);

    List<Chambre> findByBlocIdBlocAndTypeC(long idBloc, TypeChambre typeC);

}
