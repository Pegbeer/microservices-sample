package com.pegbeer.accounts.service;

import com.pegbeer.accounts.dto.AccountsDto;
import com.pegbeer.accounts.dto.CustomerDto;

public interface IAccountService {
    long createAccount(CustomerDto customerDto);

    CustomerDto getAccount(String mobileNumber);
}