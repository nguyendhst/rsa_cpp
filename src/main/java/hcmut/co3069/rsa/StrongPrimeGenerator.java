package hcmut.co3069.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class StrongPrimeGenerator {
	private static final int PRIME_CERTAINTY = 100;

	public static BigInteger generate(int bitLength) {
		return generateStrongPrime(bitLength);
	}

	public static BigInteger generateStrongPrime(int bitLength) {	

		SecureRandom random = new SecureRandom();
        BigInteger s = Math.randomStrongPrime(bitLength / 2);
        BigInteger t = Math.randomStrongPrime(bitLength / 2);

        System.out.println("s=" + s);
        System.out.println("t=" + t);

        BigInteger r = BigInteger.ZERO;
        boolean isPrime = false;
        int i = random.nextInt(100);

        while (!isPrime) {
            r = BigInteger.valueOf(2 * i).multiply(t).add(BigInteger.ONE);
            i++;
            isPrime = Math.isProbablePrime(r, 100);
        }

        System.out.println("r=" + r);

        BigInteger p0 = BigInteger.valueOf(2).multiply(Math.modPow(s,r.subtract(BigInteger.valueOf(2)), r)).multiply(s).subtract(BigInteger.ONE);

        System.out.println("\np_0=" + p0);
		System.out.println("p_0 length=" + p0.bitLength() + "\n");

        BigInteger p = BigInteger.ZERO;
        isPrime = false;
        int j = random.nextInt(100);

        while (!isPrime) {
            p = p0.add(BigInteger.valueOf(2).multiply(BigInteger.valueOf(j)).multiply(r).multiply(s));
            j++;
            isPrime = Math.isProbablePrime(p, 100);
        }


		return p;
	}

	public static boolean isProbablyPrime(BigInteger candidate, int certainty) {
		if (candidate.compareTo(BigInteger.TWO) < 0) {
			return false;
		}

		for (int i = 0; i < certainty; i++) {
			BigInteger a;
			do {
				a = new BigInteger(candidate.bitLength(), new SecureRandom());
			} while (a.compareTo(BigInteger.ONE) <= 0 || a.compareTo(candidate) >= 0);

			if (!Math.millerRabinTest(candidate, a)) {
				return false;
			}
		}

		return true;
	}
}