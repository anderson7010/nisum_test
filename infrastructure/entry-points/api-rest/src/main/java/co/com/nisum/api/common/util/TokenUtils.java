package co.com.nisum.api.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;

public class TokenUtils {
    private static final String SECRET = "kW89L4QjyfWIM0xagcVqn58LMWlQRwxj";
    private static final Long VALIDATION_TIME = 3600000L;

    public static String createToken(String email) {
        Date expirationDate = new Date(System.currentTimeMillis() + VALIDATION_TIME);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET.getBytes())
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
