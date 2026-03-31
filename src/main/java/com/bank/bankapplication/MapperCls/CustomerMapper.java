package com.bank.bankapplication.MapperCls;

import com.bank.bankapplication.Dto.CustomerDto;
import com.bank.bankapplication.Entity.CustomerEntity;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(CustomerEntity customer, CustomerDto customerDto) {
        customerDto.setName(customer.getUserName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNo(customer.getMobileNo());
        return customerDto;
    }

    public static CustomerEntity mapToCustomer(CustomerDto customerDto, CustomerEntity customer) {
        customer.setUserName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNo(customerDto.getMobileNo());
        return customer;
    }
}
