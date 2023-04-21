package com.kpo.springshaurma.service;

import com.kpo.springshaurma.api.models.ResponseStatus;
import com.kpo.springshaurma.api.models.ShaurmaAuthenticationObject;
import com.kpo.springshaurma.api.models.reponse.ShaurmaUserAuthenticationResponse;
import com.kpo.springshaurma.jwt.JwtTokenProvider;
import com.kpo.springshaurma.mapper.ShaurmaUserMapper;
import com.kpo.springshaurma.model.ShaurmaUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShaurmaUserAuthenticationService {

  private final JwtTokenProvider jwtTokenProvider;

  private final ShaurmaUserService shaurmaUserService;

  private final ShaurmaUserMapper shaurmaUserMapper;

  private static final String EMAIL_REDUX = "^(.+)@(.+)$";

  private static final Pattern emailPatter = Pattern.compile(EMAIL_REDUX);

  @Transactional
  public ShaurmaUserAuthenticationResponse login(
      final ShaurmaAuthenticationObject shaurmaAuthenticationObject) {

//    authenticationManager.authenticate(
//        new UsernamePasswordAuthenticationToken(
//            mobileAuthenticationObject.getEmail(),
//            mobileAuthenticationObject.getPassword()
//        ));

    final ShaurmaUser shaurmaUser;

    if (emailPatter.matcher(shaurmaAuthenticationObject.getLogin()).matches()) {
      shaurmaUser = shaurmaUserService
          .getByEmail(shaurmaAuthenticationObject.getLogin());
    } else {
      shaurmaUser = shaurmaUserService.getByNickname(shaurmaAuthenticationObject.getLogin());
    }

    return ShaurmaUserAuthenticationResponse.builder()
        .status(ResponseStatus.SUCCESS)
        .body(ShaurmaUserAuthenticationResponse.MobileUserAuthenticationResponseBody.builder()
            .mobileUser(shaurmaUserMapper.shaurmaUserModel2ShaurmaUserObject(shaurmaUser))
            .accessToken(jwtTokenProvider.createToken(shaurmaUser.getId().toString(),
                Collections.singletonList(shaurmaUser.getRole())))
            .build())
        .build();
  }
}
