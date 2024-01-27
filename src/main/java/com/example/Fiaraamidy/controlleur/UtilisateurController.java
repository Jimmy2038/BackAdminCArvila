package com.example.Fiaraamidy.controlleur;

import com.example.Fiaraamidy.config.Data;
import com.example.Fiaraamidy.config.JwtUtil;
import com.example.Fiaraamidy.entites.Annonce;
import com.example.Fiaraamidy.entites.Favoris;
import com.example.Fiaraamidy.entites.Token;
import com.example.Fiaraamidy.entites.Utilisateur;
import com.example.Fiaraamidy.repository.TokenRepository;
import com.example.Fiaraamidy.service.AnnonceService;
import com.example.Fiaraamidy.service.FavorisService;
import com.example.Fiaraamidy.service.TokenService;
import com.example.Fiaraamidy.service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UtilisateurController {

    private final UtilisateurService userService;
    public final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final TokenRepository tokenRepository;
    private final TokenService tokenService;

    private AnnonceService annonceService;
    private FavorisService favorisService;

    public UtilisateurController(UtilisateurService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, TokenRepository tokenRepository, TokenService tokenService, AnnonceService annonceService, FavorisService favorisService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.tokenService = tokenService;
        this.annonceService = annonceService;
        this.favorisService = favorisService;
    }

    @PostMapping("/login")
    public ResponseEntity<Data> login(@RequestBody Utilisateur user){
        try{

            Utilisateur u = userService.login(user);
            String token = JwtUtil.createToken(u);
            Token t = new Token();
            t.setExpire(false);
            t.setDesactive(false);
            t.setToken(token);
            t.setUtilisateur(u);
            tokenRepository.save(t);

            return ResponseEntity.ok(new Data(u.getIdUtilisateur()+"",token,"Welcome"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new Data(null,null,e.getMessage()));
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<Data> admin(@RequestBody Utilisateur user){
        try{
            Utilisateur u = userService.loginAdmin(user);
            String token = JwtUtil.createToken(u);
            Token t = new Token();
            t.setExpire(false);
            t.setDesactive(false);
            t.setToken(token);
            t.setUtilisateur(u);
            tokenRepository.save(t);

            return ResponseEntity.ok(new Data(u.getIdUtilisateur()+"",token,"Welcome"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new Data(null,null,e.getMessage()));
        }
    }



    @PostMapping("/inscription")
    public ResponseEntity<String> inscription(@RequestBody Utilisateur utilisateur) {
        try {
            String motDePasseCrypte = passwordEncoder.encode(utilisateur.getMdp());
            utilisateur.setMdp(motDePasseCrypte);
            utilisateur.setRole(0);
            Utilisateur nouvelUtilisateur = userService.inscription(utilisateur);

            return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur inscrit avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/deconnexion")
    public ResponseEntity<Data> logout(HttpServletRequest request) {
        try {
            String token = getTokenFromRequest(request);
            tokenService.deconnexion(token);
            return ResponseEntity.ok(new Data(null,null,"Déconnexion réussie"));
        } catch (RuntimeException e){
            return ResponseEntity.ok(new Data(null,null,e.getMessage()));
        }
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @PostMapping("/insertFavoris")
    public ResponseEntity<String> insert(@RequestBody Favoris favoris){
        try{
            this.favorisService.insert(favoris);
            return ResponseEntity.ok("Mety");
        } catch (RuntimeException e) {
            return ResponseEntity.ok(e.getMessage());
        }

    }

    @GetMapping("/favoris/{id}")
    public List<Favoris> favoris(@PathVariable int id){
        return this.favorisService.getFavoris(id);
    }


    @GetMapping("/historiqueVente/{id}")
    public List<Annonce> hitso(@PathVariable int id){
        try{
            return this.annonceService.getHistoriqueVendu(id);
        } catch (RuntimeException e) {
            return null;
        }
    }

    @PutMapping(path = "vendu/{id}")
    public String vendu(@PathVariable int id)
    {
        try{
            this.annonceService.vendu(id);
            return "Status change";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/inscription/login")
    public ResponseEntity<String> inscriptio(@RequestBody Utilisateur utilisateur) {
        try {
            String motDePasseCrypte = passwordEncoder.encode(utilisateur.getMdp());
            utilisateur.setMdp(motDePasseCrypte);
            utilisateur.setRole(1);
            Utilisateur nouvelUtilisateur = userService.inscription(utilisateur);

            return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur inscrit avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
