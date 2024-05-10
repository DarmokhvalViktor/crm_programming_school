package com.darmokhval.crm_programming_school.config;

import com.darmokhval.crm_programming_school.model.entity.User;
import com.darmokhval.crm_programming_school.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<User> optionalUser = userRepository.findByName(authentication.getName());
        if(optionalUser.isPresent()) {
            if(passwordEncoder.matches(authentication.getCredentials().toString(), optionalUser.get().getPassword())) {
                List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(optionalUser.get().getRole().toString()));
                return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
            } else {
                throw new BadCredentialsException("Wrong password");
            }
        } else {
            throw new BadCredentialsException("Wrong name");
        }
    }
}
