package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.authentication.AuthToken;
import com.pascoe.healthyeaterapi.authentication.SimpleAuthenticationManager;
import com.pascoe.healthyeaterapi.authentication.TokenProvider;
import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.model.UserCredentials;
import com.pascoe.healthyeaterapi.repository.UserAccountRepository;
import java.security.SecureRandom;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping
public class AuthenticationController {

  private final TokenProvider tokenProvider;
  private final SimpleAuthenticationManager authenticationManager;
  private final UserAccountRepository userAccountRepository;

  @PostMapping("/authentication")
  public ResponseEntity authenticate(
      @RequestBody UserCredentials userCredentials, HttpServletResponse response) {
    Optional<UserAccount> optionalUserAccount =
        userAccountRepository.findByUserCredentialsUserName(userCredentials.getUsername());

    if (optionalUserAccount.isPresent()) {
      if (doesPasswordMatch(userCredentials, optionalUserAccount)) {
        String jwt = generateAuthToken(userCredentials);
        return new ResponseEntity(new AuthToken(jwt), HttpStatus.OK);
      }
    }

    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }

  private String generateAuthToken(@RequestBody UserCredentials userCredentials) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            userCredentials.getUsername(), userCredentials.getPassword());

    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return tokenProvider.createToken(authentication);
  }

  private boolean doesPasswordMatch(
      @RequestBody UserCredentials userCredentials, Optional<UserAccount> optionalUserAccount) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());
    return encoder.matches(
        userCredentials.getPassword(),
        optionalUserAccount.get().getUserCredentials().getPassword());
  }
}
