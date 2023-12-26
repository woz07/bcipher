package dev.woz07.bcipher;

import dev.woz07.bcipher.exceptions.CipherKeyException;
import dev.woz07.bcipher.exceptions.CipherNullException;
import dev.woz07.bcipher.exceptions.CipherSizeException;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * woz07
 *
 * Cipher.java
 * This is the class that handles all of encrypting and decrypting
 * This is the class that must be initialized and used
 */

public class Cipher {
    private byte[] keys = null;
    
    /**
     * Primary constructor
     */
    public Cipher() {}
    
    /**
     * Secondary constructor
     * @param keys The keys
     */
    public Cipher(byte[] keys) throws CipherSizeException, CipherNullException, CipherKeyException {
        setKeys(keys);
    }
    
    /**
     * This is the callable function to encrypt a string
     * @param target The target string to encrypt
     * @return The encrypted result in string
     */
    public String encrypt(String target) throws CipherNullException {
        
        String encrypted = null;
        for (byte key : getKeys()) {
            encrypted = XOR(target, key);
        }
        return encrypted;
    }
    
    /**
     * This is the callable function to decrypt a string
     * @param target The target string to decrypt
     * @return The decrypted result in string
     */
    public String decrypt(String target) throws CipherNullException {
        String decrypted = null;
        for (byte key : getKeys()) {
            decrypted = XOR(target, key);
        }
        return decrypted;
    }
    
    /**
     * Set method for `keys`
     * @param keys This is the keys that you want
     * @throws CipherSizeException This is thrown when keys length doesn't equal that of base
     * @throws CipherNullException This is thrown when the passed through keys are null
     */
    public void setKeys(byte[] keys) throws CipherSizeException, CipherNullException, CipherKeyException {
        if (keys == null) {
            throw new CipherNullException("Keys cannot be null");
        }
        if (Arrays.equals(keys, new byte[]{})) {
            throw new CipherSizeException("Keys cannot be empty");
        }
        if (keys.length >= 256 || keys.length <= 0) {
            throw new CipherSizeException("There can only be 256 keys max to 1 key min");
        }
        for (byte key : keys) {
            if (((Number) key).longValue() <= 0) {
                throw new CipherKeyException("A key cannot be smaller than or equal to 0");
            }
        }
        
        this.keys = keys;
    }
    
    /**
     * Get method for `keys`
     * @return `keys`
     */
    public byte[] getKeys() throws CipherNullException {
        if (keys == null) {
            throw new CipherNullException("Keys cannot be null");
        }
        return keys;
    }
    
    /**
     * Flushes the keys (sets to null) as they cannot really be set null using the `setKeys` method
     */
    public void flush() {
        keys = null;
    }
    
    /**
     * Applies XOR to a target String by using a key
     * @param target The target string to XOR
     * @param key The key to XOR by
     * @return The final result string
     */
    private String XOR(String target, byte key) {
        byte[] bytes    = target.getBytes(StandardCharsets.UTF_8);
        byte[] result   = new byte[target.length()];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = (byte) (bytes[i] ^ key);
        }
        return new String(result, StandardCharsets.UTF_8);
    }
}
