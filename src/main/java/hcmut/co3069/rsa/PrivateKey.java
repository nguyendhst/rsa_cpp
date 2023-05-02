package hcmut.co3069.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.security.SecureRandom;

/**
 * Private key of RSA
 *
 */
public class PrivateKey {


    private PublicKey publicKey;
    // prime factors of n
    private ArrayList<BigInteger> primes;
    // private exponent
    public BigInteger d;
	public BigInteger n;
     public void GenerateKey(SecureRandom random, int bits) {
         BigInteger p = BigInteger.probablePrime(bits, random);
         BigInteger q = BigInteger.probablePrime(bits, random);
         PrivateKey a = new PrivateKey(p,q);
         this.primes = a.primes;
         this.publicKey = a.publicKey;
         this.d = a.d;
     }
    public PrivateKey(BigInteger p,BigInteger q){
        BigInteger n = p.multiply(q);
        SecureRandom random= new SecureRandom();
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        int te = random.nextInt(phi.intValue());
        BigInteger e = new BigInteger(n.bitLength()/2,random);
        while (!Math.gcd(phi,e).equals(BigInteger.ONE) && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
//        this.d = Math.modInverse(e,phi);
//        this.publicKey = new PublicKey(n, e);
        this.primes = new ArrayList<BigInteger>();
        this.primes.add(p);
        this.primes.add(q);
    }
    public BigInteger encrypt(){
       return BigInteger.ONE;
    }
    public BigInteger decrypt(){
         return BigInteger.ONE;
    }
//    public BigInteger chuky(){
//
//    }
    public PublicKey getPublicKey() {
        return publicKey;
    }
}
