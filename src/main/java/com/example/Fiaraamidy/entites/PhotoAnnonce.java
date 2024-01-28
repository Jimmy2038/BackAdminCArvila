package com.example.Fiaraamidy.entites;

import jakarta.persistence.*;


@Entity
@Table(name = "sary")
public class PhotoAnnonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSary;


    @JoinColumn(name = "id_annonce")
    private int idAnnonce;
    private String nom;
    private int taille;
    private String type;
    private String bin;

    public int getIdSary() {
        return idSary;
    }

    public void setIdSary(int idSary) {
        this.idSary = idSary;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBin() {

        return bin;
    }
    public String getBinDecode() {

        return "data:image/jpeg;base64,"+bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public PhotoAnnonce(int idSary, int idAnnonce, String nom, int taille, String type, String bin) {
        this.idSary = idSary;
        this.idAnnonce = idAnnonce;
        this.nom = nom;
        this.taille = taille;
        this.type = type;
        this.bin = bin;
    }

    public PhotoAnnonce(int idAnnonce, String nom, int taille, String type, String bin) {
        this.idAnnonce = idAnnonce;
        this.nom = nom;
        this.taille = taille;
        this.type = type;
        this.bin = bin;
    }

    public PhotoAnnonce() {
    }


}
