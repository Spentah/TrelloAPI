package api.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Cryptor {

    private final static String SALT = "password";

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

//    public static void main(String[] args) {
//        DatabaseExecutor.getConnect();
//        String user = "bibathecoder@gmail.com";
//        String encrypted = encrypt(user);
//        System.out.println(encrypted);
//        System.out.println(decryptValue(encrypted));
////        System.out.println(DatabaseExecutor.executeValue("login"));
//
//        DatabaseExecutor.closeConnect();
//    }


}
