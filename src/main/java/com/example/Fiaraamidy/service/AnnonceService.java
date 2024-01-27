package com.example.Fiaraamidy.service;

import com.example.Fiaraamidy.entites.Annonce;
import com.example.Fiaraamidy.entites.PhotoAnnonce;
import com.example.Fiaraamidy.entites.Utilisateur;
import com.example.Fiaraamidy.recherche.Recherche;
import com.example.Fiaraamidy.repository.AnnonceRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnnonceService {
    private AnnonceRepository annonceRepository;
    private ModelService modelService;
    private TransmissionService transmissionService;
    private EnergieService energieService;
    private PhotoAnnonceService photoAnnonceService;
    private UtilisateurService utilisateurService;

    public AnnonceService(AnnonceRepository annonceRepository, ModelService modelService, TransmissionService transmissionService, EnergieService energieService, PhotoAnnonceService photoAnnonceService, UtilisateurService utilisateurService) {
        this.annonceRepository = annonceRepository;
        this.modelService = modelService;
        this.transmissionService = transmissionService;
        this.energieService = energieService;
        this.photoAnnonceService = photoAnnonceService;
        this.utilisateurService = utilisateurService;
    }

    public List<Annonce> getAll()
    {
        List<Annonce> all = this.annonceRepository.findAll();
        for(int i=0;i<all.size();i++){
            List<PhotoAnnonce> photos =  this.photoAnnonceService.getPhotoByIdAnnonce(all.get(i).getIdAnnonce());
            all.get(i).setPhotos(photos);
        }
        return all;
    }

    public List<Annonce> getNonValide()
    {
        List<Annonce> all = this.getByEtat(0);
        for(int i=0;i<all.size();i++){
            List<PhotoAnnonce> photos =  this.photoAnnonceService.getPhotoByIdAnnonce(all.get(i).getIdAnnonce());
            all.get(i).setPhotos(photos);
        }
        return all;
    }

    public List<Annonce> getAllSansPhoto()
    {
        List<Annonce> all = this.annonceRepository.findAll();
        for(int i=0;i<all.size();i++){
            List<PhotoAnnonce> photos =  this.photoAnnonceService.getPhotoByIdAnnonce(all.get(i).getIdAnnonce());
            for (int j=0; j<photos.size(); j++){
                photos.get(j).setBin("...");
            }
            all.get(i).setPhotos(photos);
        }
        return all;
    }
    public void create(Annonce annonce)
    {
        transmissionService.getById(annonce.getTransmission().getIdTransmision());
        energieService.getById(annonce.getEnergie().getIdEnergie());
        modelService.getById(annonce.getModel().getIdModel());
        annonce.setEtat(0);
        this.annonceRepository.save(annonce);
    }

    public List<Annonce> getAllByUser(int idUser)
    {
        return this.annonceRepository.findAnnonceByIdUtilisateur(idUser);
    }

    public Annonce getById(int id)
    {
        Optional<Annonce> optionalAnnonce = this.annonceRepository.findById(id);
        if (optionalAnnonce.isPresent())
        {
            Annonce a = optionalAnnonce.get();
            List<PhotoAnnonce> photos =  this.photoAnnonceService.getPhotoByIdAnnonce(a.getIdAnnonce());
            a.setPhotos(photos);
            return  a;
        }
        throw new RuntimeException("Annonce not fount");
    }
    public List<Annonce> searchAnnonces(Recherche recherche) {

        Specification<Annonce> spec = Specification.where(null);

        spec = spec.and((root, query, builder) ->
                builder.between(root.get("kilometrage"), recherche.getKilMin(), recherche.getKilMax()));
        spec = spec.and((root, query, builder) ->
                builder.between(root.get("prix"),recherche.getPrixMin(),recherche.getPrixMax()));
        spec = spec.and((root, query, builder) ->
                builder.between(root.get("annee"),recherche.getAnneeMin(),recherche.getAnneeMax()));
        spec = spec.and((root, query, builder) ->
                builder.equal(root.get("etat"),5));

        if (recherche.getIdTransmission() != 0) {
            spec = spec.and((root, query, builder) ->
                    builder.equal(root.get("transmission").get("idTransmission"),recherche.getIdTransmission()));
        }
        if (recherche.getIdEnergie() != 0) {
            spec = spec.and((root, query, builder) ->
                    builder.equal(root.get("energie").get("idEnergie"),recherche.getIdEnergie()));
        }
        if(recherche.getIdMarque()!=0 && recherche.getIdModel()==0){
            spec = spec.and((root, query, builder) ->
                    builder.equal(root.get("model").get("marque").get("idMarque"),recherche.getIdMarque()));
        }
        if(recherche.getIdMarque()!=0 && recherche.getIdModel()!=0){
            spec = spec.and((root, query, builder) ->
                    builder.equal(root.get("model").get("idModel"),recherche.getIdModel()));
            spec = spec.and((root, query, builder) ->
                    builder.equal(root.get("model").get("marque").get("idMarque"),recherche.getIdMarque()));
        }
        return annonceRepository.findAll(spec);
    }

    public List<Annonce> getByEtat(int etat){
        return this.annonceRepository.findAnnoncesByEtatEquals(etat);
    }

    public void valide(int id)
    {
        Annonce annonce = this.getById(id);
        annonce.setEtat(5);
        annonce.setDateValidation(LocalDateTime.now());
        this.annonceRepository.save(annonce);
    }

    public void vendu(int id)
    {
        Annonce annonce = this.getById(id);
        annonce.setEtat(10);
        annonce.setDateVente(LocalDateTime.now());
        this.annonceRepository.save(annonce);
    }

    public List<Annonce> getHistoriqueVendu(int idU){
        utilisateurService.getById(idU);
        return this.annonceRepository.findByIdUtilisateurAndEtat(idU,10);
    }

    public List<Annonce> getAllByEtatByUser(int idU){
        return this.annonceRepository.findByIdUtilisateurNotAndEtat(idU,5);
    }

}
