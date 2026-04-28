package com.zosh.controller;

import com.zosh.payload.dto.UserDTO;
import com.zosh.payload.request.LoginRequest;
import com.zosh.payload.response.AuthResponse;
import com.zosh.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(
            @RequestBody @Valid UserDTO userDTO
    ) throws Exception {

        AuthResponse response = authService.signup(userDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginRequest req
    ) throws Exception {
        AuthResponse response = authService.login(
                req.getEmail(),
                req.getPassword()
        );
        return ResponseEntity.ok(response);
    }
}