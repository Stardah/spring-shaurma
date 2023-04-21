package com.kpo.springshaurma.jwt;

import com.kpo.springshaurma.model.ShaurmaUser;
import com.kpo.springshaurma.service.JwtUserDetailsService;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Util class that provides methods for generation, validation, etc. of JWT token.
 */
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  @Value("${jwt.token.secret}")
  private String secret;

  @Value("${jwt.token.expired}")
  private long validityInMilliseconds;

  private final JwtUserDetailsService userDetailsService;

  @PostConstruct
  protected void init() {
    secret = Base64.getEncoder().encodeToString(secret.getBytes());
  }

  public String createToken(
      final String id,
      List<ShaurmaUser.Role> roles
  ) {
    final Claims claims = Jwts.claims().setSubject(id);
    claims.put("roles", getRoleNames(roles));

    final Date now = new Date();
    final Date expiredDate = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expiredDate)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public Authentication getAuthentication(final String token) {
    final UserDetails userDetails = this.userDetailsService.getUserDetails(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public Claims getTokenBody(final String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  public UUID getUserId(final String token) {
    return UUID.fromString(
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject());
  }

  // Тут id юзера, а не имя
  String getUsername(final String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
  }

  String resolveToken(final HttpServletRequest req) {
    return req.getHeader("Authorization");
  }

  public boolean validateToken(final String token) {
    try {
      final Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

      return !claims.getBody().getExpiration().before(new Date());
    } catch (final JwtException | IllegalArgumentException e) {
      throw new RuntimeException("JWT token is expired or invalid");
    }
  }

  private List<String> getRoleNames(final List<ShaurmaUser.Role> mobileUserRoles) {

    return mobileUserRoles.stream()
        .map(ShaurmaUser.Role::getName)
        .collect(Collectors.toList());
  }
}
