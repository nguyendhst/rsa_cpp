package hcmut.co3069.rsa;

import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;

public class PublicKey implements RSAPublicKey {
	private BigInteger modulus;
	private BigInteger publicExponent;

	public PublicKey(BigInteger modulus, BigInteger publicExponent) {
		this.modulus = modulus;
		this.publicExponent = publicExponent;
	}

	@Override
	public BigInteger getModulus() {
		return modulus;
	}

	@Override
	public BigInteger getPublicExponent() {
		return publicExponent;
	}

	@Override
	public String getAlgorithm() {
		return "RSA";
	}

	@Override
	public String getFormat() {
		return "X.509";
	}

	@Override
	public byte[] getEncoded() {
		// Implement encoding if necessary
		return null;
	}
}
