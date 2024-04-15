package org.backend.bloghalderbackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.backend.bloghalderbackend.Auth.UpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.backend.bloghalderbackend.Services.AuthService;
import org.backend.bloghalderbackend.Auth.AuthResponse;
import org.backend.bloghalderbackend.Auth.LoginRequest;
import org.backend.bloghalderbackend.Auth.RegisterRequest;
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @PutMapping(value = "")
    public ResponseEntity<?> update(@RequestBody UpdateRequest request)
    {
        return authService.update(request);
    }

}