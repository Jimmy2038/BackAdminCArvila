package com.example.Fiaraamidy.entites;

import jakarta.persistence.*;

@Entity
@Table(name="energie")
public class Energie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEnergie;

    private String nomEnergie;

    public int getIdEnergie() {
        return idEnergie;
    }

    public void setIdEnergie(int idEnergie) {
        this.idEnergie = idEnergie;
    }

    public String getNomEnergie() {
        return nomEnergie;
    }

    public void setNomEnergie(String nomEnergie) {
        this.nomEnergie = nomEnergie;
    }

    public Energie() {
    }

    public Energie(int idEnergie, String nomEnergie) {
        this.idEnergie = idEnergie;
        this.nomEnergie = nomEnergie;
    }
}
