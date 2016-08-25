/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageauthenticationhsslab;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author MI
 */
public class MessageAuthenticationHSSLAB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
        // TODO code application logic here
        MessageAuthenticationHSSLAB mesAuth = new MessageAuthenticationHSSLAB();
        mesAuth.testDESCompareHash();

    }
    
    // testing script
    public void testDES() throws InvalidKeyException, Exception {
        SecretKey key = KeyGenerator.getInstance("DES").generateKey(); // use only for random key
        String keyString = "12345678";
        DesEncrypter encrypter = new DesEncrypter();
        encrypter.SetSecretKey(keyString);
        String encrypted = encrypter.encrypt("Don't tell anybody!");
        System.out.println(encrypted);    
        String decrypted = encrypter.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
        // testing script
    public void testDESCompareHash() throws InvalidKeyException, Exception {
        SecretKey key = KeyGenerator.getInstance("DES").generateKey(); // use only for random key
        String keyString = "12345678";
        DesEncrypter encrypter = new DesEncrypter();
        encrypter.SetSecretKey(keyString);
        String encrypted = encrypter.encrypt("Don't tell anybody!");
        String decrypted = encrypter.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
        // Comparison
        encrypter.validateBase64(encrypted, encrypter.getBase64Hash());
    }
}
