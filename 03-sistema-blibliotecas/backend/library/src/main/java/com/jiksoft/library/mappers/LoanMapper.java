package com.jiksoft.library.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.jiksoft.library.bo.LoanBO;
import com.jiksoft.library.dto.responses.ResponseLoanDTO;
import com.jiksoft.library.models.library.LoanEntity;

import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface LoanMapper {

    // Entity ↔ BO
    LoanEntity fromBoToEntity(LoanBO bo);

    LoanBO fromEntityToBo(LoanEntity entity);

    void fromBoToEntity(LoanBO bo, @MappingTarget LoanEntity entity);

    List<LoanBO> fromEntityListToBoList(List<LoanEntity> entityList);

    ResponseLoanDTO fromBoToResponseDto(LoanBO bo);

    List<ResponseLoanDTO> fromListBoToListResponseDto(List<LoanBO> boList);
}
