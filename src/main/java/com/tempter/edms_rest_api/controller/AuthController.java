package com.tempter.edms_rest_api.controller;

import com.tempter.edms_rest_api.dto.AuthRequest;
import com.tempter.edms_rest_api.dto.UserDTO;
import com.tempter.edms_rest_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok().body(authService.login(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok().body(authService.register(authRequest));
    }
}
