package com.pascoe.healthyeaterapi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pascoe.healthyeaterapi.authentication.TokenProvider;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Order(1)
@Component
public class AuthTokenFilter implements Filter {

    TokenProvider tokenProvider;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (tokenProvider.validateToken(httpRequest.getHeader("Authorization"))) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(401);

            chain.doFilter(request, httpResponse);
        }
    }
}
