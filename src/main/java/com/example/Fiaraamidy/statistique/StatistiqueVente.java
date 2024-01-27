package com.example.Fiaraamidy.statistique;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "v_nb_vendu")
public class StatistiqueVente {
    @Id
    int etat;
    @Column(name = "count")
    int nb;
    @Transient
    double pourcentage;

}
