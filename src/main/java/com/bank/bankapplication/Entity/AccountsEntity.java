package com.bank.bankapplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountsEntity extends BasicEntity {

    private Long customerID;

    @Id
    private Long accountNO;

    private String accountType;

    private String branchAddress;
}
