package com.ministudio.encriptacion_seguridad_informatica.Clases;
import java.security.MessageDigest;


public class sha256 {

    public static byte[] encryptSHA256(byte[] data) throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        sha256.update(data);
        return sha256.digest();
    }
}
