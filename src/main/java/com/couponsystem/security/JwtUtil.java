package com.couponsystem.security;

import java.util.function.Function;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();

    @Value("${jwt.token}")
    private String encodedSecretKey;
    private Key decodedSecretKey;

    @PostConstruct
    private void init() {
        decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), this.signatureAlgorithm);
    }

    public JwtUtil() {
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractName(String token) {
        final Claims claims = extractAllClaims(token);
        return (String) claims.get("clientName");
    }

    public int extractId(String token) {
        final Claims claims = extractAllClaims(token);
        return (int) claims.get("clientId");
    }

    public String extractUserType(String token) {
        final Claims claims = extractAllClaims(token);
        return (String) claims.get("clientType");
    }

    public int extractUserTypeForFilter(String token) {
        String user = extractAllClaims(token).get("userType").toString();
        switch (user) {
            case "ADMIN": {
                return 0;
            }
            case "COMPANY": {
                return 1;
            }
            case "CUSTOMER": {
                return 2;
            }
            default:
                return -1;
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(this.decodedSecretKey).build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String clientType, int clientId, String clientName,
                                String clientEmail) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("clientType", clientType.toUpperCase());
        claims.put("clientId", clientId);
        claims.put("clientName", clientName);
        claims.put("clientEmail", clientEmail);
        return createToken(claims, clientEmail, clientName, clientType, clientId);
    }

    private String createToken(Map<String, Object> claims, String subject, String clientName, String clientType,
                               int clientId) {
        Instant now = Instant.now();
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(10, ChronoUnit.HOURS))).signWith(this.decodedSecretKey)
                .signWith(decodedSecretKey).compact();
    }

    public Boolean validateToken(String token, String userEmail) {
        final String username = extractUsername(token);
        return (username.equals(userEmail) && !isTokenExpired(token));
    }

    public Object validateService(String token, String clientType) {
        if (extractUserType(token).equals(clientType) && !isTokenExpired(token)) {
            return true;
        }
        return false;
    }
}
