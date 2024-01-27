package com.example.Fiaraamidy.repository;

import com.example.Fiaraamidy.entites.PhotoAnnonce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoAnnonceRepository extends JpaRepository<PhotoAnnonce,Integer> {
    List<PhotoAnnonce> findPhotoAnnonceByIdAnnonce(int idAnnonce);
}
