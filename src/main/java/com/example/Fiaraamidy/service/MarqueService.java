package com.example.Fiaraamidy.service;

import com.example.Fiaraamidy.entites.Energie;
import com.example.Fiaraamidy.entites.Marque;
import com.example.Fiaraamidy.repository.MarqueRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MarqueService {
    private MarqueRepository marqueRepository;

    public MarqueService(MarqueRepository marqueRepository) {
        this.marqueRepository = marqueRepository;
    }

    public void creer(Marque marque){
        this.marqueRepository.save(marque);
    }

    public List<Marque> getAll(){
        return this.marqueRepository.findAll();
    }

    public Marque getById(int id){
        Optional<Marque> optionalMarque=this.marqueRepository.findById(id);
        if(optionalMarque.isPresent()){
            return optionalMarque.get();
        }
        throw new RuntimeException("Marque not fount");
    }

    public void update(int id, Marque marque){
        Marque marqueBdd = this.getById(id);
        if (marqueBdd.getIdMarque() == marque.getIdMarque()){
            marqueBdd.setNomMarque(marque.getNomMarque());

            this.marqueRepository.save(marqueBdd);
        }
    }
}
