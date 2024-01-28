package com.example.Fiaraamidy.service;

import com.example.Fiaraamidy.entites.Utilisateur;
import com.example.Fiaraamidy.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UtilisateurService {
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur getById(int id){
        Optional<Utilisateur> user = this.utilisateurRepository.findById(id);
        if(user.isPresent())
        {
            return user.get();
        }
        throw new RuntimeException("Utilisateur not found");
    }

    public Utilisateur login(Utilisateur user){
        Utilisateur ur = utilisateurRepository.findByEmail(user.getEmail())
                .orElseThrow(()->new RuntimeException("User not found"));

        if (passwordEncoder.matches(user.getMdp(), ur.getMdp())) {
            return ur;
        }
        throw new RuntimeException("Unknown password");
    }

    public Utilisateur loginAdmin(Utilisateur user){
        Utilisateur ur = utilisateurRepository.findByEmail(user.getEmail())
                .orElseThrow(()->new RuntimeException("User not found"));
        if (ur.getRole()==0){
            throw new RuntimeException("permission refuser");
        }
        if (passwordEncoder.matches(user.getMdp(), ur.getMdp())) {
            return ur;
        }
        throw new RuntimeException("Unknown password");
    }

    public Utilisateur inscription(Utilisateur utilisateur) {

        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Cet utilisateur existe déjà !");
        }

        return utilisateurRepository.save(utilisateur);
    }

}
