package hcmut.co3069.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Math {

	private SecureRandom rng;
	public Math (SecureRandom random) {
		this.rng = random;
	}


	// Generate a random number of the specified bit length in the range 2^(bits-1) and 2^bits-1
	public BigInteger random(int bits) {
		if (bits < 2) {
			throw new ArithmeticException("Prime size must be at least 2 bits");
		}
		// The generation of a pseudorandom number with n-bits means the random number is in the range 0 and 2^n-1 (inclusive).
		// Requirements:
		// - Unpredictable
		// - No duplicates
		// - Uniformly distributed


		// Generate a random number of the specified bit length in the range 2^(bits-1) and 2^bits-1
		// since small primes are not considered to be secure.

		BigInteger min = BigInteger.ONE.shiftLeft(bits - 1);
		BigInteger max = BigInteger.ONE.shiftLeft(bits).subtract(BigInteger.ONE);
		return min.add(new BigInteger(bits, rng)).mod(max.subtract(min)).add(min);

	}

}
