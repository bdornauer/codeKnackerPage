package at.qe.sepm.skeleton.services.encryptionServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the main service for the caesar encryption. 
 * @author Benedikt
 *
 */
@Service
public class CaesarCryptography implements EncryptionTechnique {

	private int key;

	public CaesarCryptography(int key) {
		this.key = key;
	}

	public CaesarCryptography() {

	}

	@Autowired
	CryptographyCenter cryptographyCenter;

	/**
	 * Encrypts a texts with the caesar technique. 
	 * @param text The inputtext which should be encrypted. 
	 * @param key The key for the encryption. 
	 * @return The encrypted text. 
	 */
	public String encryptString(String text, int key) {
		CaesarCryptography a = new CaesarCryptography(key);
		return cryptographyCenter.encrypt(text, a);
	}

	/**
	 * Decrypts an encrypted texts with the caesar technique. 
	 * @param text The inputtext which should be encrypted. 
	 * @param key The key for the encryption. 
	 * @return The encrypted text. 
	 */
	public String decryptString(String text, int key) {
		CaesarCryptography a = new CaesarCryptography(key);
		return cryptographyCenter.decrypt(text, a);
	}


	@Override
	public int encrypt(int charIntegerValue) {
		int newkey = Math.abs(key);
		return (charIntegerValue + newkey) % 26;
	}

	@Override
	public int decrypt(int charIntegerValue) {
		int newkey = Math.abs(key) % 26;
		if( (charIntegerValue - newkey) >= 0 ) {
			return charIntegerValue - newkey; 
		}else {
			return 26+charIntegerValue - newkey; 
		}
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

}
