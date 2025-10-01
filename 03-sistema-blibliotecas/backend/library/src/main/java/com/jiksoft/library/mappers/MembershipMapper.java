package com.jiksoft.library.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.jiksoft.library.bo.MembershipBO;
import com.jiksoft.library.dto.request.MembershipDTO;
import com.jiksoft.library.dto.responses.ResponseMembershipDTO;
import com.jiksoft.library.models.library.MembershipEntity;


@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface MembershipMapper {

    MembershipEntity fromBoToEntity(MembershipBO bo);

    MembershipBO fromEntityToBo(MembershipEntity entity);

    void fromBoToEntity(MembershipBO bo, @MappingTarget MembershipEntity entity);

    List<MembershipBO> fromEntityListToBoList(List<MembershipEntity> entityList);

    ResponseMembershipDTO fromBoToResponseDto(MembershipBO bo);

    List<ResponseMembershipDTO> fromListBoToListResponseDto(List<MembershipBO> boList);

    @Mapping(source = "library.id", target = "libraryId")
    MembershipDTO fromBoToDto(MembershipBO bo);

    @Mapping(source = "libraryId", target = "library.id")
    MembershipBO fromDtoToBo(MembershipDTO dto);
}
