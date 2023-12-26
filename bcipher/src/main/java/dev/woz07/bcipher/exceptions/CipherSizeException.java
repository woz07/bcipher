package dev.woz07.bcipher.exceptions;

/**
 * woz07
 *
 * CipherSizeException.java
 * This is the exception for something that isn't the right size
 */

public class CipherSizeException extends Exception {
    public CipherSizeException(String message) {
        super(message);
    }
}
