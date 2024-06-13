package org.example.supportservice.user.service;

import org.example.supportservice.user.dto.UserDto;
import org.example.supportservice.user.entity.Role;
import org.example.supportservice.user.entity.UserEntity;
import org.example.supportservice.user.mapper.UserMapper;
import org.example.supportservice.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserDto userDto = new UserDto();
    private UserEntity userEntity = new UserEntity();

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, userMapper, passwordEncoder);

        userDto.setUsername("username");
        userDto.setPassword("password");
        userDto.setRole(Role.User);

        userEntity.setUsername("username");
        userEntity.setPassword("encodedPassword");
        userEntity.setRole(Role.User);
    }

    @Test
    void itShouldRegister() {
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(userMapper.DtoToEntity(userDto)).thenReturn(userEntity);

        ResponseEntity response = userService.register(userDto);

        verify(passwordEncoder).encode("password");
        verify(userMapper).DtoToEntity(userDto);
        verify(userRepository).save(userEntity);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}