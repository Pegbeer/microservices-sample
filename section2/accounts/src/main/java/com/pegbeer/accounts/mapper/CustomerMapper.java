package com.pegbeer.accounts.mapper;

import com.pegbeer.accounts.dto.CustomerDto;
import com.pegbeer.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto toCustomerDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer toCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}
