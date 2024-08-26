package com.petpacket.final_project.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.petpacket.final_project.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Component
@Log4j2
public class JwtUtil {
//	@Value("${petserviceconnect.app.jwtSecret}")
//	private String jwtSecret;

	@Value("${petserviceconnect.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(key).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
//            log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
//            log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
//            log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
//            log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
//            log.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
}