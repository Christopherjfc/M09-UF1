/*
 * desXifratROT13
 */

 public class desxifraRot13 {
    public static void main(String[] args) {
        char [] alfMin = {'a','b','c','ç','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z','á','à','é','è','ï','ó','ú','ü',};
        char [] alfMay = {'A','B','C','Ç','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z','Á','À','É','È','Ï','Ó','Ú','Ü'};
        String prueba = "Tàxm, yq xxmyà Ñtïuóúàétqï ç óàç pq Éqïk."; 
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
                    int resultadoMin = (numero - 13) % alfMin.length;
                    if(resultadoMin < 0) {
                        vacio += alfMin[alfMin.length + resultadoMin];
                    } else {
                        vacio += alfMin[resultadoMin];
                    }
                } else if(Character.isUpperCase(prueba.charAt(i))){
                    for (int j = 0; j < alfMay.length; j++) {
                         if(alfMay[j] == caracter){
                             numero = j;
                         }                        
                    }
                    int resultadoMay = (numero - 13) % alfMay.length;
                    if(resultadoMay < 0) {
                        vacio += alfMay[alfMin.length + resultadoMay];
                    } else {
                        vacio += alfMay[resultadoMay];
                    }
                 }
            } else {
                vacio += prueba.charAt(i);
            }
        }
        System.out.println(vacio);
    }
}
