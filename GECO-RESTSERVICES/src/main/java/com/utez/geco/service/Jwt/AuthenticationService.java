package com.utez.geco.service.Jwt;

import com.utez.geco.DTO.JwtAuthenticationResponse;
import com.utez.geco.DTO.SigninRequest;
import com.utez.geco.DTO.SignupRequest;
import com.utez.geco.model.User;

public interface AuthenticationService {

    User signup(SignupRequest signupRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
}
