package com.utez.geco.SecurityConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utez.geco.DTO.AuthCredentialsDTO;
import com.utez.geco.service.UserDetailsImpl;
import com.utez.geco.utils.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentialsDTO auth = new AuthCredentialsDTO();
        try {
            auth = new ObjectMapper().readValue(request.getReader(), AuthCredentialsDTO.class);
        } catch(IOException e) {

        }

        UsernamePasswordAuthenticationToken usernameApi = new UsernamePasswordAuthenticationToken(
                auth.getUser(),
                auth.getPassword(),
                Collections.emptyList()
        );
        return getAuthenticationManager().authenticate(usernameApi);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getUsername(), userDetails.getEmail());

        response.addHeader("Authorzation", "Bearer " + token);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}