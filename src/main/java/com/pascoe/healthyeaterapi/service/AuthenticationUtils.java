package com.pascoe.healthyeaterapi.service;

import com.pascoe.healthyeaterapi.authentication.SimpleAuthenticationManager;
import com.pascoe.healthyeaterapi.authentication.TokenProvider;
import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.model.UserCredentials;
import java.security.SecureRandom;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class AuthenticationUtils {

  private final TokenProvider tokenProvider;
  private final SimpleAuthenticationManager authenticationManager;

  public String generateAuthToken(@RequestBody UserCredentials userCredentials) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            userCredentials.getUsername(), userCredentials.getPassword());

    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return tokenProvider.createToken(authentication);
  }

  public String generateAuthToken() {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken("admin", "pass");

    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return tokenProvider.createToken(authentication);
  }

  public boolean doesPasswordMatch(
      @RequestBody UserCredentials userCredentials, Optional<UserAccount> optionalUserAccount) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());
    return encoder.matches(
        userCredentials.getPassword(),
        optionalUserAccount.get().getUserCredentials().getPassword());
  }
}
