# Bcipher

> bcipher uses java 11

 
> bcipher is also NOT 100% secure, so please do not use this for applications that require high level of security
> as this can be broken, it is just meant to be a simple basic encryptor/ decryptor and doesn't offer a lot of security.

## What is it
Bcipher is a cipher library for Java applications, you can use it encrypt and decrypt data (strings) for you.
It utilizies a XOR based system to encrypt and decrypt your data, this is done through a key. The key is an 
array of `bytes` where each element of the key is what encrypts your data, so your data is encrypted multiple times.

> T`he length of the byte array must be less than or equal to 256 while also being greater than 0.
> Each element within the array must also be greater than or equal to 0, no negatives allowed.

## How to use it
### Local Maven
1. Git clone `https://www.github.com/woz07/bcipher` into your local machine.
2. Run the maven command `clean install`, which will install the jar to your local maven repository.
3. In your `pom.xml` add the dependency:
   ```xml
        <dependency>
            <groupId>dev.woz07.lwlfj</groupId>
            <artifactId>bcipher</artifactId>
            <version>1.0</version>
        </dependency>
   ```

---

### Default Java
1. Create a `lib` folder within your project.
2. Install the release of your choice.
3. Drag and drop the `.jar` file into your `lib` folder.
4. Configure your project to use the `.jar` file.

## Documentation
To get started you need to create an object of `Cipher.java`
```java
import dev.woz07.bcipher.Cipher;

public class Main {
    public static void main(String[] args) {
        Cipher cipher = new Cipher();
    }
}
```
There are 2 constructors, a default one and one that requires a key. If you do not have a key preset then 
you can use the one that doesn't require a key to be passed through, but do remember a key is `REQUIRED` in 
order for the cipher to do it's encryption and decryption.
Here is what it would look like if you preset the key
```java
import dev.woz07.bcipher.Cipher;
import dev.woz07.bcipher.exceptions.CipherKeyException;
import dev.woz07.bcipher.exceptions.CipherNullException;
import dev.woz07.bcipher.exceptions.CipherSizeException;

public class Main {
    public static void main(String[] args) throws CipherSizeException, CipherNullException, CipherKeyException {
        byte[] keys = {0xA, 0xB, 0xC, 0xD};
        Cipher cipher = new Cipher(keys);
    }
}
```
It says it throws a lot of exceptions but you can simply shorten that with a try/catch statement or 
by just replacing the CipherExceptions with `Exception` as they all are subclasses of `Exception`

To begin encrypting your data you can utilize the `encrypt(String target)` method, which expects you to 
pass through a String which is what will get encrypted, the method returns a String which is the encrypted 
String.
```java
import dev.woz07.bcipher.Cipher;
import dev.woz07.bcipher.exceptions.CipherKeyException;
import dev.woz07.bcipher.exceptions.CipherNullException;
import dev.woz07.bcipher.exceptions.CipherSizeException;

public class Main {
    public static void main(String[] args) throws CipherSizeException, CipherNullException, CipherKeyException {
        byte[] keys = {0xA, 0xB, 0xC, 0xD};
        Cipher cipher = new Cipher(keys);
        String original = "Hello, World!";
        String encrypted = cipher.encrypt(original);
    }
}
```

To begin decrypting something, we can utilize the `decrypt(String target` method, which expects you to pass 
through a String which is what will get decrypted (it expects you to pass through the encrypted String), 
this method also returns a String which is the decrypted String.
```java
import dev.woz07.bcipher.Cipher;
import dev.woz07.bcipher.exceptions.CipherKeyException;
import dev.woz07.bcipher.exceptions.CipherNullException;
import dev.woz07.bcipher.exceptions.CipherSizeException;

public class Main {
    public static void main(String[] args) throws CipherSizeException, CipherNullException, CipherKeyException {
        byte[] keys = {0xA, 0xB, 0xC, 0xD};
        Cipher cipher = new Cipher(keys);
        String original = "Hello, World!";
        String encrypted = cipher.encrypt(original);
        String decrypted = cipher.decrypt(encrypted);
    }
}
```

> Note: As we kept the `keys` the same we got the same result, but if we changed the `keys` then that would
> cause an issue as we won't get the correct decryption

You can easily set the keys by using the method `setKeys(byte[] keys)` this method will set the keys for you.

```java
import dev.woz07.bcipher.Cipher;
import dev.woz07.bcipher.exceptions.CipherKeyException;
import dev.woz07.bcipher.exceptions.CipherNullException;
import dev.woz07.bcipher.exceptions.CipherSizeException;

public class Main {
    public static void main(String[] args) throws CipherSizeException, CipherNullException, CipherKeyException {
        Cipher cipher = new Cipher();
        cipher.setKeys(new byte[]{0xD, 0xC, 0xB, 0xA});
    }
}
```
Here we have updated keys from null to the byte array provided in the `setKeys()` method.

You can also use the `flush()` method to set keys to null if you do not want to use them anymore.
You aren't able to set `keys` to null via `setKeys()` hence why the `flush()` method exists
```java
import dev.woz07.bcipher.Cipher;
import dev.woz07.bcipher.exceptions.CipherKeyException;
import dev.woz07.bcipher.exceptions.CipherNullException;
import dev.woz07.bcipher.exceptions.CipherSizeException;

public class Main {
    public static void main(String[] args) throws CipherSizeException, CipherNullException, CipherKeyException {
        Cipher cipher = new Cipher();
        cipher.setKeys(new byte[]{0xD, 0xC, 0xB, 0xA});
        cipher.flush();
    }
}
```
