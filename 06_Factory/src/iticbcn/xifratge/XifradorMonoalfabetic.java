package iticbcn.xifratge;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class XifradorMonoalfabetic implements Xifrador{
    public static final char[] ALFMIN = "abcçdefghijklmnñopqrstuvwxyzáàéèïíóúü".toCharArray();
    public static final char[] ALFMAY = "ABCÇDEFGHIJKLMNÑOPQRSTUVWXYZÁÀÉÈÏÍÓÚÜ".toCharArray();
    
    public char[] alfMutatMay;
    public char[] alfMutatMin;

    public XifradorMonoalfabetic(){
        this.alfMutatMin = permutaAlfabet(ALFMIN);
        this.alfMutatMay = permutaAlfabet(ALFMAY);
    }

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String cifrado = xifraMonoAlfa(msg);
        return new TextXifrat(cifrado.getBytes()); 
    }
    
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String textXifrat = new String(xifrat.getBytes());
        return desxifraMonoAlfa(textXifrat);
    }

    public int numCaracter(char[] alfabeto, char caracter) { 
        for (int i = 0; i < alfabeto.length; i++) {
            if(alfabeto[i] == caracter) {
                return i; // retorna posición donde se encuentra el carácter  en el char[]
            }
        }
        return -1; // Retornarà -1 si no lo encuentra.
    }

    public boolean esTroba(char[] alfabeto,char caracter){ 
        return numCaracter(alfabeto, caracter) != -1;  // retornará true si se encuentra el carácter en el char[]
    }

    public char[] permutaAlfabet(char[] alfabeto){
        List<Character> lista = new ArrayList<>();
        for(char caracter: alfabeto){
            lista.add(caracter); // añadiendo todo el alfabeto original a la lista
        }
        Collections.shuffle(lista); // lista desordenada
        char[] alfrandom = new char[alfabeto.length];
        for(int i = 0; i < lista.size(); i++){
            alfrandom[i] = lista.get(i); // añadiendo la lista con el alfabeto desordenado al char[]
        }
        return alfrandom; //retorna el char[] con el alfabeto desordenado;
    }

    public String xifraMonoAlfa(String texto){
        StringBuilder vacio = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
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
    
    public String desxifraMonoAlfa(String texto){
         StringBuilder vacio = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
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
}
