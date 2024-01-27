package com.example.Fiaraamidy.controlleur;

import com.example.Fiaraamidy.entites.Energie;
import com.example.Fiaraamidy.entites.Marque;
import com.example.Fiaraamidy.service.MarqueService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "marque")
public class MarqueController {

    private MarqueService marqueService;

    public MarqueController(MarqueService marqueService) {
        this.marqueService = marqueService;
    }

    @PostMapping(path = "insert")
    public void creer(@RequestBody Marque marque){
        this.marqueService.creer(marque);
    }

    @GetMapping(path="get",produces = APPLICATION_JSON_VALUE)
    public List<Marque> getALl(){
        return marqueService.getAll();
    }

    @GetMapping(path="get/{id}",produces = APPLICATION_JSON_VALUE)
    public Marque get(@PathVariable int id){
        return marqueService.getById(id);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping(path = "update/{id}", consumes = APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable int id, @RequestBody Marque marque){
        this.marqueService.update(id,marque);
    }


}
