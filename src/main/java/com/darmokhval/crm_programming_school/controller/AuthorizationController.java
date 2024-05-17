package com.darmokhval.crm_programming_school.controller;

import com.darmokhval.crm_programming_school.model.dto.JWTPairResponse;
import com.darmokhval.crm_programming_school.model.dto.JWTRefreshRequest;
import com.darmokhval.crm_programming_school.model.dto.LoginRequest;
import com.darmokhval.crm_programming_school.service.AuthorizationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Authorization")
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<JWTPairResponse> loginUser(
            @RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorizationService.loginUser(loginRequest));
    }
//    @PostMapping("/register")
//    public ResponseEntity<JWTPairResponse> registerUser(
//            @RequestBody @Valid RegisterRequest registerRequest) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(authorizationService.registerUser(registerRequest));
//    }
    @PostMapping("/refresh")
//    @SecurityRequirement(name = "bearerAuth")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JWTPairResponse> refresh(
            @RequestBody JWTRefreshRequest refreshToken) {
        return ResponseEntity.status(HttpStatus.OK).body(authorizationService.refresh(refreshToken));
    }

}
