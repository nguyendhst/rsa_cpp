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
        for (int i = 0; i < 100; i++) {
            BigInteger random1 = math.random(1024);
            BigInteger random2 = math.random(1024);
            assertEquals(random1, random2);
        }
    }
}