package com.example.Fiaraamidy.repository;

import com.example.Fiaraamidy.entites.Favoris;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavorisRepository extends JpaRepository<Favoris,Integer> {
    List<Favoris> findByIdUtilisateur(int id);
}
