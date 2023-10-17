package com.js0507dev.project1.auth.filter;

import com.js0507dev.project1.auth.entity.CustomUserDetails;
import com.js0507dev.project1.auth.entity.Token;
import com.js0507dev.project1.auth.repository.TokenRepositoryCustom;
import com.js0507dev.project1.auth.util.JwtUtil;
import com.js0507dev.project1.common.exception.CustomBaseException;
import com.js0507dev.project1.common.exception.UnauthorizedException;
import com.js0507dev.project1.common.exception.enums.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  private final TokenRepositoryCustom tokenRepositoryCustom;

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
    String authHeader = req.getHeader("Authorization");
    if (!jwtUtil.hasJwt(authHeader)) {
      chain.doFilter(req, res);
      return;
    }

    UsernamePasswordAuthenticationToken authToken = getAuthenticationToken(authHeader);
    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
    SecurityContextHolder.getContext().setAuthentication(authToken);

    chain.doFilter(req, res);
  }

  private @NonNull UsernamePasswordAuthenticationToken getAuthenticationToken(String authHeader) {
    try {
      String jwt = jwtUtil.extractJwt(authHeader);
      String email = jwtUtil.extractEmail(jwt);
      log.error("test: " + email + ", auth header: " + authHeader);
      Token foundToken = tokenRepositoryCustom
          .findByEmail(email)
          .filter(token -> !token.getRevoked())
          .orElseThrow(() -> new UnauthorizedException(ErrorCode.INVALID_JWT, "this token is invalid or rejected"));

      if (jwtUtil.isExpired(jwt)) {
        throw new UnauthorizedException(ErrorCode.EXPIRED, "this token is expired");
      }
      CustomUserDetails userDetails = new CustomUserDetails(foundToken.getMember());
      return new UsernamePasswordAuthenticationToken(userDetails, null, null);
    } catch (Exception e) {
      if (e instanceof CustomBaseException) {
        throw e;
      }
      throw new UnauthorizedException(ErrorCode.UNKNOWN_AUTHORIZATION_ERROR, e.getMessage());
    }
  }
}
