package com.bank.bankapplication.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(name ="AccountsEntity",
description = "Schema to hold Account information"
)
public class AccountDto {

    @NotEmpty
    private Long accountNumber;

    @NotEmpty(message = "cannot to null or empty")
    private String accountType;

    @NotEmpty(message = "cannot be null or empty")
    private String branchAddress;
}
