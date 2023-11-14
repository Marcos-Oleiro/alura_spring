package med.voll.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.model.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private String issuer = "med.voll";

    public String gerarToken(Usuario usuario) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
            .withIssuer(issuer)
            .withSubject(usuario.getLogin())
            .withClaim("id", usuario.getId())
            .withExpiresAt(dataExpiracao())
            .sign(algorithm);
    }

    private Instant dataExpiracao() {
        
        return LocalDateTime.now().plusHours(240).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer(issuer).build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado");
        }

    }
}
