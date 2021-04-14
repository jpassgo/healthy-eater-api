package com.pascoe.healthyeaterapi.model;

import java.security.SecureRandom;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
public class UserCredentials implements UserDetails {

  private String userName;
  private String password;
  @JsonIgnore
  private Boolean locked;
  @JsonIgnore
  private Boolean accountNonExpired;
  @JsonIgnore
  private Boolean enabled;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public String encryptPassword() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());

    return this.password = encoder.encode(this.password);
  }

  @Override
  public String getUsername() {
    return userName;
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
