package com.bank.bankapplication.ServiceImpl;

import com.bank.bankapplication.Constants.AccountConstants;
import com.bank.bankapplication.Dto.AccountDto;
import com.bank.bankapplication.Dto.CustomerDto;
import com.bank.bankapplication.Entity.AccountsEntity;
import com.bank.bankapplication.Entity.CustomerEntity;
import com.bank.bankapplication.Exception.CustomerExistingException;
import com.bank.bankapplication.Exception.ResourceNotFoundException;
import com.bank.bankapplication.MapperCls.AccountMapper;
import com.bank.bankapplication.MapperCls.CustomerMapper;
import com.bank.bankapplication.Repo.AccountRepo;
import com.bank.bankapplication.Repo.CustomerRepo;
import com.bank.bankapplication.Service.AccountSerc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountSerImpl implements AccountSerc {

    private final AccountRepo accountsRepository;

    private final CustomerRepo customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        CustomerEntity customer = CustomerMapper.mapToCustomer(customerDto, new CustomerEntity());
        Optional<CustomerEntity> optionalCustomer = customerRepository.findByMobileNo(customerDto.getMobileNo());

        if (optionalCustomer.isPresent()) {
            throw new CustomerExistingException("Customer Already Register with this Phone Number" + customerDto.getMobileNo());
        }
        CustomerEntity savedCustomer = customerRepository.save(customer);
//        savedCustomer.setCreatedAt(LocalDateTime.now());
//        savedCustomer.setCreatedBy("Anonymous");
        accountsRepository.save(createNewAccount(savedCustomer));
    }


    private AccountsEntity createNewAccount(CustomerEntity customer) {

        AccountsEntity newAccount = new AccountsEntity();
        newAccount.setCustomerID(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNO(randomAccNumber);

//        newAccount.setCreatedAt(LocalDateTime.now());
//        newAccount.setCreatedBy("Anonymous");
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }


    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        CustomerEntity customer = customerRepository.findByMobileNo(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        AccountsEntity accounts = accountsRepository.findByCustomerID(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountsDto(accounts, new AccountDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdate = false;

        AccountDto accountsDto = customerDto.getAccountDto();

        if (accountsDto != null) {

            AccountsEntity accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );

            AccountMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);


            Long customerId = accounts.getCustomerID();

            CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );

            CustomerMapper.mapToCustomer(customerDto, customer);
            customer = customerRepository.save(customer);

            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        CustomerEntity customer = customerRepository.findByMobileNo(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Long customerId = customer.getCustomerId();

//        Accounts accounts = accountsRepository.findByCustomerId(customerId).orElseThrow(
//                () -> new ResourceNotFoundException("Account", "customerId", customerId.toString())
//
//        ); //another way

        accountsRepository.deleteByCustomerID(customerId);
        customerRepository.deleteById(customerId);

        return true;
    }

}
