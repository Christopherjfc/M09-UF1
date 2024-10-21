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

public class XifratAes {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    
    public static final int MIDA_IV = 16;
    public static byte[] iv = new byte[MIDA_IV];
    public static final String CLAU = "coco";

    public static byte[] xifraAES(String text, String clau) throws Exception {
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
        return concatenaEncriptat;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
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
        byte[] hash = msgDigest.digest(bytesArreglats);
        SecretKeySpec secretKeySpec = new SecretKeySpec(hash, ALGORISME_XIFRAT);
        
        // Desxifrar.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParSpec);
        byte[] desxifraText = cipher.doFinal(cipherText);
        
        // return String desxifrat
        return new String(desxifraText, "UTF-8");
    }


    
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
        "Hola Andrés cómo está tu cuñado",
        "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES (bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + 
                e.getLocalizedMessage ());
            }
            System.out.println("--------------------" );
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
        System.out.println(); 
    }
}
