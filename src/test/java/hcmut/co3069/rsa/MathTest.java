package hcmut.co3069.rsa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.SecureRandom;
import static org.junit.jupiter.api.Assertions.*;

class MathTest {

    @Test
    void random() {
        SecureRandom random = new SecureRandom();
		int bitLength = 2048;
        BigInteger min = BigInteger.valueOf(2).pow(bitLength - 1);
		BigInteger max = BigInteger.valueOf(2).pow(bitLength).subtract(BigInteger.ONE);
		// check if random number is in range [min, max]
		for (int i = 0; i < 10000; i++) {
			BigInteger randomNum = Math.randomBigInteger(bitLength);
			assertTrue(randomNum.bitLength() == bitLength);
			assertTrue(randomNum.compareTo(min) >= 0);
			assertTrue(randomNum.compareTo(max) <= 0);
		}
    }

    @Test
    void modPow() {
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 1000; i++) {
            BigInteger r1 = new BigInteger(random.nextInt(1000),random).add(BigInteger.ONE);
            BigInteger r2 = new BigInteger(random.nextInt(1000),random).add(BigInteger.ONE);
            BigInteger m = new BigInteger(random.nextInt(2000),random).add(BigInteger.ONE);
            BigInteger re1 = r1.modPow(r2,m);
            BigInteger re2 = Math.modPow(r1,r2,m);
            assertEquals(re1,re2);
        }
    }
    @Test
    void modPowInt() {
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 1000; i++) {
            BigInteger r1 = new BigInteger(random.nextInt(1000),random).add(BigInteger.ONE);
            int r2 = random.nextInt(1000000) + 1 ;
            BigInteger m = new BigInteger(random.nextInt(2000),random).add(BigInteger.ONE);
            BigInteger re1 = r1.modPow(BigInteger.valueOf(r2),m);
            BigInteger re2 = Math.modPow(r1,r2,m);
            assertEquals(re1,re2);
        }
    }

    @Test
    void testRandom() {
        SecureRandom rng = new SecureRandom();
        for (int i = 0; i < 1000; i++) {
            assertTrue(rng.nextFloat()<1.0);
        }
    }

    @Test
    void gcd() {
        SecureRandom random =new SecureRandom();
        for (int i = 0; i < 1000; i++) {
            BigInteger r1 = new BigInteger(random.nextInt(200),random);
            BigInteger r2 = new BigInteger(random.nextInt(200),random);
            assertEquals(r1.gcd(r2),Math.gcd(r1,r2));
        }
    }

    @Test
    void gcdExtended() {
    }

    @Test
    void probableStrongPrime() {
    }
	
    @Test
    void randomPrime(){
       for (int index = 0; index < 1; index++) {
           BigInteger a = Math.randomPrime(1024);
        //   BigInteger b = BigInteger.probablePrime(2048, new SecureRandom());
           assertTrue(a.isProbablePrime(1));
		//   assertTrue(b.isProbablePrime(10));
       }
    }

    @Test
    void millerRabin() {
        SecureRandom rnd = new SecureRandom();
        for (int index = 0; index < 10; index++) {
            BigInteger a = BigInteger.probablePrime(1024, rnd);
            Boolean b = Math.millerRabinTest(a,10);
            Boolean b1 = Math.millerRabinTest(a.subtract(BigInteger.TWO), 10);
            Boolean b2 = a.subtract(BigInteger.TWO).isProbablePrime(10);
            assertTrue(b);
            assertTrue(b1 == b2);
			
           
        }
    }

    @Test
    void gordonStrongPrime1() {
        //SecureRandom rnd = new SecureRandom();
        int bitlen = 2048;
        for (int index = 0; index < 1; index++) {
            BigInteger p = Math.randomStrongPrime(bitlen);
            assertTrue(p.isProbablePrime(10));
        }
    }
//
//	@Test
//	void gordonStrongPrime2() {
//		int bitlen = 2048;
//		SecureRandom rnd = new SecureRandom();
//		for (int index = 0; index < 1; index++) {
//			BigInteger p = Math.gordonStrongPrime(2048);
//			assertTrue(p.isProbablePrime(10));
//		}
//	}
    @Test
    public void testModInverse(){
        SecureRandom rnd = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            BigInteger a = BigInteger.probablePrime(300,rnd);
            BigInteger c = BigInteger.probablePrime(200,rnd);
            BigInteger m1 = Math.modInverse(c,a);
            BigInteger m2 = c.modInverse(a);
            assertEquals(m1, m2);
        }
//        System.out.println(a.modInverse(c).compareTo(Math.modInverse(c,a)));
    }
}