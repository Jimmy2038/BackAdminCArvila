package com.example.Fiaraamidy.repository;

import com.example.Fiaraamidy.entites.Annonce;
import com.example.Fiaraamidy.entites.Token;
import com.example.Fiaraamidy.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    Optional<Token> findByToken(String token);

}
