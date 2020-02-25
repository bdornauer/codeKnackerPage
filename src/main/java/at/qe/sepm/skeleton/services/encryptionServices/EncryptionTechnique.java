package at.qe.sepm.skeleton.services.encryptionServices;

/**
 * This is an interface for different encryption techniques. It ensures to provide 
 * methods to encrypt and decrypt strings with the specified encryption technique. 
 */
public interface EncryptionTechnique {
	
	/**
	 * This method encrypts with a specified encryption technique a single character. 
	 * Each character in the alphabet is represented by a integer value e.g A -> 1, B -> 2 etc. 
	 * @param charIntegerValue the character represented by a integer, which should be encrypted 
	 * @return the encrypted character represented by a integer 
	 */
	public int encrypt( int charIntegerValue);
	
	/**
	 * This method decrypts with a specified decryption technique a single character. 
	 * Each character in the alphabet is represented by a integer value e.g A -> 1, B -> 2 etc. 
	 * @param charIntegerValue the character represented by a integer, which should be decrypted 
	 * @return the decrypted character represented by a integer 
	 */
	public int decrypt(int charIntegerValue);
	
}
