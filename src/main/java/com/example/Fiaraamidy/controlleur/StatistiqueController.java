package com.example.Fiaraamidy.controlleur;

import com.example.Fiaraamidy.statistique.StatistiqueVente;
import com.example.Fiaraamidy.statistique.VenteMarque;
import com.example.Fiaraamidy.service.StatistiqueService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class StatistiqueController {
    StatistiqueService statistiqueService;

    @GetMapping("/ByMarque")
    public List<VenteMarque> venteMarque(){
        try{
            List<VenteMarque> vm = statistiqueService.getVenteMarque();
            return vm;

        } catch (RuntimeException e) {
            return null;
        }
    }

    @GetMapping("/vente")
    public List<StatistiqueVente> vente(){
        try{
            List<StatistiqueVente> vm = statistiqueService.getStatVente();
            return vm;

        } catch (RuntimeException e) {
            return null;
        }
    }

}
