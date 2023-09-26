package com.js0507dev.project1.auth.util;

import com.js0507dev.project1.common.exception.BadRequestException;
import com.js0507dev.project1.common.exception.enums.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
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

  public String extractEmail(String jwt) throws RuntimeException {
    try {
      return extractClaim(jwt, Claims::getSubject);
    } catch (Exception ex) {
      throw new BadRequestException(ErrorCode.INVALID_JWT, "invalid jwt");
    }
  }

  public Boolean isExpired(String jwt) {
    try {
      return extractExpiration(jwt).before(new Date());
    } catch (Exception ex) {
      log.warn(ex.getMessage());
      return true;
    }
  }

  public String generateJwt(String email) {
    return buildToken(new HashMap<>(), email, jwtExpiration);
  }

  private String buildToken(
      Map<String, Object> extraClaims,
      String email,
      long expiration
  ) {
    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(email)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Date extractExpiration(String jwt) throws Exception {
    return extractClaim(jwt, Claims::getExpiration);
  }

  private <T> T extractClaim(String jwt, Function<Claims, T> claimsTFunction) throws Exception {
    Claims claims = extractAllClaims(jwt);
    return claimsTFunction.apply(claims);
  }

  private Claims extractAllClaims(String jwt) throws Exception {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(jwt)
        .getBody();
  }

  private Key getSigningKey() {
    byte[] decoded = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(decoded);
  }
}
