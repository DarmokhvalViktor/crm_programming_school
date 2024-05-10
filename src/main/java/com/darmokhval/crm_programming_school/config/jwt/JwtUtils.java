package com.darmokhval.crm_programming_school.config.jwt;

import com.darmokhval.crm_programming_school.exception.ProblemWithJwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {
    @Value("${darmokhval.app.jwtSecret}")
    private String jwtSecret;
    @Value("${darmokhval.app.jwtAccessTokenExpirationMs}")
    private int accessTokenExpiration;
    @Value("${darmokhval.app.jwtRefreshTokenExpirationMs}")
    private int refreshTokenExpiration;

    public String generateAccessToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(Map.of("roles", getRoles(userDetails), "iss", "darmokhval"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(key())
                .compact();
    }
    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(Map.of("roles", getRoles(userDetails), "iss", "darmokhval", "type", "refresh"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(key())
                .compact();
    }
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
//    TODO not working.
    public boolean validateToken(String token){
        try {
            Jwts.parser().verifyWith(key()).build().parse(token);
            return true;
        } catch (MalformedJwtException exception) {
            log.error("Invalid JWT: {}", exception.getMessage());
            throw new ProblemWithJwtException("Invalid JWT", exception);
        } catch (ExpiredJwtException exception) {
            log.error("JWT expired: {}", exception.getMessage());
            throw new ProblemWithJwtException("JWT expired", exception);
        } catch (UnsupportedJwtException exception) {
            log.error("JWT is not supported: {}", exception.getMessage());
            throw new ProblemWithJwtException("JWT is not supported", exception);
        } catch (IllegalArgumentException exception) {
            log.error("JWT claims is not supported: {}", exception.getMessage());
            throw new ProblemWithJwtException("JWT claims is not supported", exception);
        } catch (SignatureException exception) {
            log.error("JWT signature doesn't match locally computed signature: {}", exception.getMessage());
            throw new ProblemWithJwtException("JWT signature doesn't match locally computed signature:", exception);
        }
    }
    public boolean isMyCustomJWT(String token) {
        return resolveClaim(token, claims -> Objects.equals(claims.get("iss", String.class), "darmokhval"));
    }
    public Date extractExpiration(String token) {
        return resolveClaim(token, Claims::getExpiration);
    }
    public boolean isRefreshToken(String token) {
        return resolveClaim(token, claims -> Objects.equals(claims.get("type", String.class), "refresh"));
    }
    private <T> T resolveClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return resolver.apply(claims);
    }
    private List<String> getRoles(UserDetails userDetails) {
        return userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }
    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
