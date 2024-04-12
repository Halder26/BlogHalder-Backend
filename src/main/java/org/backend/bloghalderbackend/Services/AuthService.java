package org.backend.bloghalderbackend.Services;

import lombok.RequiredArgsConstructor;
import org.backend.bloghalderbackend.Auth.AuthResponse;
import org.backend.bloghalderbackend.Auth.LoginRequest;
import org.backend.bloghalderbackend.Auth.RegisterRequest;
import org.backend.bloghalderbackend.Entities.User;
import org.backend.bloghalderbackend.Jwt.JwtService;
import org.backend.bloghalderbackend.Repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode( request.getPassword()))
                .name(request.getName())
                .country(request.getCountry())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}
