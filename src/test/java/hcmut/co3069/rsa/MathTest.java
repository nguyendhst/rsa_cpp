package hcmut.co3069.rsa;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.SecureRandom;
import static org.junit.jupiter.api.Assertions.*;

class MathTest {

    @Test
    void random() {
        SecureRandom random = new SecureRandom();
        Math math = new Math(random);
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
    }

    @Test
    void testRandom() {
    }

    @Test
    void gcd() {
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
            assertTrue(p.isProbablePrime(1));
        }
    }

	@Test
	void gordonStrongPrime2() {
		int bitlen = 2048;
		SecureRandom rnd = new SecureRandom();
		BigInteger test = BigInteger.probablePrime(2048, rnd);
		test.add(test);
		for (int index = 0; index < 1; index++) {
			BigInteger p = Math.gordonStrongPrime(2048);
			assertTrue(p.isProbablePrime(10));
		}
	}
}