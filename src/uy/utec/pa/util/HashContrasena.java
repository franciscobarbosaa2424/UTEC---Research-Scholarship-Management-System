package uy.utec.pa.util;

public class HashContrasena {
    //algoritmo DJB2, creado por Daniel J. Bernstein
    public static String generarHash(String contrasena) {
        long hash = 5381;

        for (int i = 0; i < contrasena.length(); i++) {
            hash = hash * 33 + contrasena.charAt(i);
        }
        //Esto convierte el número a hexadecimal
        return Long.toHexString(hash);
    }

}