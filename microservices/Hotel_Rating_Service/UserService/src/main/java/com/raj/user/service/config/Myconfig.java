package com.raj.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Myconfig {
    @Bean
    @LoadBalanced // distribution the load into instances
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
