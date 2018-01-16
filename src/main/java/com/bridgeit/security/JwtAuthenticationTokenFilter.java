package com.bridgeit.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.bridgeit.security.exception.JwtTokenMissingException;
import com.bridgeit.security.model.JwtAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that orchestrates authentication by using supplied JWT token
 *
 * @author pascal alma
 */
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${jwt.header}")
    private String tokenHeader;

    public JwtAuthenticationTokenFilter() {
        super("/**");
    }

    /**
     * Attempt to authenticate request - basically just pass over to another method to authenticate request headers
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader("Authorization");
        System.out.println(header);
        if (header == null || !header.startsWith("Bearer ")) {
        	
            throw new JwtTokenMissingException("No JWT token found in request headers");
        }

        String authToken = header.substring(7);

        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

        return getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * Make sure the rest of the filterchain is satisfied
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        // As this authentication is in HTTP header, after success we need to continue the request normally
        // and return the response as if the resource was not secured at all
        chain.doFilter(request, response);
    }
}