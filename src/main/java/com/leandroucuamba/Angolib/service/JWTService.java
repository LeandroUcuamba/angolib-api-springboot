package com.leandroucuamba.Angolib.service;

import com.leandroucuamba.Angolib.dto.TokenDoUsuario;
import com.leandroucuamba.Angolib.dto.UsuarioDeLogin;
import com.leandroucuamba.Angolib.entity.Usuario;
import com.leandroucuamba.Angolib.repository.UsuarioRepository;
import com.leandroucuamba.Angolib.service.exception.LoginInvalidoException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class JWTService {
    public static final Key TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private UsuarioRepository repository;

    @Autowired
    public JWTService(UsuarioRepository repository){
        this.repository = repository;
    }

    public TokenDoUsuario autenticaUsuario(UsuarioDeLogin usuarioDeLogin){
        Optional<Usuario> usuarioRetornado = repository.findByEmailAndSenha(usuarioDeLogin.email(), usuarioDeLogin.senha());

        if(usuarioRetornado.isEmpty()){
            throw new LoginInvalidoException("Login Invalido!");
        }

        String token = gerarToken(usuarioDeLogin.email());
        return new TokenDoUsuario(token);
    }

    private String gerarToken(String email) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(email)
                .signWith(TOKEN_KEY, SignatureAlgorithm.HS512)
                .setExpiration(tempoDoToken())
                .compact();
    }

    private Date tempoDoToken(){
        return Date.from(Instant.now().plusSeconds(60 * 5));
    }

    public String pegaSujeitoPeloToken(String header){
        if(header == null || !header.startsWith("Bearer ")){
            throw new SecurityException("Token inválido!");
        }

        String token = header.substring(7);

        String sujeito;
        try{
            JwtParser parser = Jwts.parserBuilder().setSigningKey(TOKEN_KEY).build();
            sujeito = parser.parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new SecurityException("Erro na validação do token!");
        }
        return sujeito;
    }
}
