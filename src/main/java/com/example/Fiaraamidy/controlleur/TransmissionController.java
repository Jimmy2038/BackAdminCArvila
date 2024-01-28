package com.example.Fiaraamidy.controlleur;

import com.example.Fiaraamidy.entites.Transmission;
import com.example.Fiaraamidy.service.TransmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "transmission")
public class TransmissionController {
    private TransmissionService transmissionService;

    public TransmissionController(TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "insert")
    public void insert(@RequestBody Transmission transmission)
    {
        this.transmissionService.cree(transmission);
    }

    @GetMapping(path = "getAll")
    public List<Transmission> get()
    {
        return this.transmissionService.getAll();
    }

    @GetMapping(path = "getById/{id}")
    public Transmission get(@PathVariable int id)
    {
        return this.transmissionService.getById(id);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping(path = "update/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@PathVariable int id, @RequestBody Transmission transmission)
    {
        this.transmissionService.update(id,transmission);
    }


}
