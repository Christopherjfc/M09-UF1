/*
 * ROT13
 */

public class Rot13 {

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


    public static String xifraRot13(String prueba) {
        StringBuilder vacio = new StringBuilder();
        for (int i = 0; i < prueba.length(); i++){
            char caracter = prueba.charAt(i);
            if(esTroba(ALFMIN, caracter)|| esTroba(ALFMAY, caracter)){ // true si lo encuentra
                if(Character.isLowerCase(caracter)){ // letras minúsculas
                    int resultadoMin = (numCaracter(ALFMIN, caracter) + 13) % ALFMIN.length;
                    vacio.append(ALFMIN[resultadoMin]);                 
                } else if(Character.isUpperCase(caracter)){ // LETRAS MAYÚSCULAS
                    int resultadoMay = (numCaracter(ALFMAY, caracter) + 13) % ALFMAY.length;
                    vacio.append(ALFMAY[resultadoMay]);
                }
            } else {
                vacio.append(caracter);
            }
        }
    return vacio.toString();
    }
    

    public static String desxifraRot13(String prueba) {
        StringBuilder vacio = new StringBuilder();
        for (int i = 0; i < prueba.length(); i++){
            char caracter = prueba.charAt(i);
            if(esTroba(ALFMIN, caracter)|| esTroba(ALFMAY, caracter)){ // true si lo encuentra
                if(Character.isLowerCase(caracter)){ // letras pequeñas
                    int resultadoMin = (numCaracter(ALFMIN, caracter) - 13) % ALFMIN.length;
                    if(resultadoMin < 0) {
                        vacio.append(ALFMIN[ALFMIN.length + resultadoMin]);
                    } else {
                        vacio.append(ALFMIN[resultadoMin]);
                    }                        
                } else if(Character.isUpperCase(caracter)){ // LETRAS MAYÚSCULAS
                    int resultadoMay = (numCaracter(ALFMAY, caracter) - 13) % ALFMAY.length;
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


    public static void main(String[] args) {
        String[] pruebas = {"HoLa coMo eTáS!!!!", "AZSCÈÂ23", "Soy Christopher, tengo 20 años!",
                            "pingüino", "una excelente prueba", "Quentin Tarantino"};
        System.out.printf("      %-35s    %-35s    %-35s%n", "TEXTO ORIGINAL", "TEXTO CIFRADO", "TEXTO DESCIFRADO");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        for (String prueba : pruebas) {
            String cifrado = xifraRot13(prueba);
            String descifrado = desxifraRot13(cifrado);
            System.out.printf("%-35s => %-35s => %-35s%n", prueba, cifrado, descifrado);
        }
    }
}

