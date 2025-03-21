package com.esprit.firstspringbootproject.controller;

import com.esprit.firstspringbootproject.entities.Bloc;
import com.esprit.firstspringbootproject.services.IBlocService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocController {
    @Autowired
    IBlocService blocService;

    @PostMapping("/add-bloc")
    public Bloc addbloc(@RequestBody Bloc bloc){
        return blocService.addBloc(bloc);
    }
    @PutMapping("/update-bloc")
    public Bloc updatebloc(@RequestBody Bloc bloc){
        return blocService.updateBloc(bloc);
    }

    @GetMapping("/display-bloc")
    public List<Bloc> displaybloc() {
        return blocService.retrieveBlocs();
    }

    @GetMapping("/display-blocbyid/{id}")
    public Bloc displayblocbyid(@PathVariable("id") long idBloc) {
        return blocService.retrieveBloc(idBloc);
    }

    @DeleteMapping("/delete-bloc/{id}")
    public void deletebloc(@PathVariable("id") long idBloc) {
        blocService.removeBloc(idBloc);
    }

    @PutMapping("/affecter-chambres/{idBloc}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambres, @PathVariable long idBloc) {
        return blocService.affecterChambresABloc(numChambres, idBloc);
    }

}