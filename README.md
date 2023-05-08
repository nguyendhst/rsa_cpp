<h1 align="center"> Java RSA Implementation</h1>
   <h3 style="color:blue;"> Made for the CO3069 course</h3>

## Thực thi chương trình

### Thực thi sử dụng `java` để chạy `.class` files

- Các file `.class` được tạo ra sau khi biên dịch sẽ được lưu trong thư mục `out/`. Để thực thi chương trình, ta cần thực hiện 2 lệnh sau:

```bash
javac -d ./out ./src/main/java/hcmut/co3069/rsa/*.java
```

```bash
java -cp ./out/ hcmut.co3069.rsa.RSACryptoSystem
```

### Tạo `jar` file để thực thi

Sau khi thực thi 2 lệnh trên, ta sẽ có các file `.class` trong thư mục `out/`. Để tạo file `.jar` để thực thi, ta cần tạo file `Manifest` chứa thông tin về `Main-Class` của chương trình.

```bash
echo Main-Class: hcmut.co3069.rsa.RSACryptoSystem > myManifest
```

```bash
jar cfm ./output.jar myManifest -C ./out/ .
```

```bash
java -jar  output.jar
```

## Algorithms

### Sinh số ngẫu nhiên

  - `Math.random()`
	Cần tạo số pseudorandom kích thước `n` bits (n là số bit của số nguyên tố p, q).
		- Cần không trùng nhau và cách xa nhau.
		- Trong dự án này, ta sử dụng hiện thực BigInteger của Java và SecureRandom để tạo số ngẫu nhiên một cách an toàn.
```java
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
```





## Project Setup
  - IDE: IntelliJ IDEA (recommended)
  - Java 11 (temporary)
  - Build Automation: Gradle
  - Testing: JUnit 5

## How to run
  - Clone the project.
  - Using Gradlew:
    - To run build: `gradlew build`

    - To run tests: `gradlew test`
     
    - To run the program: `gradlew run`
  
## Project Layout
  - `src/main/java` - Contains the source code for the project.
  - `src/test/java` - Contains the test code for the project.
  - `hcmut.co3069.rsa` - Contains the main class for the project.
  - `hcmut.co3069.rsa.Utils` - Contains the utility classes for the project.
  - `hcmut.co3069.rsa.PrivateKey` - Contains the private key class for the project.
  - `hcmut.co3069.rsa.PublicKey` - Contains the public key class for the project.

# References
  - [Java Guide](https://www.baeldung.com/java-tutorial)
  - [Java Quick Start](https://www.baeldung.com/get-started-with-java-series)
  - [Java Packages][java_packages] 
  - [IntelliJ](https://www.jetbrains.com/help/idea/getting-started-with-gradle.html)

[java_packages]: https://www.baeldung.com/java-packages
