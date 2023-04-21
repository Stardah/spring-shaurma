package com.kpo.springshaurma.jwt;

import com.kpo.springshaurma.model.JwtShaurmaUser;
import com.kpo.springshaurma.model.ShaurmaUser;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {

  public static JwtShaurmaUser create(final ShaurmaUser mobileUser) {
    return new JwtShaurmaUser(
        mobileUser.getId(),
        mobileUser.getEmail(),
        mobileUser.getDisplayName(),
        mobileUser.getPassword(),
        mobileUser.getActive(),
        mobileUser.getRole(),
        convertToGrantedAuthority(Collections.singletonList(mobileUser.getRole()))
    );
  }

  private static List<GrantedAuthority> convertToGrantedAuthority(
      final List<ShaurmaUser.Role> mobileUserRoles) {
    return mobileUserRoles.stream()
        .map(role -> new SimpleGrantedAuthority(role.name()))
        .collect(Collectors.toList());
  }
}
