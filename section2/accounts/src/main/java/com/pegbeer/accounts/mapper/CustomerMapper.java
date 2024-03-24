package com.pegbeer.accounts.mapper;

import com.pegbeer.accounts.dto.CustomerDto;
import com.pegbeer.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto toCustomerDto(Customer customer){
        return new CustomerDto(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber());
    }

    public static Customer toCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setMobileNumber(customerDto.mobileNumber());
        return customer;
    }

}
