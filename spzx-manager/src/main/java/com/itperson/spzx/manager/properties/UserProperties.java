package com.itperson.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "spzx.auth.user")
public class UserProperties {

    private List<String> noAuthUrls;

}
