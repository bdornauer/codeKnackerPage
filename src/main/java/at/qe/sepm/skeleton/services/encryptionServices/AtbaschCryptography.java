package at.qe.sepm.skeleton.services.encryptionServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the main service for the caesar encryption.
 * 
 * @author Benedikt
 *
 */
@Service
public class AtbaschCryptography implements EncryptionTechnique {

	public AtbaschCryptography() {

	}

	@Autowired
	CryptographyCenter cryptographyCenter;

	/**
	 * Encrypts a texts with the caesar technique.
	 * 
	 * @param text The inputtext which should be encrypted.
	 * @return The encrypted text.
	 */
	public String encryptString(String text) {
		AtbaschCryptography a = new AtbaschCryptography();
		return cryptographyCenter.encrypt(text, a);
	}

	/**
	 * Decrypts an encrypted texts with the caesar technique.
	 * 
	 * @param text The inputtext which should be encrypted.
	 * @return The encrypted text.
	 */
	public String decryptString(String text) {
		AtbaschCryptography a = new AtbaschCryptography();
		return cryptographyCenter.decrypt(text, a);
	}

	@Override
	public int encrypt(int charIntegerValue) {
		return 25 - charIntegerValue;
	}

	@Override
	public int decrypt(int charIntegerValue) {
		return 25 - charIntegerValue;
	}

}
