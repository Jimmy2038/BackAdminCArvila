package com.example.Fiaraamidy.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "transmission")
public class Transmission {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idTransmission;

    private String nomTransmission;


    public Transmission(int idTransmision, String nomTransmission) {
        this.idTransmission = idTransmision;
        this.nomTransmission = nomTransmission;
    }

    public int getIdTransmision() {
        return idTransmission;
    }

    public void setIdTransmision(int idTransmision) {
        this.idTransmission = idTransmision;
    }

    public String getNomTransmission() {
        return nomTransmission;
    }

    public void setNomTransmission(String nomTransmission) {
        this.nomTransmission = nomTransmission;
    }

    public Transmission() {
    }


}
