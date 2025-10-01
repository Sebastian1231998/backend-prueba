package com.jiksoft.library.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.jiksoft.library.bo.BookBO;
import com.jiksoft.library.bo.LibraryBO;
import com.jiksoft.library.dto.request.BookDTO;
import com.jiksoft.library.dto.responses.ResponseBookDTO;
import com.jiksoft.library.models.library.BookEntity;
import com.jiksoft.library.models.library.LibraryEntity;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface BookMapper {

    BookEntity fromBoToEntity(BookBO bo);

    BookBO fromEntityToBo(BookEntity entity);
    
    void fromBoToEntity(BookBO bo, @MappingTarget BookEntity entity);

    List<BookBO> fromEntityListToBoList(List<BookEntity> entityList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromBoToEntity(LibraryBO bo, @MappingTarget LibraryEntity entity);

    @Mapping(source = "library.id", target = "libraryId")
    ResponseBookDTO fromBoToResponseDto(BookBO bo);

    @Mapping(source = "libraryId", target = "library.id")
    BookBO fromDtoToBo(BookDTO dto);

    @Mapping(source = "library.id", target = "libraryId")
    BookDTO fromBoToDto(BookBO bo);

    List<ResponseBookDTO> fromListBoToListResponseDto(List<BookBO> boList);
    
}