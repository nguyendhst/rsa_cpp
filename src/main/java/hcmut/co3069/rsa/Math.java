package hcmut.co3069.rsa;

import java.lang.invoke.MutableCallSite;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Math {

    private static SecureRandom rng = new SecureRandom();
    public static final BigInteger SMALL_PRIME_PRODUCT = BigInteger
            .valueOf(2L * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23 * 29 * 31 * 37 * 41);
    public static final BigInteger P0 = BigInteger.valueOf(10000000);
    public static final double c_opt = 1.2;
    public static final int margin = 20;
    /*
     * FUNCTION TrialDivision(a,b: Longlnt): BOOLEAN returns the value TRUE if
     * and only if a is not divisible by a prime smaller or equal to b. This
     * procedure
     * requires a list of small primes, e.g., the primes smaller than 216 = 65536.
     */
    public static final int[] SMALL_PRIMES = getSmallPrimes();

    public Math(SecureRandom random) {
        rng = random;
    }

    public static int[] getSmallPrimes() {
        int p = 0;
        int two16 = 65536;
        ArrayList<Integer> primes = new ArrayList<Integer>();
        while (p < two16) {
            if (isPrime(p)) {
                primes.add(p);
            }
            p++;
        }
        ;
        return primes.stream().mapToInt(i -> i).toArray();
    }

    public static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        return false;
    }

    // modPow takes a base, exponent and modulus and returns base^exponent % modulus
    // modPow is a function that calculates the remainder of the division of a
    // number by another number
    // @phudang
    static BigInteger modPow(BigInteger base, BigInteger exponent, BigInteger modulus) {
        /*
         * Thuật toán như sau:
         * - Nếu số mũ là lẻ thì result = result * base % modulus
         * - Nếu số mũ là chẵn thì base = base * base % modulus
         * - Lấy số mũ chia 2
         * Khi số mũ bằng 0 thì dừng
         * Thuật toán có dạng chứng minh như sau:
         * base ^ exponent % modulus = (((base ^ 2) ^ (exponent // 2) % modulus)* (base
         * ^ (exponent % 2) % modulus)) mod modulus
         */
        BigInteger result = BigInteger.ONE;

        while (exponent.compareTo(BigInteger.ZERO) > 0) {

            // ta kiểm tra xem số mũ có chẵn hay lẻ
            if (exponent.testBit(0)) {
                result = result.multiply(base).mod(modulus);
            }
            // Sau đó base mũ 2 rồi phần trăm cho modulus để tìm base mới
            base = base.multiply(base).mod(modulus);
            // Lấy số mũ chia 2
            exponent = exponent.shiftRight(1);
        }
        return result;
    }

    static BigInteger modPow(BigInteger base, int exponent, BigInteger modulus) {
        /**
         * Thuật toán như sau:
         * - Nếu số mũ là lẻ thì result = result * base % modulus
         * - Nếu số mũ là chẵn thì base = base * base % modulus
         * - Lấy số mũ chia 2
         * Khi số mũ bằng 0 thì dừng
         * Thuật toán có dạng chứng minh như sau:
         * base ^ exponent % modulus = (((base ^ 2) ^ (exponent // 2) % modulus)* (base
         * ^ (exponent % 2) % modulus)) mod modulus
         */
        BigInteger result = BigInteger.ONE;

        while (exponent > 0) {

            // ta kiểm tra xem số mũ có chẵn hay lẻ
            if (exponent % 2 == 1) {
                result = result.multiply(base).mod(modulus);
            }
            // Sau đó base mũ 2 rồi phần trăm cho modulus để tìm base mới
            base = base.multiply(base).mod(modulus);
            // Lấy số mũ chia 2
            exponent = exponent >> 1;
        }
        return result;
    }

    public static BigInteger randomRange(BigInteger min, BigInteger max) {
        BigInteger range = max.subtract(min);
        int len = range.bitLength();
        BigInteger res = new BigInteger(len, rng);
        BigInteger result = new BigInteger(len, rng);
        while (result.compareTo(range) >= 0) {
            result = new BigInteger(len, rng);
        }
        result = result.add(min);
        return result;
    }

    private static double GenerateRelativeSize() {
        double a = rng.nextFloat() / 2.0 + 0.5;
        return a;
    }

    // Generate a random number of the specified bit length in the range 2^(bits-1)
    // and 2^bits-1
    // since small primes are not considered to be secure.
    public static BigInteger randomBigInteger(int bits) {
        if (bits < 2) {
            throw new ArithmeticException("Prime size must be at least 2 bits");
        }
        // The generation of a pseudorandom number with n-bits means the random number
        // is in the range 0 and 2^n-1 (inclusive).
        // Requirements:
        // - Unpredictable
        // - No duplicates
        // - Uniformly distributed

        // Generate a random number of the specified bit length in the range 2^(bits-1)
        // and 2^bits-1
        // since small primes are not considered to be secure.

        BigInteger min = BigInteger.ONE.shiftLeft(bits - 1);
        BigInteger max = BigInteger.ONE.shiftLeft(bits).subtract(BigInteger.ONE);

        // Generate a random number in the range [min, max]
        return min.add(new BigInteger(bits, rng)).mod(max.subtract(min)).add(min);

        // return new BigInteger(bits, new SecureRandom());

    }

    public static BigInteger randomPrime(int bitLength) {
        while (true) {
            BigInteger a = Math.randomBigInteger(bitLength);
            if (isProbablePrime(a, 10)) {
                return a;
            }
        }
    }

    public static BigInteger randomStrongPrime(int bitLength) {
        BigInteger s = randomPrime(bitLength / 2);
        BigInteger t = randomPrime(bitLength / 2);
        boolean isPrime = false;
        Random rnd = new Random();
        int i = rnd.nextInt(100);
        BigInteger r = BigInteger.ONE;
        while (!isPrime) {
            r = t.multiply(BigInteger.TWO).multiply(BigInteger.valueOf(i)).add(BigInteger.ONE);
            i++;
            if (millerRabinTest(r, 10)) {
                isPrime = true;
            }
        }
        BigInteger p_0 = BigInteger.TWO.multiply(modPow(s, r.subtract(BigInteger.TWO), r)).multiply(s)
                .subtract(BigInteger.ONE);
        isPrime = false;
        int j = rnd.nextInt(100);
        BigInteger p = BigInteger.ONE;
        while (!isPrime) {
            p = p_0.add(BigInteger.TWO.multiply(BigInteger.valueOf(j)).multiply(r).multiply(s));
            j++;
            if (millerRabinTest(p, 10)) {
                isPrime = true;
            }
        }
        return p;
    }

    /**
     * p_0 = 2*(pow(s,r-2,r))*s-1
     * 
     * print (f"\np_0={p_0}")
     * 
     * isPrime=False
     * j=random.randint(0,100)
     * p=0
     * while (isPrime==False):
     * p=p_0+2*j*r*s
     * j=j+1
     * if (sympy.isprime(p)==True):
     * isPrime=True
     */

    public static BigInteger gordonStrongPrime(int bitLen) {

        SecureRandom random = new SecureRandom();
        BigInteger r;
        BigInteger s = BigInteger.probablePrime(bitLen, random);
        BigInteger t = BigInteger.probablePrime(bitLen, random);

        // Step 2
        int i = 69;
        while (true) {
            r = t.multiply(BigInteger.valueOf(2)).multiply(BigInteger.valueOf(i)).add(BigInteger.ONE);
            if (r.isProbablePrime(10)) {
                break;
            }
            i++;
        }

        // // Step 3
        BigInteger p0 = BigInteger.valueOf(2).multiply(s.modPow(r.subtract(BigInteger.valueOf(2)), r)).multiply(s)
                .subtract(BigInteger.ONE);

        // // Step 4
        BigInteger p;
        int j = 69;
        while (true) {
            p = p0.add(BigInteger.valueOf(2).multiply(BigInteger.valueOf(j)).multiply(r).multiply(s));
            if (p.isProbablePrime(10)) {
                break;
            }
            j++;
        }

        return p;
        // return BigInteger.ZERO;
    }

    public static BigInteger randomSecondBigInteger(BigInteger prime1, int bitLength) {
        /**
         * Range là khoảng cách từ min đến max của 1 số n bits
         * secondRandomBigInt lớn hơn n * ln2 nếu prime1 < range / 2; ngược lại, prime2
         * phải bé hơn n * ln2
         */
        BigInteger range = BigInteger.ONE.shiftLeft(bitLength - 1);
        BigInteger secondRandomBigInt;
        SecureRandom secureRandom = new SecureRandom();

        if (prime1.compareTo(range.divide(BigInteger.TWO)) < 0) {
            do {
                secondRandomBigInt = new BigInteger(bitLength, secureRandom);
            } while (secondRandomBigInt.compareTo(range) <= 0);
        } else {
            do {
                secondRandomBigInt = new BigInteger(bitLength, secureRandom);
            } while (secondRandomBigInt.compareTo(range) >= 0);
        }

        return secondRandomBigInt.add(range);
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = a.mod(b);
            a = b;
            b = temp;
        }
        ;
        return a;
    }

    static public BigInteger[] gcdExtended(BigInteger a, BigInteger b) {
        BigInteger x = BigInteger.ZERO, y = BigInteger.ONE;
        BigInteger lastx = BigInteger.ONE,
                lasty = BigInteger.ZERO, temp;
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger q = a.divide(b);
            BigInteger r = a.mod(b);

            a = b;
            b = r;

            temp = x;
            x = lastx.subtract(q.multiply(x));
            lastx = temp;

            temp = y;
            y = lasty.subtract(q.multiply(y));
            lasty = temp;
        }

        // a * lastx + b * lasty = 1
        return new BigInteger[] { a, lastx, lasty };
    }

    public static BigInteger modInverse(BigInteger e, BigInteger phi) {
        BigInteger x = gcdExtended(e, phi)[1];
        if (x.compareTo(BigInteger.ZERO) < 0) {
            x = x.add(phi);
        }
        return x;
    }

    public static BigInteger probableStrongPrime() {
        // TODO
        return BigInteger.ONE;
    }

    public static boolean isProbablePrime(BigInteger n, int k) {
        if (!n.testBit(0)) {
            return false;
        }
        if (n.compareTo(BigInteger.valueOf(3)) <= 0) {
            return true;
        }
        if (!fermatTestBase(n, BigInteger.valueOf(2))) {
            return false;
        }
        if (gcd(n, SMALL_PRIME_PRODUCT).compareTo(BigInteger.ONE) != 0) {
            return false;
        }
        return millerRabinTest(n, k);
    }

    public static boolean fermatTestBase(BigInteger n, BigInteger a) {
        return a.modPow(n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE);
    }

    // millerRabinTest takes a BigInteger n and an integer k as input and returns
    // true if n is prime and false if n is composite.
    // @param n: the number to be tested
    // @param k: the number of times the test is repeated
    // @return true if n is prime and false if n is composite
    public static boolean millerRabinTest(BigInteger n, int k) {
        // miller-rabin algorithm
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return false;
        }

        if (n.compareTo(BigInteger.valueOf(3)) <= 0) {
            return true;
        }
        int r = 0;
        BigInteger d = n.subtract(BigInteger.ONE);
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            r++;
            d = d.divide(BigInteger.TWO);
        }

        // SecureRandom random = new SecureRandom();
        // Math math = new Math(random);
        for (int i = 0; i < k; i++) {
            // BigInteger a =
            // math.randomBigInteger(n.subtract(BigInteger.valueOf(2)).intValue());
            BigInteger a = Math.randomBigInteger(2048);
            // BigInteger x = a.modPow(d, n);
            BigInteger x = modPow(a, d, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
                continue;
            }
            boolean isProbablePrime = false;
            for (int j = 0; j < r - 1; j++) {
                // x = x.modPow(BigInteger.TWO, n);
                x = modPow(x, BigInteger.TWO, n);
                if (x.equals(BigInteger.ONE)) {
                    return false;
                }
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    isProbablePrime = true;
                    break;
                }
            }
            if (!isProbablePrime) {
                return false;
            }
        }
        return true;
    }

    public static BigInteger encrypt(PublicKey publicKey, String msg) {
        // ref:
        // https://crypto.stackexchange.com/questions/53219/how-to-encrypt-plain-message-with-rsa

        // BigInteger modPow(BigInteger base,BigInteger exponent,BigInteger modulus);
        byte[] msgBytes = msg.getBytes();
        BigInteger e = publicKey.e;
        BigInteger n = publicKey.n;
        BigInteger decryptInput = new BigInteger(msgBytes);

        return modPow(decryptInput, e, n);
    }

    public static String decrypt(PrivateKey privateKey, BigInteger encryptNumber) {
        // ref:
        // https://crypto.stackexchange.com/questions/53219/how-to-encrypt-plain-message-with-rsa

        // BigInteger modPow(BigInteger base,BigInteger exponent,BigInteger modulus);
        BigInteger d = privateKey.d;
        BigInteger n = privateKey.n;

        byte[] decryptBytes = modPow(encryptNumber, d, n).toByteArray();
        String decryptText = new String(decryptBytes);

        return decryptText;
    }

}

// class StrongPrimeGenerator {
// private final int nBits;
// private final SecureRandom random;

// public StrongPrimeGenerator(int nBits) {
// this.nBits = nBits;
// this.random = new SecureRandom();
// }

// public static void main(String[] args) {
// StrongPrimeGenerator generator = new StrongPrimeGenerator(512);
// BigInteger[] strongPrimes = generator.generateTwoStrongPrimes();
// System.out.println("Strong prime 1: " + strongPrimes[0]);
// System.out.println("Strong prime 2: " + strongPrimes[1]);
// }

// public BigInteger[] generateTwoStrongPrimes() {
// BigInteger[] strongPrimes = new BigInteger[2];
// for (int i = 0; i < 2; i++) {
// BigInteger prime;
// do {
// prime = new BigInteger(nBits, 100, random);
// } while (!isStrongPrime(prime));
// strongPrimes[i] = prime;
// }
// return strongPrimes;
// }

// private boolean isStrongPrime(BigInteger prime) {
// BigInteger pMinusOneDividedByTwo =
// prime.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));
// return pMinusOneDividedByTwo.isProbablePrime(100);
// }
// }