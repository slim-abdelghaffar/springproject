package com.esprit.firstspringbootproject.controller;
import com.esprit.firstspringbootproject.entities.Etudiant;
import com.esprit.firstspringbootproject.services.IEtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantController {
    @Autowired
    IEtudiantService etudiantService;

    @PostMapping("/add-etudiants")
    public List<Etudiant> addEtudiants(@RequestBody List<Etudiant> etudiants) {
        return etudiantService.addEtudiants(etudiants);
    }

    @PutMapping("/update-etudiant")
    public Etudiant updateetudiant(@RequestBody Etudiant e){
        return etudiantService.updateEtudiant(e);
    }

    @GetMapping("/display-etudiant")
    public List<Etudiant> displayetudiants(){
        return etudiantService.retrieveAllEtudiants();
    }
    @GetMapping("/display-etudiantbyid/{id}")
    public Etudiant displyetudiantbyid(@PathVariable("id") long idEtudiant){
        return etudiantService.retrieveEtudiant(idEtudiant);
    }

    @DeleteMapping("/delete-etudiantbyid/{id}")
    public void deleteetudiant(@PathVariable("id") long idEtudiant){
        etudiantService.removeEtudiant(idEtudiant);
    }

}