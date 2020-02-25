package at.qe.sepm.skeleton.services.encryptionServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.qe.sepm.skeleton.services.TextConverterService;


@Service
public class CryptographyCenter {

	@Autowired
	TextConverterService textConverterService;

	/**
	 * The method is a gerneral function to handle a text, which should be
	 * encrypted. A character is encoded in following way: 
	 * char -> int (ASCII Value) -> int between 0 and 26 (simple function e.g 'A' -> 1, 'B'->2) -> int (ASCII
	 * Value) -> char 
	 * The function provides support for 
	 * - replacing umlaute
	 * - ignoring special characters like ! but including it to the encrypted text 
	 * - maintaining "upper and lower feature" of characters
	 * 
	 * @param inputText The text which should be encrypted
	 * @param technique A specified technique which includes a encryption function
	 *                  for a single char.
	 * @return The encrypted text.
	 */
	public String encrypt(String inputText, EncryptionTechnique technique) {
		inputText = textConverterService.replaceUmlaute(inputText);
		char[] charArray = textConverterService.stringToCharArray(inputText);
		int valueChar = 0;
		int valueEncryption;

		for (int i = 0; i < charArray.length; i++) {
			valueChar = (int) charArray[i];
			if (textConverterService.checkIfLetterInAlphabet(charArray[i])
					|| textConverterService.checkIfUmlaut(charArray[i])) {
				valueEncryption = encryptChar(valueChar, technique);
			} else {
				valueEncryption = valueChar;
			}
			charArray[i] = (char) valueEncryption;
		}
		return (new String(charArray));
	}

	/**
	 * The method is a gerneral function to handle a text, which should be
	 * decrypted. A character is encoded in following way: 
	 * char -> int (ASCII Value) -> int between 0 and 26 (simple function e.g 'A' -> 1, 'B'->2) -> int (ASCII
	 * Value) -> char 
	 * The function provides support for 
	 * - replacing ue, ae, oe with ü, ä, ö
	 * - ignoring special characters like ! but including it to the decrypted text 
	 * - maintaining "upper and lower feature" of characters
	 * 
	 * @param encrytedText The text which should be decrypted. 
	 * @param technique A specified technique which includes a decryption function
	 *                  for a single char.
	 * @return The decrypted text.
	 * @throws UmlauteRuntimeException Error if the text includes ü, ä, ö -> will be handeled in the GUI 
	 */

	public String decrypt(String encrytedText, EncryptionTechnique technique) throws UmlauteRuntimeException {
		char[] charArray = textConverterService.stringToCharArray(encrytedText);
		int valueChar;
		int valueDecryptedChar;

		for (int i = 0; i < charArray.length; i++) {
			valueChar = (int) charArray[i];
			if (textConverterService.checkIfUmlaut(charArray[i])) {
				throw new UmlauteRuntimeException();
			} else if (textConverterService.checkIfLetterInAlphabet(charArray[i])) {
				valueDecryptedChar = decrytChar(valueChar, technique);
			} else {
				valueDecryptedChar = valueChar;
			}
			charArray[i] = (char) valueDecryptedChar;
		}
		String output = new String(charArray);
		return textConverterService.integrateUmlaute(output);
	}

	/**
	 * Function the select the correct encryption technique for lower cases and upper cases. 
	 * @param asciiChar The char represented by the Ascii integer value. 
	 * @param technique  A specified technique which includes an encryption function
	 *                   for a single char.
	 * @return The encrypted char represented by the Ascii integer value
	 */
	public int encryptChar(int asciiChar, EncryptionTechnique technique) {
		return technique.encrypt(asciiChar - 65) + 65;
	}
	
	/**
	 * Function the select the correct decryption technique for lower cases and upper cases. 
	 * @param asciiChar The char represented by the Ascii integer value. 
	 * @param technique  A specified technique which includes a decryption function
	 *                   for a single char.
	 * @return The decrypted char represented by the Ascii integer value
	 */
	public int decrytChar(int asciiChar, EncryptionTechnique technique) {

		return technique.decrypt(asciiChar - 65) + 65;
	}

}
