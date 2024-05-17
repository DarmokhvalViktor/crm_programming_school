package com.darmokhval.crm_programming_school.service;

import com.darmokhval.crm_programming_school.config.MyCustomUserDetails;
import com.darmokhval.crm_programming_school.config.jwt.JwtUtils;
import com.darmokhval.crm_programming_school.model.dto.JWTPairResponse;
import com.darmokhval.crm_programming_school.model.dto.JWTRefreshRequest;
import com.darmokhval.crm_programming_school.model.dto.LoginRequest;
import com.darmokhval.crm_programming_school.model.entity.User;
import com.darmokhval.crm_programming_school.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public JWTPairResponse loginUser(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if(userOptional.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), userOptional.get().getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return createJWTPairResponse(userOptional.get());
    }

    public JWTPairResponse refresh(JWTRefreshRequest refreshToken) {
        if(refreshToken == null) {
            throw new IllegalArgumentException("Refresh token is missing");
        }
        jwtUtils.validateToken(refreshToken.getRefreshToken());
        if(!jwtUtils.isRefreshToken(refreshToken.getRefreshToken())) {
            throw new IllegalArgumentException("Provided string is not refresh token");
        }
        String usernameFromToken = jwtUtils.getUsernameFromToken(refreshToken.getRefreshToken());
        Optional<User>user = userRepository.findByName(usernameFromToken);
        if(user.isPresent()) {
            return createJWTPairResponse(user.get());
        }
        throw new IllegalArgumentException("User not found");
    }

    private JWTPairResponse createJWTPairResponse(User user) {
        MyCustomUserDetails userDetails = new MyCustomUserDetails(
                user.getName(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())));
        String accessToken = jwtUtils.generateAccessToken(userDetails);
        String refreshToken = jwtUtils.generateRefreshToken(userDetails);
        return new JWTPairResponse(accessToken, refreshToken);
    }
}
