package com.example.Fiaraamidy.controlleur;

import com.example.Fiaraamidy.entites.Annonce;
import com.example.Fiaraamidy.entites.PhotoAnnonce;
import com.example.Fiaraamidy.entites.Transmission;
import com.example.Fiaraamidy.model.UploadPhoto;
import com.example.Fiaraamidy.recherche.Recherche;
import com.example.Fiaraamidy.service.AnnonceService;
import com.example.Fiaraamidy.service.PhotoAnnonceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "annonce")
public class AnnonceController {
    private AnnonceService annonceService;
    private PhotoAnnonceService photoAnnonceService;

    public AnnonceController(AnnonceService annonceService, PhotoAnnonceService photoAnnonceService) {
        this.annonceService = annonceService;
        this.photoAnnonceService = photoAnnonceService;
    }

    @GetMapping(path = "getAllPhoto")
    public List<Annonce> all()
    {
        return this.annonceService.getAll();
    }

    @GetMapping(path = "getNonValide")
    public List<Annonce> allNV()
    {
        return this.annonceService.getNonValide();
    }

    @GetMapping(path = "getAll")
    public List<Annonce> alll()
    {
        return this.annonceService.getAllSansPhoto();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "insert")
    public String cree(@RequestBody Annonce annonce)
    {
        try{
            this.annonceService.create(annonce);
            return "Creation annonce reussie";
        } catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping(path = "getAllByUser/{idUser}")
    public List<Annonce> allByUser(@PathVariable int idUser)
    {
        return this.annonceService.getAllByUser(idUser);
    }

    @GetMapping(path = "getByIdAnnonce/{id}")
    public Annonce getByIdAnnonce(@PathVariable int id)
    {
        return this.annonceService.getById(id);
    }

    @GetMapping(path = "recherche")
    public List<Annonce> recherche(@RequestBody Recherche recherche)
    {
        return this.annonceService.searchAnnonces(recherche);
    }

    @GetMapping(path = "All")
    public List<Annonce> nonValide()
    {
        return this.annonceService.getByEtat(0);
    }

    @GetMapping(path = "valide")
    public List<Annonce> Valide()
    {
        return this.annonceService.getByEtat(5);
    }

    @GetMapping(path = "nonValide")
    public List<Annonce> nValide()
    {
        return this.annonceService.getByEtat(0);
    }

    @GetMapping(path = "valide/{idU}")
    public List<Annonce> Valide(@PathVariable int idU)
    {

            return this.annonceService.getAllByEtatByUser(idU);

    }

    @PutMapping(path = "valide/{id}")
    public String validation(@PathVariable int id)
    {
        try{
            this.annonceService.valide(id);
            return "Validation reussie";
        } catch (Exception e){
            return e.getMessage();
        }
    }




    @GetMapping(path = "photo/{id}")
    public List<PhotoAnnonce> photo(@PathVariable int id){
        try{
            return this.photoAnnonceService.getPhotoByIdAnnonce(id);
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @GetMapping(path = "photo/getAll")
    public List<PhotoAnnonce> photo(){
        return this.photoAnnonceService.getAllPhoto();
    }

    @PostMapping(path = "insertPhoto")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void insertPhoto(@RequestBody UploadPhoto uploadPhoto){
        try{
            this.photoAnnonceService.ajouterImage(uploadPhoto);
        }catch (Exception e){
            System.out.println("olana: "+e.getMessage());
        }
    }


}

