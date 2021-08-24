package com.springland365.UserManagement.simple;

import com.springland365.UserManagement.simple.SimpleUserManagerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@Profile("simple")
public class UserManagementConfig {

    @Bean
    UserDetailsService userDetailsService()
    {

        List<UserDetails> users = List.of(
                User.withUsername("mike").password("1234").authorities(() -> "READ").build(),
                User.withUsername("john").password("1234").authorities(() -> "READ").build()
        );

        return new SimpleUserManagerService(users);
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
}
