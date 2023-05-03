package hcmut.co3069.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;

public class PrivateKey implements RSAPrivateKey {

    private BigInteger modulus;
    private BigInteger privateExponent;

    public PrivateKey(BigInteger modulus, BigInteger privateExponent) {
        this.modulus = modulus;
        this.privateExponent = privateExponent;
    }

    // public PrivateKey(BigInteger p, BigInteger q) {
    // BigInteger n = p.multiply(q);
    // SecureRandom random = new SecureRandom();
    // BigInteger phi =
    // p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    // int te = random.nextInt(phi.intValue());
    // BigInteger e = new BigInteger(n.bitLength() / 2, random);
    // while (!Math.gcd(phi, e).equals(BigInteger.ONE) && e.compareTo(phi) < 0) {
    // e = e.add(BigInteger.ONE);
    // }
    // // this.d = Math.modInverse(e,phi);
    // // this.publicKey = new PublicKey(n, e);
    // this.primes = new ArrayList<BigInteger>();
    // this.primes.add(p);
    // this.primes.add(q);
    // }

    @Override
    public BigInteger getModulus() {
        return modulus;
    }

    @Override
    public BigInteger getPrivateExponent() {
        return privateExponent;
    }

    @Override
    public String getAlgorithm() {
        return "RSA";
    }

    @Override
    public String getFormat() {
        return "PKCS#8";
    }

    @Override
    public byte[] getEncoded() {
        // TODO
        return null;
    }

    //// prime factors of n
    // private ArrayList<BigInteger> primes;
    //// private exponent
    // public BigInteger d;
    // public BigInteger n;

    // public void GenerateKey(SecureRandom random, int bits) {
    // BigInteger p = BigInteger.probablePrime(bits, random);
    // BigInteger q = BigInteger.probablePrime(bits, random);
    // PrivateKey a = new PrivateKey(p, q);
    // this.primes = a.primes;
    // this.publicKey = a.publicKey;
    // this.d = a.d;
    // }

    // public BigInteger encrypt() {
    // return BigInteger.ONE;
    // }

    // public BigInteger decrypt() {
    // return BigInteger.ONE;
    // }

    //// public BigInteger chuky(){
    ////
    //// }
    // public PublicKey getPublicKey() {
    // return publicKey;
    // }
}
