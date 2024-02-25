package com.ministudio.encriptacion_seguridad_informatica.Clases;

public class CifradoCesar {

    public static String cifrar(String texto, int clave) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (Character.isLetter(caracter)) {
                char inicio = Character.isUpperCase(caracter) ? 'A' : 'a';
                caracter = (char) ((caracter - inicio + clave) % 26 + inicio);
            }
            resultado.append(caracter);
        }

        return resultado.toString();
    }

    // Método para descifrar un texto cifrado usando el cifrado César
    public static String descifrar(String textoCifrado, int clave) {
        return cifrar(textoCifrado, 26 - clave); // Se descifra con la clave inversa
    }


}
