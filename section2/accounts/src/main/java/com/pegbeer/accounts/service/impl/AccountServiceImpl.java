package com.pegbeer.accounts.service.impl;

import com.pegbeer.accounts.constants.AccountsConstants;
import com.pegbeer.accounts.dto.AccountsDto;
import com.pegbeer.accounts.dto.CustomerDto;
import com.pegbeer.accounts.entity.Accounts;
import com.pegbeer.accounts.entity.Customer;
import com.pegbeer.accounts.exception.CustomerAlreadyExistsException;
import com.pegbeer.accounts.exception.ResourceNotFoundException;
import com.pegbeer.accounts.mapper.AccountsMapper;
import com.pegbeer.accounts.mapper.CustomerMapper;
import com.pegbeer.accounts.repository.AccountsRepository;
import com.pegbeer.accounts.repository.CustomerRepository;
import com.pegbeer.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service @AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public long createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toCustomer(customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException();
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
        return savedCustomer.getCustomerId();
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto getAccount(String mobileNumber) {
        Customer customer = customerRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(ResourceNotFoundException::new);
        Accounts account = accountsRepository
                .findByCustomerId(customer.getCustomerId())
                .orElseThrow(ResourceNotFoundException::new);
        return CustomerMapper.toCustomerDto(customer, AccountsMapper.toAccountsDto(account));
    }
}
