package com.healthTrackerAPI.healthtrackerAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public interface Security {
    void configure(AuthenticationManagerBuilder auth)  throws  Exception;

    @Bean
    boolean authenticationManagerBean() throws  Exception;
}
