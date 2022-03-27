package api.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Cryptor {

    private static final String SALT = "password";

    private Cryptor() {

    }

    public static String encrypt(String password) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(SALT);
        return encryptor.encrypt(password);
    }

    public static String decryptValue(String encryptedPassword) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(SALT);
        return encryptor.decrypt(encryptedPassword);
    }

}
