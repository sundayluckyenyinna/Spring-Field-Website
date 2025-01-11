package com.springfield.website.modules.generic.email;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Data
@ToString
@Validated
@ConfigurationProperties(prefix = "email.smtp")
public class EmailConfigurationProperties {

    private String hostName;
    private String port;
    private String username;
    private String password;
    private String from;
}
