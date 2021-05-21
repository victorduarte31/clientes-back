package br.victor.clientes.configuration.security;

import br.victor.clientes.model.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;


@Service
public class TokenService {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String gerarToken(Authentication authenticate) {
        var usuario = (Usuario) authenticate.getPrincipal();

        return Jwts.builder()
                .setIssuer("Api Clientes")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + Long.parseLong("1800000")))
                .signWith(secretKey)
                .compact();
    }

    public Boolean isTokenValido(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        var claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
