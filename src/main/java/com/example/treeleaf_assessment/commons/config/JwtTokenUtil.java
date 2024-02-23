package com.example.treeleaf_assessment.commons.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    private static final long ADMIN_JWT_TOKEN_VALIDITY = 18000000;
    private static final long USER_JWT_TOKEN_VALIDITY = 18000000;
    private final String secret = "MWFoMEc3YmpkaHY=";


    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String getByKey(String token, String key) {
        Claims allClaimsFromToken = getAllClaimsFromToken(token);
        return (String.valueOf(allClaimsFromToken.get(key)));
    }

    public List<String> getPermissions(String jwtToken) {
        Claims claims = getAllClaimsFromToken(jwtToken);
        return (List<String>) claims.get("permissionList");
    }

    public Long getId(String token) {
        Claims allClaimsFromToken = getAllClaimsFromToken(token);
        return ((Number) allClaimsFromToken.get("id")).longValue();
    }

    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //generate token for user
    public String generateToken(Map<String, Object> claims) {
        return doGenerateToken(claims, claims.get("userName").toString(), USER_JWT_TOKEN_VALIDITY);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, Long tokenValidity) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String generateAdminToken(Map<String, Object> claims) {
        return doGenerateToken(claims, claims.get("userName").toString(), ADMIN_JWT_TOKEN_VALIDITY);
    }
}
