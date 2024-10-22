package com.example.currency_converter.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private static final String SECRET_KEY = "SecretKeyToGenJWTs"; // Use uma chave fixa para testes
	private static final Logger LOGGER = Logger.getLogger(JwtAuthorizationFilter.class.getName());

	public JwtAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(req, res);
			return;
		}

		String token = header.replace("Bearer ", "");
		UsernamePasswordAuthenticationToken authentication = getAuthentication(token);

		if (authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			LOGGER.log(Level.WARNING, "Authentication failed for token: {0}", token);
			res.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Token"); // Retorna erro 403 para token inválido
			return;
		}

		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()) // Use getBytes() para evitar problemas
																				// de codificação
					.parseClaimsJws(token).getBody();

			String user = claims.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
		} catch (SignatureException e) {
			LOGGER.log(Level.SEVERE, "Invalid token signature: {0}", e.getMessage());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Token parsing failed: {0}", e.getMessage());
		}
		return null;
	}
}
