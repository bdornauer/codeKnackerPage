package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.services.CodeKnackerService;
import at.qe.sepm.skeleton.services.TextConverterService;
import at.qe.sepm.skeleton.services.encryptionServices.AtbaschCryptography;
import at.qe.sepm.skeleton.services.encryptionServices.CaesarCryptography;
import at.qe.sepm.skeleton.ui.beans.ChartBean;
import at.qe.sepm.skeleton.ui.beans.CodeKnackerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class CodeKnackerController {
	@Autowired
	TextConverterService textConverterService;

	@Autowired
	CodeKnackerService codeKnackerService;

	@Autowired
	CaesarCryptography caesarCryptography;

	@Autowired
	AtbaschCryptography atbaschCryptography;

	/**
	 * This method updates the whole page, if some input parameters change. This
	 * include - new input text - the technique - difficulty - the technique
	 * parameters. Relating to the input the correct output is computed. - converted
	 * input - caesarSliceValues - decrypted output - data charts
	 * 
	 * @param codeKnackerBean
	 */
	public void updateCiphretextAlphabet(CodeKnackerBean codeKnackerBean) {
		String inputConverter = updateInputConvert(codeKnackerBean.getInputText(), codeKnackerBean.getDifficulty());
		codeKnackerBean.setInputConvertedText(inputConverter);
		codeKnackerBean.setNumberLettersInput(this.countLettersInput(codeKnackerBean.getInputText()));
		codeKnackerBean.setCiphretextAlphabetChar(
				textConverterService.firstCharToArray(codeKnackerBean.getCiphretextAlphabet()));

		String output = "";
		if (codeKnackerBean.getTechnique().compareTo("substitution") == 0) {
			output = updateOutConvert(inputConverter, codeKnackerBean.getCiphretextAlphabetChar());
		} else if (codeKnackerBean.getTechnique().compareTo("caesar") == 0) {
			output = caesarCryptography.decryptString(codeKnackerBean.getInputConvertedText(), codeKnackerBean.getKeyCaesar());
		} else if (codeKnackerBean.getTechnique().compareTo("atbasch") == 0) {
			output = atbaschCryptography.decryptString(codeKnackerBean.getInputConvertedText());
		}
		codeKnackerBean.setOutputText(output.toUpperCase());

	}

	/**
	 * Updates the converted input text according to the difficulty and inputtext
	 * @param input
	 * @param difficulty
	 * @return
	 */
	public String updateInputConvert(String input, String difficulty) {
		input = textConverterService.stringToUpperCase(input);
		input = textConverterService.replaceUmlaute(input);
		if (difficulty.equals("Schwer")) {
			input = textConverterService.removeWhitespacesAndTabs(input);
		}
		return input;
	}

	/**
	 * TODO: better description - new name
	 * 
	 * @param input
	 * @param alphabetCiphre
	 * @return
	 */
	public String updateOutConvert(String input, char[] alphabetCiphre) {
		return codeKnackerService.encryptTextWithCiphreText(input, alphabetCiphre);
	}

	/**
	 * Updates the frequency analysis for the chart.
	 * 
	 * @param codeKnackerBean
	 * @param charBean
	 */
	public void updateChartInputValue(CodeKnackerBean codeKnackerBean, ChartBean charBean) {
		Integer countLetterInAlphabet[] = textConverterService
				.counterLettersInString(codeKnackerBean.getInputConvertedText());
		charBean.setCountInputLetters(countLetterInAlphabet);
		charBean.updateCountInputLettersChart();
	}

	/**
	 * Counts the letters of a text - without tabs, spaces and specialCharacters.
	 * @param output
	 * @return
	 */
	public int countLettersInput(String input) {
		String s = textConverterService.removeWhitespacesAndTabs(input);
		s = textConverterService.deleteSpecialCharacters(s); 
		return s.length(); 
	}
	/**
	 * Method to reset the substitution alphabet.
	 * 
	 * @param codeKnackerBean
	 */
	public void resetAlphabet(CodeKnackerBean codeKnackerBean) {
		codeKnackerBean.initCiphretextAlphabet();
		this.updateCiphretextAlphabet(codeKnackerBean);
	}


	public void updateUseAndUnusedLetters(CodeKnackerBean codeKnackerBean){
		String chiphreAlphabet = String.copyValueOf(codeKnackerBean.getCiphretextAlphabetChar()).toUpperCase();
		chiphreAlphabet = chiphreAlphabet.replace("-", "");

		String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String unusedLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String usedLetters = "--------------------------";

		for(int i=0; i < chiphreAlphabet.length(); i++){
			for(int j=0; j < ALPHABET.length(); j++){
				if( chiphreAlphabet.charAt(i) == ALPHABET.charAt(j) ){
					unusedLetters = unusedLetters.substring(0, j) + '-' + unusedLetters.substring(j + 1);
					usedLetters = usedLetters.substring(0, j) + ALPHABET.charAt(j) + usedLetters.substring(j + 1);
				}
			}
		}
	codeKnackerBean.setUnusedLetters(unusedLetters);
	codeKnackerBean.setUsedLetters(usedLetters);
	}
}
