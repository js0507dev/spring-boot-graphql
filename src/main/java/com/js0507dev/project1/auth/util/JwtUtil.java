package com.js0507dev.project1.auth.util;

import com.js0507dev.project1.member.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {
  @Value("${application.security.jwt.secret-key}")
  private String secretKey;
  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;
  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;
  private final String tokenPrefix = "Bearer ";

  public Boolean hasJwt(String authHeader) {
    return authHeader != null && authHeader.startsWith(tokenPrefix);
  }

  public String extractJwt(String authHeader) {
    return authHeader.substring(tokenPrefix.length());
  }

  public String extractEmail(String jwt) {
    return extractClaim(jwt, Claims::getSubject);
  }

  public Boolean isExpired(String jwt) {
    return extractExpiration(jwt).before(new Date());
  }

  private Date extractExpiration(String jwt) {
    return extractClaim(jwt, Claims::getExpiration);
  }

  private <T> T extractClaim(String jwt, Function<Claims, T> claimsTFunction) {
    Claims claims = extractAllClaims(jwt);
    return claimsTFunction.apply(claims);
  }

  private Claims extractAllClaims(String jwt) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJwt(jwt)
        .getBody();
  }

  private Key getSigningKey() {
    byte[] decoded = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(decoded);
  }
}
