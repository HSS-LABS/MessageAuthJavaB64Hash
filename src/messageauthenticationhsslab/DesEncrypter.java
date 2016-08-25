/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageauthenticationhsslab;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.util.Base64;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesEncrypter {
  Cipher ecipher;
  Cipher dcipher;
  private String base64Hash;
  
  public DesEncrypter(){
  }


  public String encrypt(String str) throws Exception {
    // Encode the string into bytes using utf-8
    byte[] utf8 = str.getBytes("UTF8");
    
    // Encrypt
    byte[] enc = ecipher.doFinal(utf8);
    // Set hash to be retrieved later
    String encString  = new sun.misc.BASE64Encoder().encode(enc);
    base64Hash = Base64.getEncoder().encodeToString(encString.getBytes());
    System.out.println(Base64.getEncoder().encodeToString(encString.getBytes()));
    System.out.println(base64Hash);
    // Encode bytes to base64 to get a string
    return encString;
  }
  
  public String getBase64Hash() {
      return base64Hash;
  }
  
  public void validateBase64(String plainText, String base64HashString) {
      String compareBase64Hash = Base64.getEncoder().encodeToString
        (plainText.getBytes());
      System.out.println("Original hash : "+base64HashString);
      System.out.println("Generated hash : "+compareBase64Hash);
      if (compareBase64Hash.equals(base64HashString))
          System.out.println("Message is not Tampered With");
      else
          System.out.println("Message was Tampered With");
  }

  public String decrypt(String str) throws Exception {
    // Decode base64 to get bytes
    byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

    byte[] utf8 = dcipher.doFinal(dec);

    // Decode using utf-8
    return new String(utf8, "UTF8");
  }
  
  public void SetSecretKey(String password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
    byte key[] = password.getBytes();
    DESKeySpec desKeySpec = new DESKeySpec(key);
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
    ecipher = Cipher.getInstance("DES");
    dcipher = Cipher.getInstance("DES");
    ecipher.init(Cipher.ENCRYPT_MODE, secretKey);
    dcipher.init(Cipher.DECRYPT_MODE, secretKey);
    
  }
}



//public class Main {
//  public static void main(String[] argv) throws Exception {
//    SecretKey key = KeyGenerator.getInstance("DES").generateKey();
//    DesEncrypter encrypter = new DesEncrypter(key);
//    String encrypted = encrypter.encrypt("Don't tell anybody!");
//    String decrypted = encrypter.decrypt(encrypted);
//  }
//}
