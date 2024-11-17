package com.wowlibre.model;

import java.math.*;

/**
 * Clase que representa los parámetros utilizados para el cifrado.
 */
public class ParamsEncrypt {
    public final int N_length_bits;       // Longitud de N en bits
    public final BigInteger N;             // Número primo grande
    public final BigInteger g;             // Generador
    public final String hash;              // Función hash
    public final int identityMaxLength;    // Longitud máxima de identidad
    public final int passwordMaxLength;    // Longitud máxima de contraseña

    /**
     * Constructor para inicializar los parámetros de cifrado.
     *
     * @param N_length_bits longitud de N en bits
     * @param N número primo grande
     * @param g generador
     * @param hash función hash
     * @param identityMaxLength longitud máxima de identidad
     * @param passwordMaxLength longitud máxima de contraseña
     */
    public ParamsEncrypt(int N_length_bits, BigInteger N, BigInteger g, String hash, int identityMaxLength,
                         int passwordMaxLength) {
        this.N_length_bits = N_length_bits;
        this.N = N;
        this.g = g;
        this.hash = hash;
        this.identityMaxLength = identityMaxLength;
        this.passwordMaxLength = passwordMaxLength;
    }

    // Instancia estática de parámetros para TrinityCore
    public static final ParamsEncrypt trinitycore = new ParamsEncrypt(
            256,
            new BigInteger("894B645E89E1535BBDAD5B8B290650530801B18EBFBF5E8FAB3C82872A3E9BB7", 16),
            BigInteger.valueOf(7),
            "SHA-1",
            16,
            16
    );
}
