package com.pegbeer.accounts.controller;


import com.pegbeer.accounts.dto.AccountsContactDto;
import com.pegbeer.accounts.dto.CustomerDto;
import com.pegbeer.accounts.dto.ResponseDto;
import com.pegbeer.accounts.exception.InvalidPathVariableException;
import com.pegbeer.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private final IAccountService accountService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment env;

    @Autowired
    private AccountsContactDto accountsContactDto;

    public AccountsController(IAccountService accountService) {
        this.accountService = accountService;
    }


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

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.ok(buildVersion);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.ok(env.getProperty("JAVA_HOME"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactDto> getContactInfo(){
        return ResponseEntity.ok(accountsContactDto);
    }
}
