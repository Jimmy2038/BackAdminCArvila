package com.example.Fiaraamidy.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Fiaraamidy.entites.Utilisateur;
import com.example.Fiaraamidy.repository.TokenRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtUtil {
    public static String createToken(Utilisateur user){
        Date now = new Date();
        long validityInMilliseconds = 4 * 24 * 60 * 60 * 1000;
        Date validity = new Date(now.getTime()+validityInMilliseconds);

        return JWT.create()
                .withIssuer(user.getPseudo())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("id",user.getIdUtilisateur())
                .sign(Algorithm.HMAC256("secret"));
    }

    public Authentication validToken(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Utilisateur user = new Utilisateur(
                decodedJWT.getClaim("id").asInt(),
                decodedJWT.getIssuer()
        );

        return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
    }

}
