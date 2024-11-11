package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.crypto.Cipher;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception{
        // genero una clave utilizando el algoritmo "RSA"
        KeyPairGenerator clau = KeyPairGenerator.getInstance("RSA");

        // cre una instancia de SecureRandom
        SecureRandom secureRandom = new SecureRandom();

        // especifico el tamaño de la llave en bits y le añado el secureRandom, que generará un numero aleatorio seguro
        clau.initialize(2048, secureRandom);

        // genero el KeyPair y lo retorno
        KeyPair keyPair = clau.generateKeyPair();
        return keyPair;
    }
    
    public byte[] xifraRSA(String msg, PublicKey clauPublica)throws Exception{
        // especifico que tipo de algoritmo utilizaré
        Cipher cipher = Cipher.getInstance("RSA");

        // inicializo el cipher en modo encriptado pasandole también la clauPublica
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);

        // Le paso el texto que quiero encriptar, esto me devolderá como resultado el texto encriptado en bytes
        return cipher.doFinal(msg.getBytes());
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception{
        // especifico que tipo de algoritmo utilizaré
        Cipher cipher = Cipher.getInstance("RSA");

        // inicializo el cipher en modo desencriptado pasandole también la clauPrivada
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);

        // Le paso el texto que quiero desencriptar
        byte[] desxifraText = cipher.doFinal(msgXifrat);

        // retorno el descifrado ya leíble
        return new String(desxifraText, "UTF-8");
    }
}
