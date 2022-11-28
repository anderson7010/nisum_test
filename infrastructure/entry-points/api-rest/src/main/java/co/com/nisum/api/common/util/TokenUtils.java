package co.com.nisum.api.common.util;

import co.com.nisum.api.common.config.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenUtils {
    private final TokenProperties tokenProperties;

    public String createToken(String email) {
        Date expirationDate = new Date(System.currentTimeMillis() +
                Long.parseLong(tokenProperties.getValidationTime()));
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(tokenProperties.getSecret().getBytes()))
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(tokenProperties.getSecret().getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return new UsernamePasswordAuthenticationToken(
                    claims.getSubject(), null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
