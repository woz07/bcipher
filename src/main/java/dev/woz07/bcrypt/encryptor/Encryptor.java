package dev.woz07.bcrypt.encryptor;

import dev.woz07.bcrypt.exceptions.CipherKeyException;
import dev.woz07.bcrypt.exceptions.CipherNullException;
import dev.woz07.bcrypt.exceptions.CipherSizeException;

import java.nio.charset.StandardCharsets;

public class Encryptor {
    private byte[] keys = null;
    
    /**
     * Primary constructor
     */
    public Encryptor() {}
    
    /**
     * Secondary constructor
     * @param keys
     */
    public Encryptor(byte[] keys) {
        this.keys = keys;
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
     * @param keys This is the new replacement keys you want
     * @throws CipherSizeException This is thrown when keys length doesn't equal that of base
     * @throws CipherNullException This is thrown when the passed through keys are null
     */
    public void setKeys(byte[] keys) throws CipherSizeException, CipherNullException, CipherKeyException {
        if (keys == null) {
            throw new CipherNullException("Keys cannot be null");
        }
        if (keys.length > 256 || keys.length <= 0) {
            throw new CipherSizeException("Length of keys must be greater than 0 and less than 256");
        }
        for (byte key : keys) {
            if (key < 0) {
                throw new CipherKeyException("A key cannot be smaller than 0");
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
