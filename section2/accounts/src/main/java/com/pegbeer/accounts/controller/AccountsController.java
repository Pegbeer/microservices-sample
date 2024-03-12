package com.pegbeer.accounts.controller;

import com.pegbeer.accounts.dto.CustomerDto;
import com.pegbeer.accounts.dto.ResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello world");
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CustomerDto customerDto, UriComponentsBuilder ucb){
        return ResponseEntity.ok(null);
    }
}
