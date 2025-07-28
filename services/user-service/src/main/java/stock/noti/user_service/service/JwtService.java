package stock.noti.user_service.service;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Service
public class JwtService {

    private final Key key;

    public JwtService(@Value("${jwt.secret}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Duration.ofHours(24).toMillis()); //token sống 24h
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean isTokenExpired(String token){
        Date expiration =
                Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getExpiration();
        return expiration.before(new Date());
    }
}
