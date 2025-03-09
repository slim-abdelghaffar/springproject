package com.esprit.firstspringbootproject.controller;

import com.esprit.firstspringbootproject.entities.Etudiant;
import com.esprit.firstspringbootproject.entities.Universite;
import com.esprit.firstspringbootproject.services.IUniversiteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universitie")
public class UniversitieController {

    @Autowired
    IUniversiteService universiteService;
    @PostMapping("/add-universitie")
    public Universite adduniversitie(@RequestBody Universite u){
        return universiteService.addUniversite(u);
    }

    @PutMapping("/update-universitie")
    public Universite updateuniversitie(@RequestBody Universite u){
        return universiteService.updateUniversite(u);
    }

    @GetMapping("/display-universitie")
    public List<Universite> displayuniversities(){
        return universiteService.retrieveAllUniversities();
    }

    @GetMapping("/display-universitiebyid/{id}")
    public Universite displayuniversitiebyid(@PathVariable("id") long idUniversite){
        return universiteService.retrieveUniversite(idUniversite);
    }

    @PutMapping("/affecter-foyer/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable long idFoyer, @PathVariable String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }



}