package com.ministudio.encriptacion_seguridad_informatica.Clases;

public class sustitucion_simple {

    private static final String ORIGINAL_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SUBSTITUTION_ALPHABET = "QWERTYUIOPASDFGHJKLZXCVBNM";


    public static String encrypt(String message) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            char encryptedChar = c;
            int index = ORIGINAL_ALPHABET.indexOf(Character.toUpperCase(c));
            if (index != -1) {
                if (Character.isLowerCase(c)) {
                    encryptedChar = Character.toLowerCase(SUBSTITUTION_ALPHABET.charAt(index));
                } else {
                    encryptedChar = SUBSTITUTION_ALPHABET.charAt(index);
                }
            }
            encryptedMessage.append(encryptedChar);
        }
        return encryptedMessage.toString();
    }


    // Método para desencriptar un mensaje
    public static String decrypt(String encryptedMessage) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (char c : encryptedMessage.toCharArray()) {
            char decryptedChar = c;
            int index = SUBSTITUTION_ALPHABET.indexOf(Character.toUpperCase(c));
            if (index != -1) {
                if (Character.isLowerCase(c)) {
                    decryptedChar = Character.toLowerCase(ORIGINAL_ALPHABET.charAt(index));
                } else {
                    decryptedChar = ORIGINAL_ALPHABET.charAt(index);
                }
            }
            decryptedMessage.append(decryptedChar);
        }
        return decryptedMessage.toString();
    }


    /**
     *
     * Alfabeto Original:     A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
     * Alfabeto de Reemplazo: Q W E R T Y U I O P A S D F G H J K L Z X C V B N M
     *
     * Entonces, para encriptar el mensaje "HELLO", vamos a sustituir cada letra según el alfabeto de reemplazo:
     *
     * H -> I
     * E -> P
     * L -> S
     * L -> S
     * O -> A
     *
     *
     *
     */
}
