package com.khanhnq.loans.mapper;

import com.khanhnq.loans.dto.LoansDto;
import com.khanhnq.loans.entity.Loans;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-23T10:51:10+0700",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class LoansMapperImpl implements LoansMapper {

    @Override
    public LoansDto mapToLoansDto(Loans loans) {
        if ( loans == null ) {
            return null;
        }

        LoansDto loansDto = new LoansDto();

        loansDto.setMobileNumber( loans.getMobileNumber() );
        loansDto.setLoanNumber( loans.getLoanNumber() );
        loansDto.setLoanType( loans.getLoanType() );
        loansDto.setTotalLoan( loans.getTotalLoan() );
        loansDto.setAmountPaid( loans.getAmountPaid() );
        loansDto.setOutstandingAmount( loans.getOutstandingAmount() );

        return loansDto;
    }

    @Override
    public void updateLoans(Loans loans, LoansDto loansDto) {
        if ( loansDto == null ) {
            return;
        }

        loans.setMobileNumber( loansDto.getMobileNumber() );
        loans.setLoanNumber( loansDto.getLoanNumber() );
        loans.setLoanType( loansDto.getLoanType() );
        loans.setTotalLoan( loansDto.getTotalLoan() );
        loans.setAmountPaid( loansDto.getAmountPaid() );
        loans.setOutstandingAmount( loansDto.getOutstandingAmount() );
    }
}
