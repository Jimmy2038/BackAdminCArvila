package com.example.Fiaraamidy.repository;

import com.example.Fiaraamidy.entites.Marque;
import com.example.Fiaraamidy.entites.Model;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ModelRepository extends JpaRepository<Model,Integer> {
    List<Model> findModelByMarque(Marque marque);
}
