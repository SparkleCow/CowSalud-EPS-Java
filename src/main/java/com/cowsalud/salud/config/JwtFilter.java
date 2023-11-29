package com.cowsalud.salud.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");
        if(requestToken==null||!requestToken.startsWith("bearer")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = requestToken.substring(7);
        jwtService.validateToken(token);
        String username = jwtService.getUsername(token);
        


        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    }
}