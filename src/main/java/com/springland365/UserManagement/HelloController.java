package com.springland365.UserManagement;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello()
    {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        return "Hello " + auth.getName() + "\n";
    }

    @GetMapping("/greeting")
    public String greeting(Authentication auth)
    {
        return "Greeting " + auth.getName() + "!\n";
    }
}
