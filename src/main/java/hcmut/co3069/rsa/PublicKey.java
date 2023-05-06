package hcmut.co3069.rsa;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

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
		try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
            return keyFactory.generatePublic(publicKeySpec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return null;
		}
	}
}
