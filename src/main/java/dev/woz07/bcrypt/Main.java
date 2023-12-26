package dev.woz07.bcrypt;

import dev.woz07.bcrypt.encryptor.Encryptor;
import dev.woz07.bcrypt.exceptions.CipherKeyException;
import dev.woz07.bcrypt.exceptions.CipherNullException;
import dev.woz07.bcrypt.exceptions.CipherSizeException;

public class Main {
    public static void main(String[] args) throws CipherNullException, CipherSizeException, CipherKeyException {
        Encryptor encryptor = new Encryptor();
        byte[] keys = {0xA, 0xB, 0xC, 0xD, 0xE, 0xF};
        encryptor.setKeys(keys);
        String original = "Hello, World!";
        String encrypted = encryptor.encrypt(original);
        String decrypted = encryptor.decrypt(encrypted);
    
        System.out.println(original);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}
