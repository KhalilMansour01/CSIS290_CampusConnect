package com.example.campus_connect.Mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.campus_connect.DTOs.Clubs.ClubRequestDTO;
import com.example.campus_connect.DTOs.Clubs.ClubResponseDTO;
import com.example.campus_connect.Entity.ClubsEntity;
import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Repository.UsersRepository;

@Component
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public abstract class ClubMapper {

    @Autowired
    protected UsersRepository usersRepository;

    // Map request DTO to entity
    @Mapping(target = "president", source = "presidentId", qualifiedByName = "mapPresident")
    @Mapping(target = "id", ignore = true)
    public abstract ClubsEntity toEntity(ClubRequestDTO dto);

    // Map entity to response DTO
    public abstract ClubResponseDTO toResponseDto(ClubsEntity entity);

    // Custom method to fetch president by ID
    @Named("mapPresident")
    protected UsersEntity mapPresident(String id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}