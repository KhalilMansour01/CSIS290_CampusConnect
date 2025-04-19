package com.example.club_connect.Modules.Clubs;

import com.example.club_connect.Modules.Users.UserMapper;
import com.example.club_connect.Modules.Users.UsersEntity;
import com.example.club_connect.Modules.Users.UsersRepository;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public abstract class ClubMapper {

    @Autowired
    protected UsersRepository usersRepository;

    // Map request DTO to entity
    @Mapping(target = "president", source = "presidentId", qualifiedByName = "mapPresident")
    @Mapping(target = "id", ignore = true)
    public abstract ClubsEntity toEntity(ClubRequestDTO dto);

    // Map entity to response DTO
    public abstract ClubResponseDTO toDto(ClubsEntity entity);

    // Custom method to fetch president by ID
    @Named("mapPresident")
    protected UsersEntity mapPresident(String id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}