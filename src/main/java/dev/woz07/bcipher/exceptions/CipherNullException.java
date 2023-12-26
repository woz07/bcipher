package dev.woz07.bcipher.exceptions;

/**
 * woz07
 *
 * CipherNullException.java
 * This is the exception for when something is null when it's not supposed to be
 */

public class CipherNullException extends Exception {
    public CipherNullException(String message) {
        super(message);
    }
}
