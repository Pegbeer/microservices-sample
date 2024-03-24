package com.pegbeer.accounts.controller;


import com.pegbeer.accounts.dto.CustomerDto;
import com.pegbeer.accounts.dto.ResponseDto;
import com.pegbeer.accounts.service.IAccountService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

    private IAccountService accountService;

    public AccountsController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello world");
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CustomerDto customerDto, UriComponentsBuilder ucb){
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
}
