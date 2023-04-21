package com.cpan282.clotheswarehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("authentication/login");
        registry.addViewController("/access-denied").setViewName("authentication/access-denied");

    }

    /*
     * We create a new RestTemplate bean to use for all requests.
     * It will help us to fetch data from the Distribution Centre API
     */
    // We will use it to get Distribution Centres from the Distribution Centres App
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
