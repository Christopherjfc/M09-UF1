package iticbcn.xifratge;
/*
 * 
 */

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Arrays;

public class XifradorAES implements Xifrador{
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    public static final int MIDA_IV = 16;
    public static byte[] iv = new byte[MIDA_IV];

    public TextXifrat xifra(String text, String clau) throws ClauNoSuportada {
        if (clau.isBlank() || clau == null) {
            throw new ClauNoSuportada("ERROR: la clau no por ser buida o null");
        }
        try {
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(iv); // llena el iv con bytes aleatorios (criptográficamente seguros). 
    
            //Obtenir els bytes de l’String
            byte[] textBytes = text.getBytes("UTF-8"); // genera els bytes del text i l'emmagatzema en un array bytes
            byte[] clauBytes = clau.getBytes("UTF-8"); // genera els bytes de la clau i l'emmagatzema en un array bytes
            byte[] bytesArreglats = Arrays.copyOf(clauBytes, MIDA_IV); // com utilitzaré "AES" hauré d'ajustar la clau a 128 bits - 16 bytes 
            
            // Genera IvParameterSpec
            IvParameterSpec ivParSpec = new IvParameterSpec(iv); // encapsula l'array de bytes en un objecte per a així poder utilitzar-lo
            
            // Genera hash
            MessageDigest msgDigest = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] hash = msgDigest.digest(bytesArreglats);
            SecretKeySpec secretKeySpec = new SecretKeySpec(hash, ALGORISME_XIFRAT);
            
            // Encrypt.
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParSpec);
            byte[] cipherText = cipher.doFinal(textBytes);
           
            // Combinar IV i part xifrada.
            byte[] concatenaEncriptat = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, concatenaEncriptat, 0, iv.length);
            System.arraycopy(cipherText, 0, concatenaEncriptat, iv.length, cipherText.length);
    
            // return iv+msgxifrat
            return new TextXifrat(concatenaEncriptat);
            
        } catch (Exception e) {
            throw new ClauNoSuportada("Error detectat al xifrar: " + e.getMessage());
        }
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau.isBlank() || clau == null) {
            throw new ClauNoSuportada("ERROR: la clau no por ser buida o null");
        }
        try {
            byte[] bIvIMsgXifrat = xifrat.getBytes(); // agafa els bytes del text xifrat
    
            // Extreure l'IV.
            System.arraycopy(bIvIMsgXifrat, 0, iv, 0, iv.length); // copia los primeros 16 bytes del texto cifrado al array iv
            IvParameterSpec ivParSpec = new IvParameterSpec(iv);
            
            // Extreure la part xifrada.
            byte[] cipherText = new byte[bIvIMsgXifrat.length -16];
            System.arraycopy(bIvIMsgXifrat, iv.length, cipherText, 0, cipherText.length);
            
            // Fer hash de la clau
            byte[] clauBytes = clau.getBytes("UTF-8"); // genera els bytes de la clau i l'emmagatzema en un array bytes
            byte[] bytesArreglats = Arrays.copyOf(clauBytes, MIDA_IV); // com utilitzaré "AES" hauré d'ajustar la clau a 128 bits - 16 bytes 
            MessageDigest msgDigest = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] hash = msgDigest.digest(bytesArreglats) ;
            SecretKeySpec secretKeySpec = new SecretKeySpec(hash, ALGORISME_XIFRAT);
            
            // Desxifrar.
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParSpec);
            byte[] desxifraText = cipher.doFinal(cipherText);
            
            // return String desxifrat
            return new String(desxifraText, "UTF-8");
        } catch (Exception e) {
            throw new ClauNoSuportada("Error detectat al desxifrar: " + e.getMessage());
        }

    }
}
