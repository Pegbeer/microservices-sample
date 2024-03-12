package com.pegbeer.accounts.service;

import com.pegbeer.accounts.dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);
}
