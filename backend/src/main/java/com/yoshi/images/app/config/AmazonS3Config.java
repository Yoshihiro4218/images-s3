package com.yoshi.images.app.config;

import com.amazonaws.*;
import com.amazonaws.auth.*;
import com.amazonaws.services.s3.*;
import lombok.*;
import org.springframework.context.annotation.*;

import static com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;

@Configuration
@AllArgsConstructor
public class AmazonS3Config {
    private final AmazonS3Properties amazonS3Properties;

    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(amazonS3Properties.getAccessKey(),
                                                             amazonS3Properties.getSecretKey());
        EndpointConfiguration endpointConfiguration = new EndpointConfiguration(amazonS3Properties.getUrl(),
                                                                                amazonS3Properties.getRegion());
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSocketTimeout(amazonS3Properties.getTimeoutMills());
        clientConfiguration.setSignerOverride("AWSS3V4SignerType");
        return AmazonS3ClientBuilder.standard()
                                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                                    .withEndpointConfiguration(endpointConfiguration)
                                    .withClientConfiguration(clientConfiguration)
                                    .withPathStyleAccessEnabled(true)
                                    .build();
    }
}
