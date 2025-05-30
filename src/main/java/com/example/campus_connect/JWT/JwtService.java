package com.example.campus_connect.JWT;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Repository.UsersRepository;

@Service
public class JwtService {
    @Autowired
    private UsersRepository usersRepository;
    
    private static final String SECRET_KEY = Base64.getEncoder()
            .encodeToString("9C8380FBD615FC93C9A2FF197191ED35C0EB93B64B41A6A89E4D906D848BDAA4".getBytes());

    private static final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(30);

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        UsersEntity user = usersRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Add the user ID to the token
        claims.put("userId", user.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(EXPIRATION_TIME)))
                .signWith(generateKey())
                .compact();

    }

    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public Claims getClaims(String token) {

        SecretKey key = generateKey();
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        // return Jwts.parserBuilder()
        // .verifyWith(generateKey())
        // .build()
        // .parseSignedClaims(token)
        // .getPayload();
    }

    public boolean validateToken(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }

    public String extractUserId(String token) {
        return getClaims(token).get("userId", String.class);
    }
}