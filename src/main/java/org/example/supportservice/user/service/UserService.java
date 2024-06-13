package org.example.supportservice.user.service;

import lombok.AllArgsConstructor;
import org.example.supportservice.user.dto.UserDto;
import org.example.supportservice.user.entity.Role;
import org.example.supportservice.user.mapper.UserMapper;
import org.example.supportservice.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity register(UserDto user) {
        user.setRole(Role.User);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(
                userMapper.DtoToEntity(user)
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
