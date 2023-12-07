package com.cowsalud.salud.config.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*  
    Esta clase implementa OncePerRequiestFilter para manejar las solicitudes HTTP.
    JwtFilter valida que se reciba un token en el header de la peticion HTTP y a su vez, valida si sus datos son correctos o erroneos.
    En caso de ser validos, crea un objeto UsernamePasswordAuthenticationToken 
    (Implementacion de la interfaz Authentication) y actualiza el contexto de seguridad de spring security con esta implementación.
*/

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");
        if(requestToken==null||!requestToken.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = requestToken.substring(7);
        String username = jwtService.getUsername(token);
        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtService.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
        return;
    }
}