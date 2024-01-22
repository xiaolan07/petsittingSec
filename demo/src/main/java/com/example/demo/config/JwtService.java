package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.DialectOverride;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author jixia
 * @Description TODO
 * @date 22/01/2024-18:08
 */
@Service
public class JwtService {

   private static final String secretKey = "+eBtFFPPOqbatvu1GhFp1DoIEW3a050ALCUF6SE6pHtIGx+d6fe7xv+cCeedBG9bQTb23q+4QnaLKfVLOOOCBIGKq45XqnoqAQ/cn5TkL+OOofyerrQeMXl4rVyunyaD4lOOy11vJNMsVsP/JZAqQ1+l77Yju+a6AWyBUo/GVdpmrw8G768oh4qBw4Sr5LTXW7afm8vsvDcI3NJ/2QNhkWTFkNzUH8xrQTxqAtc21XrkaGbxAPTKUL2AEJWti1Fc9wmIzniqPYHC1d9N6cYUhpZBKzJlk8SPYnhEFg+Iwt6I+yjiK0fNnv9BVhU0CGVpfxNqUFKIoBeXjYEQeFj0oLfmlsd1XHPKayuiG8I5mzg=";
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String gengrateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);

    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 24))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }





}
