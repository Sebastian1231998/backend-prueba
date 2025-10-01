package com.jiksoft.library.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.jiksoft.library.bo.LibraryBO;
import com.jiksoft.library.dto.request.LibraryDTO;
import com.jiksoft.library.dto.responses.ResponseLibraryDTO;
import com.jiksoft.library.models.library.LibraryEntity;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface LibraryMapper {

    LibraryEntity fromBoToEntity(LibraryBO bo);

    LibraryBO fromEntityToBo(LibraryEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromBoToEntity(LibraryBO bo, @MappingTarget LibraryEntity entity);

    List<LibraryBO> fromEntityListToBoList(List<LibraryEntity> entityList);

    ResponseLibraryDTO fromBoToResponseDto(LibraryBO bo);

    LibraryBO fromDtoToResponseBo(LibraryDTO dto);

    List<ResponseLibraryDTO> fromListBoToListResponseDto(List<LibraryBO> boList);
    
}