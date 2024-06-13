package org.example.supportservice.user.mapper;

import org.example.supportservice.user.dto.UserDto;
import org.example.supportservice.user.entity.Role;
import org.example.supportservice.user.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private UserMapper userMapper;
    private UserDto userDto;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();

        UUID id = UUID.randomUUID();
        String username = "testUser";
        String password = "testPassword";
        Role role = Role.User;

        userDto = new UserDto();
        userDto.setId(id);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setRole(role);

        userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setRole(role);
    }

    @Test
    void itShouldEntityToDto() {
        UserDto mappedDto = userMapper.EntityToDto(userEntity);

        assertEquals(userDto.getId(), mappedDto.getId());
        assertEquals(userDto.getUsername(), mappedDto.getUsername());
        assertEquals(userDto.getPassword(), mappedDto.getPassword());
        assertEquals(userDto.getRole(), mappedDto.getRole());
    }

    @Test
    void itShouldDtoToEntity() {
        UserEntity mappedEntity = userMapper.DtoToEntity(userDto);

        assertEquals(userEntity.getId(), mappedEntity.getId());
        assertEquals(userEntity.getUsername(), mappedEntity.getUsername());
        assertEquals(userEntity.getPassword(), mappedEntity.getPassword());
        assertEquals(userEntity.getRole(), mappedEntity.getRole());
    }
}