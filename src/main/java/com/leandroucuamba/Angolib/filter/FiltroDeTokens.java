package com.leandroucuamba.Angolib.filter;

import com.leandroucuamba.Angolib.service.JWTService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class FiltroDeTokens extends GenericFilterBean {
    public static final int TOKEN_INDEX = 7;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String header = httpServletRequest.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou mal formatado");
            return;
        }

        String token = header.substring(TOKEN_INDEX);

        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(JWTService.TOKEN_KEY).build();
            parser.parseClaimsJws(token).getBody();
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | PrematureJwtException |
                 IllegalArgumentException | ExpiredJwtException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }
}
