package com.khanhnq.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {
    @NotEmpty(message = "Account number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Account number must be 10 digits")
    @Schema(
            description = "Account number of the account", example = "3344556677"
    )
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be a null or empty")
    @Schema(
            description = "Account type of the account", example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "Branch address can not be a null or empty")
    @Schema(
            description = "Branch address of the account", example = "Ha Noi"
    )
    private String branchAddress;
}
