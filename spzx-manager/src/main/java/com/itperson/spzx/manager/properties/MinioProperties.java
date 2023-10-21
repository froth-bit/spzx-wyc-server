package com.itperson.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spzx.minio")
public class MinioProperties {

    String endpointUrl;
    String accessKey;
    String secretKey;
    String bucketName;

}
