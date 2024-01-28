package com.example.Fiaraamidy.config;


import com.example.Fiaraamidy.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private TokenService tokenService;

    public SecurityConfig(JwtUtil jwtUtil, TokenService tokenService) {
        this.jwtUtil = jwtUtil;
        this.tokenService = tokenService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtAuthFilter(jwtUtil, tokenService), BasicAuthenticationFilter.class)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers(HttpMethod.POST,"/login").permitAll()
                                .requestMatchers(HttpMethod.POST,"/admin").permitAll()
                                .requestMatchers(HttpMethod.POST,"/inscription").permitAll()
                                .requestMatchers(HttpMethod.POST,"/inscription/login").permitAll()
                                .requestMatchers(HttpMethod.GET,"/annonce/getAll").permitAll()
                                .requestMatchers(HttpMethod.GET,"/annonce/photo/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET,"/annonce/getAllPhoto").permitAll()
                                .requestMatchers(HttpMethod.GET,"/annonce/valide").permitAll()
                                .requestMatchers(HttpMethod.GET,"/annonce/recherche").permitAll()
                                .anyRequest().authenticated()
                );
        return  http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(12);
    }
}
