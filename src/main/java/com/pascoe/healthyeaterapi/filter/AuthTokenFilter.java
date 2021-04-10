package com.pascoe.healthyeaterapi.filter;

import static com.pascoe.healthyeaterapi.constants.HeadersUtils.AUTHORIZATION_HEADER;
import static com.pascoe.healthyeaterapi.constants.HeadersUtils.BEARER;
import static io.micrometer.core.instrument.util.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.pascoe.healthyeaterapi.authentication.TokenProvider;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@AllArgsConstructor
public class AuthTokenFilter implements Filter {

  TokenProvider tokenProvider;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    if (httpRequest.getServletPath().contains("/authentication")
        || tokenProvider.validateToken(extractToken(httpRequest))) {
      chain.doFilter(request, response);
    } else {
      ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
    }
  }

  private String extractToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

    if (isNotBlank(bearerToken) && bearerToken.startsWith(BEARER)) {
      return bearerToken.substring(7);
    }
    return EMPTY;
  }
}
