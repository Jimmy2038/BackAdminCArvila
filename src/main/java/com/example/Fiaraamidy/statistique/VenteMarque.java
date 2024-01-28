package com.example.Fiaraamidy.statistique;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "v_nb_vente_par_marque")
public class VenteMarque {
    @Id
    int marqueId;
    String nomMarque;
    int nb;
    double prix;
}