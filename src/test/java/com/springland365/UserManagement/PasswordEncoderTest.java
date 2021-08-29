package com.springland365.UserManagement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class PasswordEncoderTest {

    @Test
    public void testNoOPPasswordEncoder() {
        PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();

        String encoded = encoder.encode("password");
        assertTrue(encoder.matches("password", encoded));
    }

    @Test
    public void testPbkdf2PasswordEncoder(){
        PasswordEncoder encoder = new Pbkdf2PasswordEncoder();

        String rawPassword = "password";
        String encoded1 = encoder.encode(rawPassword);
        log.info("First round Raw:" + rawPassword + " encoded:" + encoded1);
        assertTrue(encoder.matches(rawPassword , encoded1));


        String encoded2 = encoder.encode(rawPassword);
        log.info("Seond Round Raw:" + rawPassword + " encoded:" + encoded2);
        assertTrue(encoder.matches(rawPassword , encoded2));

        // encoded changes when the same password is encoded second time
        assertNotEquals(encoded1 , encoded2);

        encoder = new Pbkdf2PasswordEncoder("secret");
        encoded1 = encoder.encode(rawPassword);
        log.info("Specify secret,  Raw:" + rawPassword + " encoded:" + encoded1);
        assertTrue(encoder.matches(rawPassword , encoded1));

        encoder = new Pbkdf2PasswordEncoder("secret" , 16);
        encoded1 = encoder.encode(rawPassword);
        log.info("First round , Specify secret and slat length,  Raw:" + rawPassword + " encoded:" + encoded1);
        assertTrue(encoder.matches(rawPassword , encoded1));

        encoded2 = encoder.encode(rawPassword);
        log.info("Second round , Specify secret and slat length,  Raw:" + rawPassword + " encoded:" + encoded2);
        assertTrue(encoder.matches(rawPassword , encoded2));

        // encoded changes when the same password is encoded second time
        assertNotEquals(encoded1 , encoded2);
    }

    @Test
    public void testBCryptPasswordEncoder()
    {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encoded1 = encoder.encode(rawPassword);
        log.info("First round Raw:" + rawPassword + " encoded:" + encoded1);
        assertTrue(encoder.matches(rawPassword , encoded1));

        String encoded2 = encoder.encode(rawPassword);
        log.info("Second round Raw:" + rawPassword + " encoded:" + encoded2);
        assertTrue(encoder.matches(rawPassword , encoded2));

        //BCryptPasswordEncoder generate consistent encoded password each time

        assertNotEquals(encoded1 , encoded2);

        encoder = new BCryptPasswordEncoder(16);
        encoded1 = encoder.encode(rawPassword);
        log.info("With Strength First round Raw:" + rawPassword + " encoded:" + encoded1);
        assertTrue(encoder.matches(rawPassword , encoded1));

        encoded2 = encoder.encode(rawPassword);
        log.info("With Strength Second round Raw:" + rawPassword + " encoded:" + encoded2);
        assertTrue(encoder.matches(rawPassword , encoded2));

        assertNotEquals(encoded1 ,encoded2);

        encoder = new BCryptPasswordEncoder(16 , new SecureRandom());
        encoded1 = encoder.encode(rawPassword);
        log.info("With Strength and SecureRandom First round Raw:" + rawPassword + " encoded:" + encoded1);
        assertTrue(encoder.matches(rawPassword , encoded1));

        encoded2 = encoder.encode(rawPassword);
        log.info("With Strength  and SecureRandom Second round Raw:" + rawPassword + " encoded:" + encoded2);
        assertTrue(encoder.matches(rawPassword , encoded2));

        assertNotEquals(encoded1 , encoded2);
    }

    @Test
    public void testSCryptPasswordEncoder()
    {
        PasswordEncoder encoder = new SCryptPasswordEncoder() ;
        String rawPassword = "password";
        String encoded1 ;
        String encoded2 ;


        encoded1 = encoder.encode(rawPassword);
        log.info("First round Raw:" + rawPassword + " encoded:" + encoded1);
        assertTrue(encoder.matches(rawPassword , encoded1));

        encoded2 = encoder.encode(rawPassword);
        log.info("Second round Raw:" + rawPassword + " encoded:" + encoded2);
        assertTrue(encoder.matches(rawPassword , encoded2));

        assertNotEquals(encoded1 , encoded2);
    }

    @Test
    public void encodePassword()
    {
        PasswordEncoder encoder= new BCryptPasswordEncoder();
        log.info("Bcrypt " + encoder.encode("12345"));

        encoder = new BCryptPasswordEncoder(16 , new SecureRandom());
        log.info("Bcrypt Secure Strength and Secure Random:" + encoder.encode("12345"));

        encoder = new SCryptPasswordEncoder();
        log.info("SCrypt " + encoder.encode("12345"));
    }


}
