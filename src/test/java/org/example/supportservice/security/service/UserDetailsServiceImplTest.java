package org.example.supportservice.security.service;

import org.example.supportservice.user.dto.UserDto;
import org.example.supportservice.user.entity.UserEntity;
import org.example.supportservice.user.mapper.UserMapper;
import org.example.supportservice.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserDetailsServiceImplTest {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userMapper = Mockito.mock(UserMapper.class);
        userDetailsService = new UserDetailsServiceImpl(userRepository, userMapper);
    }

    @Test
    void itShouldLoadUserByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID());
        userEntity.setUsername("testUser");
        userEntity.setPassword("testPassword");

        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());

        when(userRepository.findByUsername(userEntity.getUsername())).thenReturn(Optional.of(userEntity));
        when(userMapper.EntityToDto(userEntity)).thenReturn(userDto);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());

        assertEquals(userDto.getUsername(), userDetails.getUsername());
        assertEquals(userDto.getPassword(), userDetails.getPassword());
    }

    @Test
    void itShouldLoadUserByUsername_NotFound() {
        String username = "nonExistingUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userDetailsService.loadUserByUsername(username));
    }
}