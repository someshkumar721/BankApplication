package com.bank.bankapplication.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "CustomerEntity",
        description = "Schema to hold customer and Account information"
)
public class CustomerDto {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "email show not be or empty")
    @Email(message = "Email should be a valid value")
    private String email;

    @Pattern(regexp = "($|[0-9]{10})",message = "must be valid mobile no")
    private String mobileNo;

    private AccountDto accountDto;

}
