/*
 * Xifrat ROT13
 */

public class xifraRot13 {
    public static void main(String[] args) {
        char [] alfMay = {'A','B','C','Ç','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z','Á','À','É','È','Ï','í','Ó','Ú','Ü'};
        char [] alfMin = {'a','b','c','ç','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z','á','à','é','è','ï','í','ó','ú','ü',};
        String prueba = "Hola me llamo Christopher âä HOLÁ!!!!!!";
        String vacio = "";
        boolean trobat;
        for (int i = 0; i < prueba.length(); i++){
            trobat = false;
            if(Character.isLetter(prueba.charAt(i))){
                char caracter = prueba.charAt(i);
                if(Character.isLowerCase(caracter)){
                   for (int j = 0; j < alfMin.length; j++) {
                        if(alfMin[j] == caracter){
                            trobat = true;
                            int resultadoMin = (j + 13) % alfMin.length;
                            vacio += alfMin[resultadoMin];
                        }                       
                   }
                } else if(Character.isUpperCase(caracter)){
                    for (int j = 0; j < alfMay.length; j++) {
                        if(alfMay[j] == caracter){
                            trobat = true;
                            int resultadoMay = (j + 13) % alfMay.length;
                            vacio += alfMay[resultadoMay];
                        }                        
                    }
                }
            }  
            if(!trobat){
                vacio += prueba.charAt(i);
            }
        }
        System.out.println(vacio);
    }
}

