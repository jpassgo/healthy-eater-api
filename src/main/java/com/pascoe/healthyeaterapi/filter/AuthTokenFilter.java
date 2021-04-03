package com.pascoe.healthyeaterapi.filter;

import static io.micrometer.core.instrument.util.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.pascoe.healthyeaterapi.authentication.TokenProvider;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class AuthTokenFilter implements Filter {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String BEARER_TOKEN = "Bearer ";

  TokenProvider tokenProvider;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    if (tokenProvider.validateToken(extractToken(httpRequest))) {
      chain.doFilter(request, response);
    } else {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.setStatus(401);

      chain.doFilter(request, httpResponse);
    }
  }

  private String extractToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

    if (isNotBlank(bearerToken) && bearerToken.startsWith(BEARER_TOKEN)) {
      return bearerToken.substring(7);
    }
    return EMPTY;
  }
}
