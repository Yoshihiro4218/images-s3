package com.yoshi.images.app.config;

import lombok.*;
import org.hibernate.validator.constraints.*;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;

import javax.validation.constraints.*;
import javax.validation.constraints.NotEmpty;

@Component
@ConfigurationProperties("s3")
@Data
@Validated
public class AmazonS3Properties {
    @URL
    private String url;
    @NotEmpty
    private String accessKey;
    @NotEmpty
    private String secretKey;
    @Min(1)
    private int timeoutMills;
    @NotEmpty
    private String region;
    @NotEmpty
    private String bucketName;
}

