/*
 * Xifrat ROT13
 */

public class xifraRot13 {
    public static void main(String[] args) {
        char [] alfMay = {'A','B','C','Ç','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z','Á','À','É','È','Ï','Ó','Ú','Ü'};
        char [] alfMin = {'a','b','c','ç','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z','á','à','é','è','ï','ó','ú','ü',};
        String prueba = "Hola, me llamo Christopher y soy de Perú.";
        String vacio = "";
        int numero = 0;
        for (int i = 0; i < prueba.length(); i++){
            if(Character.isLetter(prueba.charAt(i))){
                char caracter = prueba.charAt(i);
                if(Character.isLowerCase(prueba.charAt(i))){
                   for (int j = 0; j < alfMin.length; j++) {
                        if(alfMin[j] == caracter){
                            numero = j;
                        }                        
                   }
                   int resultadoMin = (numero + 13) % alfMin.length;
                   vacio += alfMin[resultadoMin];
                } else if(Character.isUpperCase(prueba.charAt(i))){
                    for (int j = 0; j < alfMay.length; j++) {
                         if(alfMay[j] == caracter){
                             numero = j;
                         }                        
                    }
                    int resultadoMay = (numero + 13) % alfMay.length;
                    vacio += alfMay[resultadoMay];
                 }

                
            } else {
                vacio += prueba.charAt(i);
            }
        }
        System.out.println(vacio);
    }
}

