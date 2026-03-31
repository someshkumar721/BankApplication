package com.bank.bankapplication.Service;

import com.bank.bankapplication.Dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountSerc {
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
