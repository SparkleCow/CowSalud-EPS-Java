package com.cowsalud.salud.config.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/*
    Clase con distintos métodos para el adecuado manejo y gestión de los token.  
*/

@Service
public class JwtService {

    private final String SECRET_KEY = "6ed1eb0150e8f8fb285ec976ce500807406933985fbea5086c690eb221e53f5a";

    public boolean  validateToken(String token, UserDetails userDetails) {
        return (getUsername(token).equals(userDetails.getUsername())) && isNotExpired(token);
    }

    private boolean isNotExpired(String token) {
        Date expirationDate = extractClaim(token, Claims::getExpiration);
        return expirationDate != null && !expirationDate.before(new Date(System.currentTimeMillis()));
    }

    public String generateToken(UserDetails userDetails){
        HashMap<String, Object> extraClaims = new HashMap<>();
        return generateToken(extraClaims, userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
         return Jwts.builder()
            .setClaims(extraClaims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setSubject(userDetails.getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
            .signWith(generateSignKey()).compact();
    }

    public String getUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(generateSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Key generateSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
