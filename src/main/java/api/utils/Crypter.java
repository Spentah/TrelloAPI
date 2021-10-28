package api.utils;

import javax.crypto.*;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Crypter {

//    private final String PASS = System.getProperty("bibaPassword");
    private Cipher cipher;

    {
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public Key generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(256);
        Key key = keygen.generateKey();
        return key;
    }

    public byte[] encrypt(Key key, String pass) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(pass.getBytes());
            return encrypted;
//            System.out.println(DatatypeConverter.printHexBinary(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(Key key, byte[] encrypted) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            String result = new String(cipher.doFinal(encrypted));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
