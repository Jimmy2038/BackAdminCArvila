package com.example.Fiaraamidy.service;

import com.example.Fiaraamidy.entites.Favoris;
import com.example.Fiaraamidy.repository.FavorisRepository;
import com.example.Fiaraamidy.repository.MarqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FavorisService {
    private FavorisRepository favorisRepository;
    private UtilisateurService utilisateurService;
    private AnnonceService annonceService;


    public void insert(Favoris favoris){
        utilisateurService.getById(favoris.getIdUtilisateur());
        annonceService.getById(favoris.getAnnonce().getIdAnnonce());
        this.favorisRepository.save(favoris);
    }

    public List<Favoris> getFavoris(int id){
        return this.favorisRepository.findByIdUtilisateur(id);
    }
}
