package com.darmokhval.crm_programming_school.service;

import com.darmokhval.crm_programming_school.config.MyCustomUserDetails;
import com.darmokhval.crm_programming_school.model.entity.User;
import com.darmokhval.crm_programming_school.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException(name));
        return MyCustomUserDetails.build(user);
    }
}
