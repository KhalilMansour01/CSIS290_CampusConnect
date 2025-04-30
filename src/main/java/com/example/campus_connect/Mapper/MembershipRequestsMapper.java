package com.example.campus_connect.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.campus_connect.DTOs.MembershipRequests.MembershipRequestDTO;
import com.example.campus_connect.Entity.MembershipRequestsEntity;
import com.example.campus_connect.DTOs.MembershipRequests.MembershipResponseDTO;

@Mapper(componentModel = "spring")
public interface MembershipRequestsMapper {

    // @Mapping(source = "user.id", target = "userId")
    // @Mapping(source = "club.id", target = "clubId")
    // MembershipRequestDTO toRequestDTO(MembershipRequestsEntity entity);

    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "club", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "requestDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    MembershipRequestsEntity toEntity(MembershipRequestDTO dto);

    MembershipResponseDTO toResponseDTO(MembershipRequestsEntity entity);
}