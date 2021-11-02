package api.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Cryptor {

    private final static String CRYPTO_PASSWORD = "password";

    private Cryptor() {

    }

    public static String encrypt(String password) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(CRYPTO_PASSWORD);
        return encryptor.encrypt(password);
    }

    public static String decrypt(String encryptedPassword) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(CRYPTO_PASSWORD);
        return decryptor.decrypt(encryptedPassword);
    }


}
