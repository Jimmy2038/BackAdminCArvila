package com.example.Fiaraamidy.service;

import com.example.Fiaraamidy.entites.Energie;
import com.example.Fiaraamidy.repository.EnergieRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EnergieService {
    private EnergieRepository energieRepository;

    public EnergieService(EnergieRepository energieRepository) {
        this.energieRepository = energieRepository;
    }

    public void insert(Energie energie){
        this.energieRepository.save(energie);
    }

    public Energie getById(int id){
        Optional<Energie> optionalEnergie=this.energieRepository.findById(id);
        if(optionalEnergie.isPresent()){
            return optionalEnergie.get();
        }
        throw new RuntimeException("Enerigie not fount");
    }

    public void update(int id, Energie energie){
        Energie energieBdd = this.getById(id);
        if (energieBdd.getIdEnergie() == energie.getIdEnergie()){
            energieBdd.setNomEnergie(energie.getNomEnergie());

            this.energieRepository.save(energieBdd);
        }
    }

    public Energie[] getAll(){
        List<Energie> energies = this.energieRepository.findAll();
        return energies.toArray(new Energie[energies.size()]);
    }
}
