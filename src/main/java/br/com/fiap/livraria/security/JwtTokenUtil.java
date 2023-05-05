package br.com.fiap.livraria.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Signature;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value ("{jwt.secret}")
    private String secret;

    @Value("{jwt.expire}")
    private String expire;

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        Date dataCriacao = getFromLocalDate(LocalDateTime.now());
        Date dataExpiracao =
                getFromLocalDate(LocalDateTime.now().plusMinutes(Long.parseLong(expire)));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(dataCriacao)
                .setExpiration(dataExpiracao)
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private  Date getFromLocalDate(LocalDateTime now){
        return Date.from(now.toInstant(OffsetDateTime.now().getOffset()));
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer", ""))
                .getBody();
        return claims.getSubject();
    }
}

