package com.example.Fiaraamidy.service;

import com.example.Fiaraamidy.repository.StatistiqueVenteRepository;
import com.example.Fiaraamidy.statistique.StatistiqueVente;
import com.example.Fiaraamidy.statistique.VenteMarque;
import com.example.Fiaraamidy.repository.VenteMarqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatistiqueService {
    private VenteMarqueRepository venteMarqueRepository;
    private StatistiqueVenteRepository statistiqueVenteRepository;

    public List<VenteMarque> getVenteMarque()
    {
        return venteMarqueRepository.findAll();
    }

    public List<StatistiqueVente> getStatVente()
    {
        List<StatistiqueVente> liste =statistiqueVenteRepository.findAll();
        if(liste.size()==1){
            if(liste.get(0).getEtat()==5){
                liste.get(0).setPourcentage(100);
                StatistiqueVente stat=new StatistiqueVente(10,0,0);
                liste.add(stat);
                return liste;
            }else{
                StatistiqueVente statnv=new StatistiqueVente(5,0,0);
                StatistiqueVente statv=liste.get(0);
                statv.setPourcentage(100);
                liste.clear();
                liste.add(statnv);
                liste.add(statv);
                return liste;
            }
        }else if(liste.size()==0){
            StatistiqueVente statnv=new StatistiqueVente(5,0,0);
            StatistiqueVente statv=new StatistiqueVente(10,0,0);
            liste.add(statnv);
            liste.add(statv);
            return liste;
        }
        double pnv = (liste.get(0).getNb()*100)/(liste.get(0).getNb()+liste.get(1).getNb());
        double pv = (liste.get(1).getNb()*100)/(liste.get(0).getNb()+liste.get(1).getNb());
        liste.get(0).setPourcentage(pnv);
        liste.get(1).setPourcentage(pv);
        return liste;
    }

}
