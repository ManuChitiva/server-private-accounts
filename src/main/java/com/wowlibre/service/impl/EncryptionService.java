package com.wowlibre.service.impl;


import com.wowlibre.model.*;

import java.math.*;
import java.nio.charset.*;
import java.security.*;

import static com.wowlibre.model.ParamsEncrypt.trinitycore;


public class EncryptionService {

    /**
     * Calcula el verificador.
     *
     * @param params   parámetros del grupo.
     * @param salt     sal.
     * @param username identidad del usuario.
     * @param password contraseña del usuario.
     * @return verificador calculado como un array de bytes.
     * @throws NoSuchAlgorithmException si el algoritmo de hash no está disponible.
     */
    public static byte[] computeVerifier(ParamsEncrypt params, byte[] salt, String username, String password) throws Exception {
        // Verificar la longitud de identidad y contraseña
        if (username.length() > params.identityMaxLength) {
            throw new IllegalArgumentException("La identidad debe tener un máximo de " + params.identityMaxLength +
                    " caracteres");
        }
        if (password.length() > params.passwordMaxLength) {
            throw new IllegalArgumentException("La contraseña debe tener un máximo de " + params.passwordMaxLength +
                    " caracteres");
        }

        // Calcular x
        BigInteger x = getX(params, salt, username, password);
        System.out.println("x (Java): " + x.toString(16));
        if (x.toString(16).equals("e1c467962f66e1cffd736b08643edade4a85c880")) {
            System.out.println(" ");
        }
        // Calcular el verificador
        BigInteger g = params.g;
        BigInteger N = params.N;

        System.out.println("N en Decimal: " + trinitycore.N);

        System.out.println("N (Java): " + N);
        System.out.println("g (Java): " + g);

        BigInteger verifier = modPow(g, x, N);
        System.out.println("modPow (Java): " + verifier);

        // Convertir a little endian
        byte[] verifierBytes = verifier.toByteArray();
        byte[] littleEndianVerifierBytes = new byte[verifierBytes.length];

        for (int i = 0; i < verifierBytes.length; i++) {
            littleEndianVerifierBytes[i] = verifierBytes[verifierBytes.length - 1 - i];
        }

        // Imprimir el verificador en hexadecimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : littleEndianVerifierBytes) {
            hexString.append(String.format("%02x", b & 0xFF)); // Asegurarte de que el byte sea positivo
        }

        System.out.println("Verifier (Java in Hex): " + hexString.toString());
        return littleEndianVerifierBytes;
    }

    /**
     * Calcula el valor intermedio x.
     *
     * @param params   parámetros del grupo.
     * @param salt     sal.
     * @param identity identidad del usuario.
     * @param password contraseña del usuario.
     * @return el valor intermedio X como BigInteger.
     * @throws NoSuchAlgorithmException si el algoritmo de hash no está disponible.
     */
    private static BigInteger getX(ParamsEncrypt params, byte[] salt, String identity, String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(params.hash);

        // Hash(identity:password)
        byte[] identityPasswordHash =
                digest.digest((identity.toUpperCase() + ":" + password.toUpperCase()).getBytes(StandardCharsets.UTF_8));

        // Hash(salt | hash(identity:password))
        digest.reset();
        digest.update(salt);
        byte[] hashX = digest.digest(identityPasswordHash);

        // Invertir el orden de los bytes para little-endian
        byte[] littleEndianHashX = new byte[hashX.length];
        for (int i = 0; i < hashX.length; i++) {
            littleEndianHashX[i] = hashX[hashX.length - 1 - i];
        }

        return new BigInteger(1, littleEndianHashX); // Devuelve un BigInteger positivo
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Calcula la potencia modular.
     *
     * @param base     base como BigInteger.
     * @param exponent exponente como BigInteger.
     * @param modulus  módulo como BigInteger.
     * @return resultado de (base ** exponent) % modulus como BigInteger.
     */
    private static BigInteger modPow(BigInteger base, BigInteger exponent, BigInteger modulus) {
        return base.modPow(exponent, modulus);
    }
}
