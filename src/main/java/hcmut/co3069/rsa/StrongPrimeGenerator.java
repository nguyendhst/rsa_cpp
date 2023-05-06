package hcmut.co3069.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class StrongPrimeGenerator {
    private static final int PRIME_CERTAINTY = 100;

    public static BigInteger generate(int bitLength) {
        return generateStrongPrime(bitLength);
    }

    public static BigInteger generateStrongPrime(int bitLength) {
        //SecureRandom random = new SecureRandom();

        // Generate auxiliary primes p1 and p2
        //BigInteger p1 = BigInteger.probablePrime(bitLength / 2, random);
        //BigInteger p2 = BigInteger.probablePrime(bitLength / 2, random);

		BigInteger p1 = Math.randomPrime(bitLength / 2);
		BigInteger p2 = Math.randomPrime(bitLength / 2);

        // Calculate p = 2 * p1 * p2 + 1
        BigInteger p = BigInteger.TWO.multiply(p1).multiply(p2).add(BigInteger.ONE);

        // Find strong prime candidate (p + 2 * i * p1 * p2) that is prime
        BigInteger strongPrimeCandidate;
        int i = 0;
        do {
            strongPrimeCandidate = p.add(BigInteger.valueOf(2 * i).multiply(p1).multiply(p2));
            i++;
        } while (!isProbablyPrime(strongPrimeCandidate, PRIME_CERTAINTY));

        return strongPrimeCandidate;
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

    //private static boolean isStrongPrime(BigInteger prime) {
    //    BigInteger pMinusOne = prime.subtract(BigInteger.ONE);
    //    BigInteger[] factors = pMinusOne.divideAndRemainder(BigInteger.valueOf(2));
    //    if (factors[1].intValue() != 0) {
    //        return false;
    //    }
    //    BigInteger q = factors[0];
    //    int bitLength = prime.bitLength();
    //    int iterations = bitLength > 20 ? bitLength : 20;
    //    for (int i = 0; i < iterations; i++) {
    //        BigInteger a = new BigInteger(bitLength, random);
    //        a = a.mod(pMinusOne).add(BigInteger.ONE);
    //        BigInteger ap = a.modPow(q, prime);
    //        if (ap.equals(BigInteger.ONE) || ap.equals(pMinusOne)) {
    //            continue;
    //        }
    //        boolean found = false;
    //        for (BigInteger j = BigInteger.ZERO; j.compareTo(q) < 0; j = j.add(BigInteger.ONE)) {
    //            ap = ap.modPow(BigInteger.TWO, prime);
    //            if (ap.equals(pMinusOne)) {
    //                found = true;
    //                break;
    //            }
    //        }
    //        if (!found) {
    //            return false;
    //        }
    //    }
    //    return true;
    //}

    // Algorithm Gordon’s algorithm for generating a strong prime
    // SUMMARY: a strong prime p is generated.

    // 1. Generate two large random primes s and t of roughly equal bitlength

    // 2. Select an integer i_0 . Find the first prime in the sequence 2it+1 , for i
    // = i_0, i_0 + 1, . . . . Denote this prime by r = 2it + 1

    // 3. Compute p_0 = 2(s^(r-2) mod r)s - 1

    // 4. Select an integer j_0 . Find the first prime in the sequence p_0 + 2jrs,
    // for j = j_0, j_0 + 1, . . . . Denote this prime by p = p_0 + 2jrs.

    // 5. Return p as the strong prime.

    //public static BigInteger gordonStrongPrime(int bitLen) {

    //    BigInteger s = BigInteger.probablePrime(bitLen, random);
    //    BigInteger t = BigInteger.probablePrime(bitLen, random);
    //    BigInteger r = BigInteger.ZERO;

    //    // Step 2
    //    for (int i = 0; i < bitLen; i++) {
    //        BigInteger temp = BigInteger.valueOf(2).multiply(t).add(BigInteger.ONE);
    //        if (temp.isProbablePrime(100)) {
    //            r = temp;
    //            break;
    //        }
    //        t = t.add(BigInteger.ONE);
    //    }

    //    // Step 3
    //    BigInteger p0 = BigInteger.valueOf(2).multiply(s.modPow(r.subtract(BigInteger.valueOf(2)), r)).multiply(s)
    //            .subtract(BigInteger.ONE);

    //    // Step 4
    //    BigInteger p = BigInteger.ZERO;
    //    for (int j = 0; j < bitLen; j++) {
    //        BigInteger temp = p0.add(BigInteger.valueOf(2).multiply(BigInteger.valueOf(j)).multiply(r).multiply(s));
    //        if (temp.isProbablePrime(100)) {
    //            p = temp;
    //            break;
    //        }
    //    }

    //    return p;
    //}
}