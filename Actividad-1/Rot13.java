/*
 * ROT13
 */

public class Rot13 {

    public static int numCaracter(char[] alfabeto, char caracter) {
        int numero = -1; 
        for (int i = 0; i < alfabeto.length; i++) {
            if(alfabeto[i] == caracter) {
                numero = i; // Posicion donde se encuentra el carácter  en el char[]
            }
        }
        return numero; // Retornarà -1 si no lo encuentra y si lo encuentra retornará la posición de la i.
    } 

    public static boolean esTroba(char[] alfabeto,char caracter){ 
        if (numCaracter(alfabeto, caracter) != -1|| numCaracter(alfabeto, caracter) != -1) {
            return true;
        }
        return false; // retornará false si no encuentra el carácter en el char[]
    }


    public static String xifraRot13(String prueba) {
        char [] alfMin = "abcçdefghijklmnñopqrstuvwxyzáàéèïíóúü".toCharArray();
        char [] alfMay = "ABCÇDEFGHIJKLMNÑOPQRSTUVWXYZÁÀÉÈÏíÓÚÜ".toCharArray();
        StringBuilder vacio = new StringBuilder();
        for (int i = 0; i < prueba.length(); i++){
            char caracter = prueba.charAt(i);
            if(esTroba(alfMin, caracter)|| esTroba(alfMay, caracter)){ // true si lo encuentra
                if(Character.isLowerCase(caracter)){ // letras minúsculas
                    int resultadoMin = (numCaracter(alfMin, caracter) + 13) % alfMin.length;
                    vacio.append(alfMin[resultadoMin]);                 
                } else if(Character.isUpperCase(caracter)){ // LETRAS MAYÚSCULAS
                    int resultadoMay = (numCaracter(alfMay, caracter) + 13) % alfMay.length;
                    vacio.append(alfMay[resultadoMay]);
                }
            } else {
                vacio.append(caracter);
            }
        }
    return vacio.toString();
    }
    

    public static String desxifraRot13(String prueba) {
        char [] alfMin = "abcçdefghijklmnñopqrstuvwxyzáàéèïíóúü".toCharArray();
        char [] alfMay = "ABCÇDEFGHIJKLMNÑOPQRSTUVWXYZÁÀÉÈÏíÓÚÜ".toCharArray();
        StringBuilder vacio = new StringBuilder();
        for (int i = 0; i < prueba.length(); i++){
            char caracter = prueba.charAt(i);
            if(esTroba(alfMin, caracter)|| esTroba(alfMay, caracter)){ // true si lo encuentra
                if(Character.isLowerCase(caracter)){ // letras pequeñas
                    int resultadoMin = (numCaracter(alfMin, caracter) - 13) % alfMin.length;
                    if(resultadoMin < 0) {
                        vacio.append(alfMin[alfMin.length + resultadoMin]);
                    } else {
                        vacio.append(alfMin[resultadoMin]);
                    }                        
                } else if(Character.isUpperCase(caracter)){ // LETRAS MAYÚSCULAS
                    int resultadoMay = (numCaracter(alfMay, caracter) - 13) % alfMay.length;
                    if(resultadoMay < 0) {
                        vacio.append(alfMay[alfMin.length + resultadoMay]);
                    } else {
                        vacio.append(alfMay[resultadoMay]);
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

