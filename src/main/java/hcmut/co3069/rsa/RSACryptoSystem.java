package hcmut.co3069.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;
import hcmut.co3069.rsa.Math;
import hcmut.co3069.rsa.*;

public class RSACryptoSystem {

    private PublicKey publicKey;
    private PrivateKey privateKey;
    private BigInteger bitLength;

    public RSACryptoSystem(int keyBitLength) {
        SecureRandom random = new SecureRandom();
        bitLength = BigInteger.valueOf(keyBitLength);

        // Generate p and q, two distinct strong primes
        BigInteger p = StrongPrimeGenerator.generate(keyBitLength / 2);
        BigInteger q = StrongPrimeGenerator.generate(keyBitLength / 2);

        // Calculate n = p * q
        BigInteger n = p.multiply(q);

        // Calculate phi(n) = (p - 1) * (q - 1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Choose e such that 1 < e < phi(n) and gcd(e, phi(n)) = 1
        BigInteger e;
        do {
            e = new BigInteger(phi.bitLength(), random);
        } while (e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phi) >= 0 || !e.gcd(phi).equals(BigInteger.ONE));

        // Calculate d = e^(-1) mod phi(n)
        BigInteger d = e.modInverse(phi);

        publicKey = new PublicKey(n, e);
        privateKey = new PrivateKey(n, d);
    }

    public BigInteger encrypt(BigInteger message) {
        return Math.modPow(message, publicKey.getPublicExponent(), publicKey.getModulus());
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return Math.modPow(encryptedMessage, privateKey.getPrivateExponent(), privateKey.getModulus());
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public static void main(String[] args) {
        RSACryptoSystem rsa = new RSACryptoSystem(1024);
        String message = "Hello, RSA!";
        BigInteger encrypted = rsa.encrypt(new BigInteger(message.getBytes()));
        BigInteger decrypted = rsa.decrypt(encrypted);
        System.out.println("Original message: " + message);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + new String(decrypted.toByteArray()));
    }
}
