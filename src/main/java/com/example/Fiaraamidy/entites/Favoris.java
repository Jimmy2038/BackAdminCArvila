package com.example.Fiaraamidy.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Favoris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int idUtilisateur;
    @ManyToOne
    @JoinColumn(name = "id_annonce")
    Annonce annonce;
}
