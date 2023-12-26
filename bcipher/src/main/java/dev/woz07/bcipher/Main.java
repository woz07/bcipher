package dev.woz07.bcipher;

import dev.woz07.bcipher.exceptions.CipherKeyException;
import dev.woz07.bcipher.exceptions.CipherNullException;
import dev.woz07.bcipher.exceptions.CipherSizeException;

public class Main {
    public static void main(String[] args) throws CipherNullException, CipherSizeException, CipherKeyException {
        String message = "Hello!";
        Cipher cipher = new Cipher(keys);
        String e = cipher.encrypt(message);
        String d = cipher.decrypt(e);
    
        System.out.println(message);
        System.out.println(e);
        System.out.println(d);
    }
}
