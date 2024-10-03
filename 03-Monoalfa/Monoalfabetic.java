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


    /*
     * Generar una LISTA de CHARACTER pasarle el metodo shuffle y retornar 
     * un char[]
     */
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


    /*
     * En xifraMonoAlfa tendremos que encontrar en numero de posicion que 
     * sacaremos del caracter del texto y en el texto original, luego 
     * con esa misma posicion agarraremos el alfabetoMutado y lo sustituiremos con esa misma 
     * posición.
     * 
     */
    public static String xifraMonoAlfa(String texto){
        StringBuilder vacio = new StringBuilder();

        return vacio.toString();
    }
    
    
    /*
     * 
     */
    public static String desxifraMonoAlfa(String texto){
        StringBuilder vacio = new StringBuilder();

        return vacio.toString();
    }

    
    public static void main(String[] args) {
        char[] alfdes = permutaAlfabet(ALFMIN);
        for (char c : alfdes) {
            System.out.print(c);
        }
        System.out.println();
    }
}
