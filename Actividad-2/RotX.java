/*
 * ROTX
 */

public class RotX {

    public static final char [] ALFMIN = "abcçdefghijklmnñopqrstuvwxyzáàéèïíóúü".toCharArray();
    public static final char [] ALFMAY = "ABCÇDEFGHIJKLMNÑOPQRSTUVWXYZÁÀÉÈÏÍÓÚÜ".toCharArray();

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


    public static String xifraRotX(String prueba, int desplazamiento) {
        StringBuilder vacio = new StringBuilder();
        for (int i = 0; i < prueba.length(); i++){
            char caracter = prueba.charAt(i);
            if(esTroba(ALFMIN, caracter)|| esTroba(ALFMAY, caracter)){ // true si lo encuentra
                if(Character.isLowerCase(caracter)){ // letras minúsculas
                    int resultadoMin = (numCaracter(ALFMIN, caracter) + desplazamiento) % ALFMIN.length;
                    vacio.append(ALFMIN[resultadoMin]);                 
                } else if(Character.isUpperCase(caracter)){ // LETRAS MAYÚSCULAS
                    int resultadoMay = (numCaracter(ALFMAY, caracter) + desplazamiento) % ALFMAY.length;
                    vacio.append(ALFMAY[resultadoMay]);
                }
            } else {
                vacio.append(caracter);
            }
        }
    return vacio.toString();
    }
    

    public static String desxifraRotX(String prueba, int desplazamiento) {
        StringBuilder vacio = new StringBuilder();
        for (int i = 0; i < prueba.length(); i++){
            char caracter = prueba.charAt(i);
            if(esTroba(ALFMIN, caracter)|| esTroba(ALFMAY, caracter)){ // true si lo encuentra
                if(Character.isLowerCase(caracter)){ // letras pequeñas
                    int resultadoMin = (numCaracter(ALFMIN, caracter) - desplazamiento) % ALFMIN.length;
                    if(resultadoMin < 0) {
                        vacio.append(ALFMIN[ALFMIN.length + resultadoMin]);
                    } else {
                        vacio.append(ALFMIN[resultadoMin]);
                    }                        
                } else if(Character.isUpperCase(caracter)){ // LETRAS MAYÚSCULAS
                    int resultadoMay = (numCaracter(ALFMAY, caracter) - desplazamiento) % ALFMAY.length;
                    if(resultadoMay < 0) {
                        vacio.append(ALFMAY[ALFMAY.length + resultadoMay]);
                    } else {
                        vacio.append(ALFMAY[resultadoMay]);
                    }
                }
            }else {
                vacio.append(caracter);
            }  
        }
        return vacio.toString();
    }

    public static void forcaBrutaRotX(String cifrado){
        for (int i = 0; i < ALFMAY.length; i++) {
            System.out.printf("%s  ->  %d%n",desxifraRotX(cifrado, i), i);
        }
    }


    public static void main(String[] args) {
        String[] pruebas = {"Hola, soy Christopher Flores!", "a"};
        System.out.printf("      %-35s    %-35s    %-35s%n", "TEXTO ORIGINAL", "TEXTO CIFRADO", "TEXTO DESCIFRADO");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for (String prueba : pruebas) {
            String cifrado = xifraRotX(prueba, 254);
            String descifrado = desxifraRotX(cifrado, 254);
            System.out.printf("%-35s => %-35s => %-35s%n", prueba, cifrado, descifrado);
            System.out.println();
            forcaBrutaRotX(cifrado);
            System.out.println();
        }
    }
}

