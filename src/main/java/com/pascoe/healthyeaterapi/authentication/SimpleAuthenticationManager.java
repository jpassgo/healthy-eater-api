package com.pascoe.healthyeaterapi.authentication;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class SimpleAuthenticationManager implements AuthenticationManager {

  static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

  static {
    AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
  }

  public Authentication authenticate(Authentication auth) throws AuthenticationException {
    if (auth.getName().equals(auth.getCredentials())) {
      return new UsernamePasswordAuthenticationToken(
          auth.getName(), auth.getCredentials(), AUTHORITIES);
    }
    throw new BadCredentialsException("Bad Credentials");
  }
}
