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


    /*
     * Generar una LISTA de CHARACTER pasarle el metodo shuffle y retornar 
     * un char[]
     */
    public static char[] permutaAlfabet(char[] alfabeto){
        List<Character> lista = new ArrayList<>();
        for(char caracter: alfabeto){
            lista.add(caracter);
        }
        Collections.shuffle(lista);
        char[] alfrandom = new char[alfabeto.length];
        for(int i = 0; i < lista.size(); i++){
            alfrandom[i] = lista.get(i);
        }
        return alfrandom;
    }


    /*
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
    }
}
