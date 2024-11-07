package iticbcn.xifratge;
/*
 * ROTX
 */

public class XifradorRotX implements Xifrador{

    public static final char [] ALFMIN = "abcçdefghijklmnñopqrstuvwxyzáàéèïíóúü".toCharArray();
    public static final char [] ALFMAY = "ABCÇDEFGHIJKLMNÑOPQRSTUVWXYZÁÀÉÈÏÍÓÚÜ".toCharArray();


    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplazamiento = validaNumClau(clau);
        if (desplazamiento == -1) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        String cifratRotX = xifraRotX(msg, desplazamiento); //obté el xifrat
        return new TextXifrat(cifratRotX.getBytes()); // agafa els bytes del xifrat
    }

    public String desxifra(TextXifrat cifrado, String clau) throws ClauNoSuportada {
        int desplazamiento = validaNumClau(clau);
        if (desplazamiento == -1) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        String textXifrat = new String(cifrado.getBytes()); // agafa els bytes del xifrat
        return desxifraRotX(textXifrat, desplazamiento);
    }

    public int validaNumClau(String clau) {
        try {
            int desplazamiento = Integer.parseInt(clau);
            if (desplazamiento >=0 && desplazamiento <=40) return desplazamiento;
        } catch (NumberFormatException e) {
            return -1; // si la conversión es erronea, retorna -1
        }
        return -1; // si no está en el rango establecido retorna -1
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
}
