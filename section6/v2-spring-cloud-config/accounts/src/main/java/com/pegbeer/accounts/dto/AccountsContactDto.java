package com.pegbeer.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties("accounts")
@Getter
@Setter
public class AccountsContactDto {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}