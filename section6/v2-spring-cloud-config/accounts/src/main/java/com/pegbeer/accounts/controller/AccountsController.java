package com.pegbeer.accounts.controller;


import com.pegbeer.accounts.dto.AccountsContactDto;
import com.pegbeer.accounts.dto.CustomerDto;
import com.pegbeer.accounts.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RefreshScope
@RestController
@RequestMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private IAccountService accountService;

    @Autowired
    private AccountsContactDto accountsContactDto;

    public AccountsController(IAccountService accountService){
        this.accountService = accountService;
    }

    private final String mobileNumberPattern = "(^$|[0-9]{8})";


    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CustomerDto customerDto, UriComponentsBuilder ucb){
        long newAccountNumber = accountService.createAccount(customerDto);
        URI locationOfNewAccount = ucb.path("accounts/{id}")
                .buildAndExpand(newAccountNumber)
                .toUri();
        return ResponseEntity.created(locationOfNewAccount).build();
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<CustomerDto> get(@PathVariable String mobileNumber){
        CustomerDto customer = accountService.getAccount(mobileNumber);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactDto> getContactInfo(){
        System.out.print(accountsContactDto.toString());
        return ResponseEntity.ok(accountsContactDto);
    }
}
