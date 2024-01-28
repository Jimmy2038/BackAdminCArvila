package com.example.Fiaraamidy.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table( name = "annonce")
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAnnonce;


    private int idUtilisateur;

    private LocalDateTime daty;

    @ManyToOne
    @JoinColumn(name = "id_model")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "id_energie")
    private Energie energie;

    @ManyToOne
    @JoinColumn(name = "id_transmission")
    private Transmission transmission;

    private int kilometrage;

    private int annee;

    private String descri;

    private double prix;

    private int etat;

    private LocalDateTime dateValidation;

    private LocalDateTime dateVente;

    @Transient
    private String etatString;

    @Transient
    private List<PhotoAnnonce> photos;


    public String getEtatString()
    {
        if(etat==0){
            this.setEtatString("Non valide");
        }else if(etat==5){
            this.setEtatString("Valide");
        }else if(etat==10) {
            this.setEtatString("Vendu");
        }
        return etatString;
    }

    public Annonce(int idAnnonce,int idUtilisateur, LocalDateTime daty, Model model, Energie energie, Transmission transmission, int kilometrage, int annee, String descri, double prix, int etat, LocalDateTime dateValidation, LocalDateTime dateVente) {
        this.idAnnonce = idAnnonce;
        this.daty = daty;
        this.model = model;
        this.idUtilisateur=idUtilisateur;
        this.energie = energie;
        this.transmission = transmission;
        this.kilometrage = kilometrage;
        this.annee = annee;
        this.descri = descri;
        this.prix = prix;
        this.etat = etat;
        this.dateValidation = dateValidation;
        this.dateVente = dateVente;
    }

    public Annonce() {
    }


}
