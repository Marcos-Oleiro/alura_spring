package med.voll.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import med.voll.api.model.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
            .withIssuer("med.voll")
            .withSubject(usuario.getLogin())
            .withClaim("id", usuario.getId())
            .withExpiresAt(dataExpiracao())
            .sign(algorithm);
    }

    private Instant dataExpiracao() {
        
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
