package com.khanhnq.accounts.dto;

import com.khanhnq.accounts.annotation.EmailConstraint;
import com.khanhnq.accounts.annotation.MobileNumberConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    @Schema(
            description = "Name of the customer",
            example = "Nguyen Quoc Khanh"
    )
    private String name;

    @EmailConstraint
    @Schema(
            description = "Email of the customer",
            example = "khanhnq@gmail.com"
    )
    private String email;

    @MobileNumberConstraint
    @Schema(
            description = "Mobile number of the customer",
            example = "0987654321"
    )
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDto accountsDto;
}
