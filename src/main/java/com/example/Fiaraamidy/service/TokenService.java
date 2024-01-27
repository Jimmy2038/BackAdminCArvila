package com.example.Fiaraamidy.service;

import com.example.Fiaraamidy.entites.Token;
import com.example.Fiaraamidy.entites.Utilisateur;
import com.example.Fiaraamidy.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public TokenRepository getTokenRepository() {
        return tokenRepository;
    }

    public void setTokenRepository(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token findByToken(String token){
        return tokenRepository.findByToken(token).orElseThrow(()->new RuntimeException("token not found"));
    }

    public void deconnexion(String t){
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Token token = tokenRepository.findByToken(t)
                .orElseThrow(()->new RuntimeException("Token Invalide"));

        token.setDesactive(true);
        token.setExpire(true);

        tokenRepository.save(token);
    }


}
