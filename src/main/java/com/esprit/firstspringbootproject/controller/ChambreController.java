package com.esprit.firstspringbootproject.controller;

import com.esprit.firstspringbootproject.entities.Bloc;
import com.esprit.firstspringbootproject.entities.Chambre;
import com.esprit.firstspringbootproject.entities.TypeChambre;
import com.esprit.firstspringbootproject.services.IChambreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chambre")
@AllArgsConstructor
public class ChambreController {
    @Autowired
    IChambreService chambreService;

    @PostMapping("/add-chambre")
    public Chambre addchambre(@RequestBody Chambre c){
        return chambreService.addChambre(c);
    }
    @PutMapping("/update-chambre")
    public Chambre updatechambre(@RequestBody Chambre c){
        return chambreService.updateChambre(c);
    }
    @GetMapping("/display-chambre")
    public List<Chambre> dispalychambres(){
        return chambreService.retrieveAllChambres();
    }
    @GetMapping("/display-chambrebyid/{id}")
    public Chambre displaychambrebyid(@PathVariable("id") long idChambre){
        return chambreService.retrieveChambre(idChambre);
    }

    @GetMapping("/chambres-non-reservees/{nomUniversite}/{typeChambre}")
    public List<Chambre> getChambresNonReservees(@PathVariable String nomUniversite, @PathVariable TypeChambre typeChambre) {
        return chambreService.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, typeChambre);
    }
    @GetMapping("/chambres-par-bloc/{idBloc}/{typeChambre}")
    public List<Chambre> getChambresParBloc(@PathVariable long idBloc, @PathVariable TypeChambre typeChambre) {
        return chambreService.getChambresParBlocEtType(idBloc, typeChambre);
    }
}