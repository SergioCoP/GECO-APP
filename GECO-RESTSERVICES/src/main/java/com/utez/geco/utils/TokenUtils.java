package com.utez.geco.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    private final static String SECRET_KEY = "bWkgY2xhdmUgZXMgbXV5IHNlZ3VyYSAxMjM0NTY3ODkgQUJDYWJj";
    private final static Long TOKEN_TIME = 2_592_000L;

    public static String createToken(String user, String email) {
        long expirationTime = TOKEN_TIME * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() * expirationTime);
        Map<String, Object> extra = new HashMap<>();
        extra.put("user", user);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
