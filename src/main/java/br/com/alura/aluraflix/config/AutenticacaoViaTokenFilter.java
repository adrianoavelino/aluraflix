package br.com.alura.aluraflix.config;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = recuperarToken(request);
        System.out.println(token);
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || bearerToken.isEmpty() || !bearerToken.startsWith("Bearer")) {
            return null;
        }
        return bearerToken.substring(7, bearerToken.length());
    }
}
