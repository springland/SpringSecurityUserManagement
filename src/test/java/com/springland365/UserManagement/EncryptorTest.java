package com.springland365.UserManagement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class EncryptorTest {
    @Test
    public void testByteStandard()
    {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        BytesEncryptor encryptor = Encryptors.standard(password , salt);
        byte[] encrypted = encryptor.encrypt(valueToEncrypt.getBytes(StandardCharsets.UTF_8));
        byte[] decrypted = encryptor.decrypt(encrypted);

        String valueDecrypted = new String(decrypted ,StandardCharsets.UTF_8);
        assertEquals(valueToEncrypt , valueDecrypted);
    }

    @Test
    public void testByteStronger()
    {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        BytesEncryptor encryptor = Encryptors.stronger(password , salt);
        byte[] encrypted = encryptor.encrypt(valueToEncrypt.getBytes(StandardCharsets.UTF_8));
        byte[] decrypted = encryptor.decrypt(encrypted);

        String valueDecrypted = new String(decrypted ,StandardCharsets.UTF_8);
        assertEquals(valueToEncrypt , valueDecrypted);

    }

    @Test
    public void testTextNoOp()
    {
        String valueToEncrypt = "HELLO";
        TextEncryptor e = Encryptors.noOpText();
        String encrypted = e.encrypt(valueToEncrypt);
        log.info(encrypted);
        assertEquals(encrypted , valueToEncrypt);
    }

    @Test
    public void testTextEncryptor()
    {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        TextEncryptor encryptor = Encryptors.text(password , salt);
        String encrypted = encryptor.encrypt(valueToEncrypt);
        String decrypted = encryptor.decrypt(encrypted);
        assertEquals(valueToEncrypt , decrypted);
        log.info(encrypted);
    }

    @Test
    public void testQueryableText()
    {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        TextEncryptor encryptor = Encryptors.queryableText(password , salt);
        String encrypted = encryptor.encrypt(valueToEncrypt);
        String decrypted = encryptor.decrypt(encrypted);
        assertEquals(valueToEncrypt , decrypted);
        log.info(encrypted);

    }
}
