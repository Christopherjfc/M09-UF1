/*
 * Cifrat ROT13
 */

public class Rot13 {
    public static void main(String[] args) {
        char [] alfMin = {'a','b','c','ç','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z','á','à','é','è','ï','ó','ü'};
        char [] alfMay = {'A','B','C','Ç','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z','Á','À','É','È','Ï','Ó','Ü'};
        String prueba ="stu"; //l   efg
        String vacio = "";

        // char primeraLetra = alfMin[0];
        // char ultimaLetra = alfMin[alfMin.length-1];
        
        for (int i = 0; i <= prueba.length(); i++){
            if(Character.isLetter(prueba.charAt(i))){
                if(Character.isLowerCase(prueba.charAt(i))){
                   for (int j = 0; j < alfMin.length; j++) {
                       //crearé 2 funciones para las mayúsculas y minúsculas, que se llamen una entre si, si se enuentran varias minúsculas y minúsculas en el String de prueba.
                   }
                }
            }
        }
    }
}