package com.kitex.kitex.util;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Component
public class StringUtils {

	public String hashPassword(String plainString) {
		try {
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);

			KeySpec spec = new PBEKeySpec(plainString.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

			byte[] hash = factory.generateSecret(spec).getEncoded();

			return String.format("%x", new BigInteger(hash));
		}
		catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException("String hashing failed");
		}
	}

}
