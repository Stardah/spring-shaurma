package com.kpo.springshaurma.controller;

import com.kpo.springshaurma.api.models.ShaurmaAuthenticationObject;
import com.kpo.springshaurma.api.models.ShaurmaUserObject;
import com.kpo.springshaurma.api.models.SimpleResponse;
import com.kpo.springshaurma.api.models.reponse.ShaurmaUserAuthenticationResponse;
import com.kpo.springshaurma.service.ShaurmaUserAuthenticationService;
import com.kpo.springshaurma.service.ShaurmaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(
    value = "/api/v1.0",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class ShaurmaUserAuthenticationApiImpl {

  private final ShaurmaUserAuthenticationService shaurmaUserAuthenticationService;
  private final ShaurmaUserService shaurmaUserService;

  @PostMapping("/register")
  public SimpleResponse register(ShaurmaUserObject shaurmaUserObject) {
    return shaurmaUserService.register(shaurmaUserObject);
  }

  @PostMapping("/login")
  public ShaurmaUserAuthenticationResponse login(
      ShaurmaAuthenticationObject shaurmaAuthenticationObject) {
    return shaurmaUserAuthenticationService.login(shaurmaAuthenticationObject);
  }
}
