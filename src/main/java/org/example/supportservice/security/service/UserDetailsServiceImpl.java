package org.example.supportservice.security.service;

import lombok.AllArgsConstructor;
import org.example.supportservice.user.mapper.UserMapper;
import org.example.supportservice.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return mapper.EntityToDto(
                userRepository.findByUsername(username)
                        .orElseThrow()
        );
    }
}
