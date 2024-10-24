package com.fintect.MobileMoneyAccountMs.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@Configuration
@ConfigurationProperties(prefix = "accounts")
@Component
@Getter
@Setter
public class AccountsContactInfoDto {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
