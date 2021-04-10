package com.pascoe.healthyeaterapi.controller;

import static com.pascoe.healthyeaterapi.constants.HeadersUtils.AUTHORIZATION_HEADER;
import static com.pascoe.healthyeaterapi.constants.HeadersUtils.BEARER;

import com.pascoe.healthyeaterapi.authentication.SimpleAuthenticationManager;
import com.pascoe.healthyeaterapi.authentication.TokenProvider;
import com.pascoe.healthyeaterapi.model.LoginDetails;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping
public class AuthenticationController {

  private final TokenProvider tokenProvider;
  private final SimpleAuthenticationManager authenticationManager;

  @PostMapping("/authentication")
  public ResponseEntity authenticate(
      @RequestBody LoginDetails loginDetails, HttpServletResponse response) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            loginDetails.getUsername(), loginDetails.getPassword());

    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = tokenProvider.createToken(authentication);

    response.addHeader(AUTHORIZATION_HEADER, BEARER + jwt);
    return new ResponseEntity(jwt, HttpStatus.OK);
  }
}
