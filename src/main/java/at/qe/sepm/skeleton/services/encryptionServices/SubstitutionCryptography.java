package at.qe.sepm.skeleton.services.encryptionServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the main service for the caesar encryption. 
 * @author Benedikt
 */
@Service
public class SubstitutionCryptography implements EncryptionTechnique {

	private String ciphertextAlphabet;

	public SubstitutionCryptography(String ciphertextAlphabet) {
		this.ciphertextAlphabet = ciphertextAlphabet;
	}

	public SubstitutionCryptography() {
		
	}

	@Autowired
	CryptographyCenter cryptographyCenter;

	/**
	 * Encrypts a texts with the caesar technique. 
	 * @param text The inputtext which should be encrypted.
	 * @return The encrypted text. 
	 */
	public String encryptString(String text, String ciphertextAlphabet) {
		SubstitutionCryptography a = new SubstitutionCryptography(ciphertextAlphabet);
		return cryptographyCenter.encrypt(text, a);
	}

	/**
	 * Decrypts an encrypted texts with the caesar technique. 
	 * @param text The inputtext which should be encrypted.
	 * @return The encrypted text. 
	 */
	public String decryptString(String text, String ciphertextAlphabet) {
		SubstitutionCryptography a = new SubstitutionCryptography(ciphertextAlphabet);
		return cryptographyCenter.decrypt(text, a);
	}


	@Override
	public int encrypt(int charIntegerValue) {
		return ciphertextAlphabet.charAt(charIntegerValue)-65; 
	}

	@Override
	public int decrypt(int charIntegerValue) {
		return ciphertextAlphabet.indexOf(((char) (charIntegerValue+65)));
	}

	public String getCiphertextAlphabet() {
		return ciphertextAlphabet;
	}

	public void setCiphertextAlphabet(String ciphertextAlphabet) {
		this.ciphertextAlphabet = ciphertextAlphabet;
	}

}
