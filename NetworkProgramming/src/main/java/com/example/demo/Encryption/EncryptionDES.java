package com.example.demo.Encryption;

//import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionDES {
    private static EncryptionDES instance;
    final String originKey;
    final SecretKeySpec key;
    Cipher cipher;

    private EncryptionDES() throws NoSuchPaddingException, NoSuchAlgorithmException {
        originKey = "19970831";
        key = new SecretKeySpec(originKey.getBytes(), "DES");
        cipher = Cipher.getInstance("DES");
    }

    public static EncryptionDES getInstance() throws NoSuchAlgorithmException, NoSuchPaddingException {
        if(instance == null){
            instance = new EncryptionDES();
        }
        return instance;
    }

    public String encryptData(String content) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encipherByte = cipher.doFinal(content.getBytes());
        byte[] encode = Base64.getEncoder().encode(encipherByte);
        return new String(encode);
    }

    public String decryptData(String content) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decode = Base64.getDecoder().decode(content);
        return new String(cipher.doFinal(decode));
    }
}
