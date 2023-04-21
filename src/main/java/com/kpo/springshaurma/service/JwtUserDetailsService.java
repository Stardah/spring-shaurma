package com.kpo.springshaurma.service;

import com.kpo.springshaurma.jwt.JwtUserFactory;
import com.kpo.springshaurma.model.ShaurmaUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService {

    private final ShaurmaUserService mobileUserService;

    public UserDetails getUserDetails(final String email) {
        ShaurmaUser mobileUser = mobileUserService.getByEmail(email);
        return JwtUserFactory.create(mobileUser);
    }
}
