import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Monoalfabetic {
    public static final char[] ALFMIN = "abcçdefghijklmnñopqrstuvwxyzáàéèïíóúü".toCharArray();
    public static final char[] ALFMAY = "ABCÇDEFGHIJKLMNÑOPQRSTUVWXYZÁÀÉÈÏÍÓÚÜ".toCharArray();
    
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


    public static char[] permutaAlfabet(char[] alfabeto){
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


    public static char[] alfMutatMin = permutaAlfabet(ALFMIN);
    public static char[] alfMutatMay = permutaAlfabet(ALFMAY);


    public static String xifraMonoAlfa(String texto){
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
    
    
    public static String desxifraMonoAlfa(String texto){
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

    
    public static void main(String[] args) {
        String[] pruebas = {"Hola cÓmo Estás!", "Java-17", "Soy Christopher Flores", "Tengo 20 años!",
                            "pingüino", "una excelente prueba", "Quentin Tarantino"};
        System.out.printf("      %-35s    %-35s    %-35s%n", "TEXTO ORIGINAL", "TEXTO CIFRADO", "TEXTO DESCIFRADO");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        for (String prueba : pruebas) {
            String cifrado = xifraMonoAlfa(prueba);
            String descifrado = desxifraMonoAlfa(cifrado);
            System.out.printf("%-35s => %-35s => %-35s%n", prueba, cifrado, descifrado);
        }
    }
}
