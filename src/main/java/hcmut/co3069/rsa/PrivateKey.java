package hcmut.co3069.rsa;

import hcmut.co3069.rsa.PublicKey;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.security.SecureRandom;

/**
 * Private key of RSA
 *
 */
public class PrivateKey {


    public PublicKey publicKey;
    // prime factors of n
    private ArrayList<BigInteger> primes;
    // private exponent
    private BigInteger d;

    // public GenerateKey(SecureRandom random, int bits) {
    //     BigInteger p = BigInteger.probablePrime(bits, random);
    //     BigInteger q = BigInteger.probablePrime(bits, random);
    //     BigInteger n = p.multiply(q);
    //     BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    //     BigInteger e = BigInteger.probablePrime(bits / 2, random);
    //     while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
    //         e.add(BigInteger.ONE);
    //     }
    //     this.d = e.modInverse(phi);
    //     this.publicKey = new PublicKey(n, e);
    //     this.primes = new ArrayList<BigInteger>();
    //     this.primes.add(p);
    //     this.primes.add(q);
    // }

}
