package sptech.school.application.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET = "tFksjiMf5vNnpFOP0u/5TRqlA9TAw4P7XyJRCGYk3dI=";
    private static final Key key = new SecretKeySpec(
            Base64.getDecoder().decode(SECRET),
            SignatureAlgorithm.HS256.getJcaName()
    );

    public String generateToken(String email, String username, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("username", username)
                .claim("role", role.toUpperCase())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String extractRole(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }
}