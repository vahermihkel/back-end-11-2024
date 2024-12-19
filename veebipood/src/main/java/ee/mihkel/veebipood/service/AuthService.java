package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.models.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    public Token getToken(Person person) {
        Date expirationDate = new Date((new Date()).getTime() + 20 * 60 * 1000);

        Map<String, String> claims = new HashMap<>();
        claims.put("id", person.getId().toString());
        claims.put("email", person.getEmail());

        String securityKey = "QwY+AUVs/wXnJjDsYiTXPpYsS+BQ+s3zZWHKx+PAbH8=";
        Key signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey)); // ?

        String jwtToken = Jwts.builder()
                .expiration(expirationDate)
                .claims(claims)
                .signWith(signingKey)
                .compact();

        return new Token(jwtToken, expirationDate);
    }
}
