package at.qe.sepm.skeleton.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TextConverterService {

	private Character[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ß', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private Character[] umlaute = { 'ä', 'ö', 'ü', 'Ä', 'Ö', 'Ü' };

	/**
	 * Checks if a character is part of the alphabet.
	 * 
	 * @param c The character which should be checked.
	 * @return Specified boolean result.
	 */
	public boolean checkIfLetterInAlphabet(char c) {
		List<Character> alphabetList = Arrays.asList(alphabet);

		if (alphabetList.contains(c)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if a character is part an umlaut.
	 * 
	 * @param c The character which should be checked.
	 * @return Specified boolean result.
	 */
	public boolean checkIfUmlaut(char c) {
		List<Character> umlautetList = Arrays.asList(umlaute);

		if (umlautetList.contains(c)) {
			return true;
		}
		return false;
	}

	/**
	 * A string is going to be converted into an integer array. Each integer element
	 * represents a single ascii character.
	 * 
	 * @param message The input string.
	 * @return The output array including integer values which represent ascii
	 *         values.
	 */
	public int[] stringToAsciiArray(String message) {
		char messageArrayOfChars[] = message.toCharArray();
		int stringOfAscciiChars[] = new int[messageArrayOfChars.length];
		for (int i = 0; i < messageArrayOfChars.length; i++) {
			stringOfAscciiChars[i] = (int) messageArrayOfChars[i];
		}
		return stringOfAscciiChars;
	}

	/**
	 * An given array containing ASCII-Integer-Value will be converter to a string.
	 * 
	 * @param asciiMessage The ASCII-Integer array.
	 * @return The converter string.
	 */
	public String asciiArrayToString(int asciiMessage[]) {
		char message[] = new char[asciiMessage.length];
		for (int i = 0; i < asciiMessage.length; i++) {
			message[i] = (char) asciiMessage[i];
		}
		return new String(message);
	}

	/**
	 * Replaces in an input string all umlaute wiht ae, ue and oe.
	 * 
	 * @param Inputstring containing possible umlaute.
	 * @return String containing without umlaute.
	 */
	public String replaceUmlaute(String input) {

		String output = input.replace("ü", "ue").replace("ö", "oe").replace("ä", "ae").replace("ß", "SS");

		output = output.replace("Ü", "UE").replace("Ö", "OE").replace("Ä", "AE");

		return output;
	}

	/**
	 * Strings containing elements ue, oe and ae will be converter to ü, ö and ä.
	 * 
	 * @param input Sting without umlaute, only ue, oe and ae.
	 * @return String with umlaute
	 */
	public String integrateUmlaute(String input) {

		String output = input.replace("ue", "ü").replace("oe", "ö").replace("ae", "ä");

		output = output.replace("UE", "Ü").replace("OE", "Ö").replace("AE", "Ä");

		return output;
	}

	/**
	 * Converts a string to a character array.
	 * 
	 * @param message Inputstring.
	 * @return Array containing single characters.
	 */
	public char[] stringToCharArray(String message) {
		return message.toCharArray();
	}

	/**
	 * Convert a charArray to a string.
	 * 
	 * @param message Array containing single characters.
	 * @return The computed string.
	 */
	public String charArrayToString(char[] message) {
		return String.copyValueOf(message);
	}

	/**
	 * Converts all letters in a string to uppercase letters.
	 * 
	 * @param text
	 * @return
	 */
	public String stringToUpperCase(String text) {
		return text.toUpperCase();
	}

	/**
	 * Deletes all special characters in a string e.g. ! .
	 * 
	 * @param text String with special characters.
	 * @return String without any special characters.
	 */
	public String deleteSpecialCharacters(String text) {
		text = text.replaceAll("[0-9]", "");
		return text = text.replaceAll("[^\\w_ ]", "");
	}

	/**
	 * Remove whitespaces and tabs in a string.
	 * 
	 * @param text Text with tabs and whitespaces.
	 * @return Text without tabs and whitespaces.
	 */
	public String removeWhitespacesAndTabs(String text) {
		return text.replaceAll("\\s+", "");
	}

	/**
	 * Takes a string array and creates a char array, which includes the first
	 * letter of each string
	 * 
	 * @param arrayStrings
	 */
	public char[] firstCharToArray(String[] arrayStrings) {
		char[] charArray = new char[arrayStrings.length];

		for (int i = 0; i < arrayStrings.length; i++) {
			if (arrayStrings[i] != null) {
				charArray[i] = arrayStrings[i].charAt(0);
			}
		}
		return charArray;
	}

	/**
	 * It counts the number of each letter in a string and returns a integer array,
	 * providing the counted value e.g. letter A is containing count[0] letters ...
	 * letter Z is containing count[25] letters
	 * 
	 * @param text
	 * @return integer array, providing the counted values of each letter
	 */
	public Integer[] counterLettersInString(String text) {
		text = text.toUpperCase();
		Integer count[] = new Integer[26];
		for (int i = 0; i < 26; i++) {
			count[i] = 0;
		}

		for (int i = 0; i < text.length(); i++) {
			int charValue = (int) text.charAt(i);
			if (64 < charValue && charValue < 91) {
				count[charValue - 65]++;
			}
		}

		return count;
	}

}
