package com.example.ss_day1_ex2.services;

import com.example.ss_day1_ex2.model.security.UserAdapter;
import com.example.ss_day1_ex2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .map(UserAdapter::new)  // u -> new UserAdapter(u)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
