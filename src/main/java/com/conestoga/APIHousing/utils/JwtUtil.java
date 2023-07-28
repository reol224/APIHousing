package com.conestoga.APIHousing.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int expiration;

    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // public String generateToken(String username) {

    //     Date now = new Date();
    //     Date expiryDate = new Date(now.getTime() + expiration * 1000);

    //     return Jwts.builder()
    //             .setSubject(username)
    //             .setIssuedAt(now)
    //             .setExpiration(expiryDate)
    //       .signWith(key, SignatureAlgorithm.HS256)
    //             .compact();
    // }

        public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    // public boolean validateToken(String token) {
    //     SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

    //     try {
    //         Claims claims = Jwts.parserBuilder()
    //                 .setSigningKey(key)
    //                 .build()
    //                 .parseClaimsJws(token)
    //                 .getBody();

    //         return !claims.getExpiration().before(new Date());
    //     } catch (Exception ex) {
    //         // Exception indicates token is not valid
    //         return false;
    //     }

      public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    
    }

    public String extractUsername(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
