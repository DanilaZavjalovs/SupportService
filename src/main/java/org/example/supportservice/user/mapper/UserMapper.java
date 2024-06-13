package org.example.supportservice.user.mapper;

import org.example.supportservice.user.dto.UserDto;
import org.example.supportservice.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserDto EntityToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());
        userDto.setRole(userEntity.getRole());

        return userDto;
    }

    public UserEntity DtoToEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDto.getId());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setRole(userDto.getRole());

        return userEntity;
    }
}
