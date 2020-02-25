package at.qe.sepm.skeleton.ui.beans;

import org.primefaces.PrimeFaces;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

@ManagedBean
@Component
@Scope("session")
public class CodeKnackerBean {
	private String[] ciphretextAlphabet = new String[26];
	char[] ciphretextAlphabetChar = new char[26];

	final private String ALPHABET ="--------------------------";
	private String unusedLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String usedLetters= "--------------------------";

	private String technique;
	private String difficulty;

	private String inputText;
	private String inputConvertedText;
	private String outputText;

	private int keyCaesar;

	private int numberLettersInput;

	/**
	 * Sets the initial values, at the first loading of the codeKnackerPage.
	 */
	@PostConstruct
	public void init() {
		initCiphretextAlphabet();
		setDifficulty("Leicht");
		setTechnique("caesar");

		setInputText("");
		setInputConvertedText("");
		setOutputText("");
	}

	public String[] getCiphretextAlphabet() {
		return ciphretextAlphabet;
	}

	public void setCiphretextAlphabet(String[] ciphretextAlphabet) {
		this.ciphretextAlphabet = ciphretextAlphabet;
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	public String getInputConvertedText() {
		return inputConvertedText;
	}

	public void setInputConvertedText(String inputConvertedText) {
		this.inputConvertedText = inputConvertedText;
	}

	public String getOutputText() {
		return outputText;
	}

	public void setOutputText(String outputText) {
		this.outputText = outputText;
	}

	public char[] getCiphretextAlphabetChar() {
		return ciphretextAlphabetChar;
	}

	public void setCiphretextAlphabetChar(char[] ciphretextAlphabetChar) {
		this.ciphretextAlphabetChar = ciphretextAlphabetChar;
	}

	public String getTechnique() {
		updateTechniqueDivs(technique);
		return technique;
	}

	public void setTechnique(String technique) {
		this.technique = technique;
	}
	

	/**
	 * Method to chose the right technique-layout. Only one should be visible, the
	 * other techniques are invisible.
	 * 
	 * @param div
	 */
	@RequestMapping(value = "/sites/codeKnacker/codeKnacker.xhtml", method = RequestMethod.GET)
	public void updateTechniqueDivs(String div) {
		PrimeFaces.current().executeScript("closeDiv('caesar');");
		PrimeFaces.current().executeScript("closeDiv('substitution');");
		PrimeFaces.current().executeScript("closeDiv('atbasch');");
		PrimeFaces.current().executeScript("openDiv('" + div + "');");
	}

	public void initalizeTechnique(String technique) {
		PrimeFaces.current().executeScript("openDiv('" + technique + "');");
	}

	public int getKeyCaesar() {
		return keyCaesar;
	}

	/**
	 * Turns the slice-animation, running a script method.
	 * 
	 * @param keyCaesar
	 */
	public void setKeyCaesar(int keyCaesar) {
		this.keyCaesar = keyCaesar;
		PrimeFaces.current().executeScript("turnCaesar(" + this.keyCaesar + ")");
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Resets the substation-alphabet - to the initialized alphabet (latin alphabet)
	 */
	public void initCiphretextAlphabet() {
		for (int i = 0; i < this.ALPHABET.length(); i++) {
			this.ciphretextAlphabet[i] = Character.toString(ALPHABET.charAt(i));
		}

		unusedLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		usedLetters = "--------------------------";
	}


	public int getNumberLettersInput() {
		return numberLettersInput;
	}
	public void setNumberLettersInput(int numberLettersInput) {
		this.numberLettersInput = numberLettersInput;
	}

	public String getUnusedLetters() {
		return unusedLetters;
	}

	public void setUnusedLetters(String unusedLetters) {
		this.unusedLetters = unusedLetters;
	}

	public String getUsedLetters() {
		return usedLetters;
	}

	public void setUsedLetters(String usedLetter) {
		this.usedLetters = usedLetter;
	}
}