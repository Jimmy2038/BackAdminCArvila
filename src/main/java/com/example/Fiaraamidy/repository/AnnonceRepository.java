package com.example.Fiaraamidy.repository;

import com.example.Fiaraamidy.entites.Annonce;
import com.example.Fiaraamidy.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce,Integer>, JpaSpecificationExecutor<Annonce> {
    List<Annonce> findAnnonceByIdUtilisateur(int id);
    List<Annonce> findAnnoncesByEtatEquals(int etat);

    List<Annonce> findByIdUtilisateurAndEtat(int idU,int etat);

    List<Annonce> findByIdUtilisateurNotAndEtat(int idU,int etat);

}
