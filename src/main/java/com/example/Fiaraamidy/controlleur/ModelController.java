package com.example.Fiaraamidy.controlleur;

import com.example.Fiaraamidy.entites.Energie;
import com.example.Fiaraamidy.entites.Model;
import com.example.Fiaraamidy.service.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "model")
public class ModelController {
    private ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "insert")
    public void creer(@RequestBody Model model){
        this.modelService.creer(model);
    }

    @GetMapping(path = "get")
    public List<Model> getAll(){
        return this.modelService.getAll();

    }

    @GetMapping(path = "getByMarque/{id}")
    public List<Model> getAll(@PathVariable int id){
        return this.modelService.getAllByMarque(id);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping(path = "update/{id}", consumes = APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable int id, @RequestBody Model model){
        this.modelService.update(id,model);
    }

    @GetMapping(path = "getModelById/{id}")
    public Model get(@PathVariable int id){
        return this.modelService.getById(id);
    }
}
