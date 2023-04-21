package com.kpo.springshaurma.model;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtShaurmaUser implements UserDetails {

    private static final long serialVersionUID = -567848796871987687L;

    private final UUID id;
    private final String email;
    private final String displayName;
    private final String password;
    private final boolean enabled;
    private final ShaurmaUser.Role mobileUserRole;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtShaurmaUser(
            UUID id,
            String email,
            String displayName,
            String password,
            boolean enabled,
            ShaurmaUser.Role mobileUserRole,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.enabled = enabled;
        this.password = password;
        this.mobileUserRole = mobileUserRole;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public ShaurmaUser.Role getMobileUserRole() {
        return mobileUserRole;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return displayName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
