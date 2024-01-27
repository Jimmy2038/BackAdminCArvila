package com.example.Fiaraamidy.recherche;

public class Recherche {
    int idMarque;
    int idModel;
    int idTransmission;
    int idEnergie;
    Long prixMin;
    Long prixMax;
    Long kilMin;
    Long kilMax;
    int anneeMin;
    int anneeMax;

    public Recherche(int idMarque, int idModel, int idTransmission, int idEnergie, Long prixMin, Long prixMax, Long kilMin, Long kilMax, int anneeMin, int anneeMax) {
        this.idMarque = idMarque;
        this.idModel = idModel;
        this.idTransmission = idTransmission;
        this.idEnergie = idEnergie;
        this.prixMin = prixMin;
        this.prixMax = prixMax;
        this.kilMin = kilMin;
        this.kilMax = kilMax;
        this.anneeMin = anneeMin;
        this.anneeMax = anneeMax;
    }

    public Recherche() {
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public int getIdTransmission() {
        return idTransmission;
    }

    public void setIdTransmission(int idTransmission) {
        this.idTransmission = idTransmission;
    }

    public int getIdEnergie() {
        return idEnergie;
    }

    public void setIdEnergie(int idEnergie) {
        this.idEnergie = idEnergie;
    }

    public Long getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(Long prixMin) {
        this.prixMin = prixMin;
    }

    public Long getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(Long prixMax) {
        this.prixMax = prixMax;
    }

    public Long getKilMin() {
        return kilMin;
    }

    public void setKilMin(Long kilMin) {
        this.kilMin = kilMin;
    }

    public Long getKilMax() {
        return kilMax;
    }

    public void setKilMax(Long kilMax) {
        this.kilMax = kilMax;
    }

    public int getAnneeMin() {
        return anneeMin;
    }

    public void setAnneeMin(int anneeMin) {
        this.anneeMin = anneeMin;
    }

    public int getAnneeMax() {
        return anneeMax;
    }

    public void setAnneeMax(int anneeMax) {
        this.anneeMax = anneeMax;
    }
}
