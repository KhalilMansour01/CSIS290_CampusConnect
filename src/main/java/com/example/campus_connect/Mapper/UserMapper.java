package com.example.campus_connect.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.campus_connect.DTOs.Users.UserRequestDTO;
import com.example.campus_connect.DTOs.Users.UserResponseDTO;
import com.example.campus_connect.Entity.UsersEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Request DTO -> Entity
    @Mapping(target = "id", ignore = true) // ID is system-generated
    @Mapping(target = "createdAt", ignore = true) // Will be auto-set in DB or service
    UsersEntity toEntity(UserRequestDTO dto);

    // Entity -> Response DTO
    UserResponseDTO toDto(UsersEntity entity);
}