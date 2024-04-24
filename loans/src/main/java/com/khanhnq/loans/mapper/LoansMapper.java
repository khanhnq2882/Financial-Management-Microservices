package com.khanhnq.loans.mapper;

import com.khanhnq.loans.dto.LoansDto;
import com.khanhnq.loans.entity.Loans;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoansMapper {
    LoansMapper LOANS_MAPPER = Mappers.getMapper(LoansMapper.class);
    LoansDto mapToLoansDto (Loans loans);
    void updateLoans (@MappingTarget Loans loans, LoansDto loansDto);
}
