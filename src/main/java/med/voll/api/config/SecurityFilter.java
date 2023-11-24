package med.voll.api.config;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.reps.UsuarioRepository;
import med.voll.api.service.TokenService;

@Component
public class SecurityFilter extends OncePerRequestFilter{


    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository rep;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);

        if (Objects.nonNull(tokenJWT)){

            String subject = tokenService.getSubject(tokenJWT);

            UserDetails usuario = rep.findByLogin(subject);

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));

        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        
        String authToken = request.getHeader("Authorization");

        if (Objects.nonNull(authToken))  {
            return authToken.replace("Bearer ", "");
        }

        return null; 
    }
}
