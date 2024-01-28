package com.example.Fiaraamidy.config;

import com.example.Fiaraamidy.entites.Token;
import com.example.Fiaraamidy.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private TokenService tokenService;

    public JwtAuthFilter(JwtUtil jwtUtil, TokenService tokenService) {
        this.jwtUtil = jwtUtil;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null){
            String[] elements = header.split(" ");
            if(elements.length == 2 && "Bearer".equals(elements[0])){
                try {
                    Authentication authentication = jwtUtil.validToken(elements[1]);
                    Token token = this.tokenService.findByToken(elements[1]);
                    if (token.isDesactive()) {
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        return;
                    }

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (RuntimeException e){
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }
        filterChain.doFilter(request,response);
    }

}
