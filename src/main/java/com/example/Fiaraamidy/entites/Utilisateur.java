package com.example.Fiaraamidy.entites;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "utilisateur")
@Builder
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUtilisateur;

    private String email;

    private String mdp;

    private int role;

    private String pseudo;

    public Utilisateur() {
    }


    public Utilisateur(int idUtilisateur, String email, String mdp, int role, String pseudo) {
        this.idUtilisateur = idUtilisateur;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.pseudo = pseudo;
    }

    public Utilisateur(int idUtilisateur, String pseudo) {
        this.idUtilisateur = idUtilisateur;
        this.email = email;
    }
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
