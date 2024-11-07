package iticbcn.xifratge;
/*
 * 
 */
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class XifradorPolialfabetic implements Xifrador{
    public static final char[] ALFMIN = "abcçdefghijklmnñopqrstuvwxyzáàéèïíóúü".toCharArray();
    public static final char[] ALFMAY = "ABCÇDEFGHIJKLMNÑOPQRSTUVWXYZÁÀÉÈÏÍÓÚÜ".toCharArray();
    public static char[] alfMutatMin;
    public static char[] alfMutatMay;
    public static Random random = new Random();
    
    public static long validaLongCau(String clau, boolean esXifrat) throws ClauNoSuportada{
        try {
            return Long.parseLong(clau);
        } catch (NumberFormatException e) {
            if (esXifrat) {
                throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
            } else {
                throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
            }
        }
    }
    
    public static int numCaracter(char[] alfabeto, char caracter) { 
        for (int i = 0; i < alfabeto.length; i++) {
            if(alfabeto[i] == caracter) {
                return i; // retorna posición donde se encuentra el carácter  en el char[]
            }
        }
        return -1; // Retornarà -1 si no lo encuentra.
    }

    public static boolean esTroba(char[] alfabeto,char caracter){ 
        return numCaracter(alfabeto, caracter) != -1;  // retornará true si se encuentra el carácter en el char[]
    }
    
    public static void initRandom(long clau) {
        random = new Random(clau);
    }

    public static void permutaAlfabet(){
        List<Character> listaMin = new ArrayList<>();
        for(char caracter: ALFMIN){
            listaMin.add(caracter); // añadiendo todo el alfabeto original a la lista
        }
        Collections.shuffle(listaMin, random); // lista desordenada
        char[] alfRandomMin = new char[ALFMIN.length];
        for(int i = 0; i < listaMin.size(); i++){
            alfRandomMin[i] = listaMin.get(i); // añadiendo la lista con el alfabeto desordenado al char[]
        }
        alfMutatMin = alfRandomMin;

        List<Character> listaMay = new ArrayList<>();
        for(char caracter: ALFMAY){
            listaMay.add(caracter); // añadiendo todo el alfabeto original a la lista
        }
        Collections.shuffle(listaMay, random); // lista desordenada
        char[] alfRandomMay = new char[ALFMAY.length];
        for(int i = 0; i < listaMay.size(); i++){
            alfRandomMay[i] = listaMay.get(i); // añadiendo la lista con el alfabeto desordenado al char[]
        }
        alfMutatMay = alfRandomMay;
    }

    public static String xifraPoliAlfa(String texto){
        StringBuilder vacio = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            permutaAlfabet();
            if(esTroba(ALFMIN, caracter)|| esTroba(ALFMAY, caracter)){ // true si lo encuentra
                if(Character.isLowerCase(caracter)){ // letras minúsculas
                    vacio.append(alfMutatMin[numCaracter(ALFMIN, caracter)]); // Ubica la posición del caracter en el alfabeto original y reemplaza esa posición en el alfabeto permutado               
                } else if(Character.isUpperCase(caracter)){
                    vacio.append(alfMutatMay[numCaracter(ALFMAY, caracter)]);
                }
            } else {
                vacio.append(caracter);
            }
        }
        return vacio.toString();
    }
    
    public static String desxifraPoliAlfa(String texto){
         StringBuilder vacio = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            permutaAlfabet();
            if(esTroba(ALFMIN, caracter)|| esTroba(ALFMAY, caracter)){ // true si lo encuentra
                if(Character.isLowerCase(caracter)){ // letras minúsculas
                    vacio.append(ALFMIN[numCaracter(alfMutatMin, caracter)]); // Ubica la posición del caracter en el alfabeto permutado y reemplaza esa posición en el alfabeto original               
                } else if(Character.isUpperCase(caracter)){
                    vacio.append(ALFMAY[numCaracter(alfMutatMay, caracter)]);
                }
            } else {
                vacio.append(caracter);
            }
        }
        return vacio.toString();
    } 

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
           long seed = validaLongCau(clau, true);
           initRandom(seed);
           String cifrado = xifraPoliAlfa(msg);
           return new TextXifrat(cifrado.getBytes()); 
        } catch (ClauNoSuportada e) {
            throw e;
        }
    }
    
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{
        try{
            long seed = validaLongCau(clau, false);
           initRandom(seed);
           String textXifrat = new String(xifrat.getBytes());
           return desxifraPoliAlfa(textXifrat);
        } catch (ClauNoSuportada e) {
            throw e;
        }

    }
    
}
