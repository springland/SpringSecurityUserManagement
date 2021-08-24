package com.springland365.UserManagement.simple;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class SimpleUserManagerService implements UserDetailsService {

    final List<UserDetails> users ;

    public SimpleUserManagerService(List<UserDetails> users)
    {
        this.users = users;
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        return this.users.stream().filter( u -> u.getUsername().equals(userName))
                .findFirst()
                .orElseThrow(
                        () -> new UsernameNotFoundException(userName + " not found")
                );


    }
}
