package se751;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class AESEncrypt {
    private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = cipher.doFinal(Data.getBytes());
        return new BASE64Encoder().encode(encVal);
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = cipher.doFinal(decodedValue);
        return new String(decValue);
    }
    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
    }

}
