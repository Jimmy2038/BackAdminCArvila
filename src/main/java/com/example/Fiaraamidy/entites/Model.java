package com.example.Fiaraamidy.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModel;
    private String nomModel;
    @ManyToOne
    @JoinColumn(name = "marque_id")
    Marque marque;

    public Model() {
    }

    public Model(int idModel, String nomModel, Marque marque) {
        this.idModel = idModel;
        this.nomModel = nomModel;
        this.marque = marque;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public String getNomModel() {
        return nomModel;
    }

    public void setNomModel(String nomModel) {
        this.nomModel = nomModel;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }
}
