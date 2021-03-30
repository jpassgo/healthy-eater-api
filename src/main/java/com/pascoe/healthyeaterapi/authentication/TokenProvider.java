package com.pascoe.healthyeaterapi.authentication;

import io.github.jhipster.config.JHipsterProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;


@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private String secretKey;
    private Key key;
    private long tokenValidityInMilliseconds;
//    private long tokenValidityInMillisecondsForRememberMe;
    private final JHipsterProperties jHipsterProperties;

    public TokenProvider(JHipsterProperties jHipsterProperties) {
        this.jHipsterProperties = jHipsterProperties;
    }

    @PostConstruct
    public void init() {
        this.secretKey = jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();
        this.tokenValidityInMilliseconds = 1000 * jHipsterProperties.getSecurity()
                .getAuthentication().getJwt().getTokenValidityInSeconds();
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(getSigningKey())
                .setExpiration(getTokenValidityDuration())
                .compact();
    }

    private Date getTokenValidityDuration() {
        long now = (new Date()).getTime();
        return new Date(now + this.tokenValidityInMilliseconds);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
