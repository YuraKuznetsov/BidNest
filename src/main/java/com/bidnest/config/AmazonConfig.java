package com.bidnest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.regions.Region;

@Configuration
public class AmazonConfig {

    @Value("${amazon-s3.credentials.access-key}")
    private String accessKey;
    @Value("${amazon-s3.credentials.secret-key}")
    private String secretKey;

    @Bean
    public S3Client s3() {
        AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        return S3Client.builder()
                .region(Region.EU_CENTRAL_1)
                .credentialsProvider(() -> awsCredentials)
                .build();
    }
}