package at.qe.sepm.skeleton.ui.controllers;

import java.util.Random;

import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.services.TextConverterService;
import at.qe.sepm.skeleton.services.encryptionServices.CaesarCryptography;
import at.qe.sepm.skeleton.services.encryptionServices.SubstitutionCryptography;
import at.qe.sepm.skeleton.ui.beans.InputHandleBean;

@Component
@ViewScoped
public class SubstitutionController {

	@Autowired
	TextConverterService textConverter;


	@Autowired
	SubstitutionCryptography substitutionCryptography;
	
	/**
	 *  * The methode handleEvent is needed to convert an inputstring to specific
	 * outputstring. Also the encrypted or decrypted string is going to be calculated 
	 * depentend on the outputstring. 
	 * Following values are important: 
	 * - specialCharacter (delete them?) 
	 * - tabs and whitespaces (delete them?) 
	 * - upperCase (change only to upper casse 
	 * - direction (encrypt or decrypt the inputstring?) 
	 */
	public void handleEvent(InputHandleBean input) {		
		if (input.getInputText() != null) {
			String outputTextTemp = input.getInputText();
			
			if (input.isSpecialCharacters()) {
				outputTextTemp = textConverter.deleteSpecialCharacters(outputTextTemp);
			}
			
			if (input.isWithSpaces()) {
				outputTextTemp = textConverter.removeWhitespacesAndTabs(outputTextTemp);
			}
			
			outputTextTemp = textConverter.stringToUpperCase(outputTextTemp);
			input.setOutputText(outputTextTemp);
			
			if (input.isDirection()) {
				input.setCryptedText(substitutionCryptography.decryptString(input.getOutputText(), input.getCiphertextAlphabet()));		
			} else {
				input.setCryptedText(substitutionCryptography.encryptString(input.getOutputText(), input.getCiphertextAlphabet()));
			}
		}
	}
	
	public void randomCiphretextAlphabetGenerator(InputHandleBean inputHandleBean) {
		Random rand = new Random();
		char alphabetArray[] =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); 
		int m, n; 
		char c1, c2;
		
		for(int i = 0; i<1000; i++) {
			m = rand.nextInt(26);
			n = rand.nextInt(26);
			c1 = alphabetArray[m];
			c2 = alphabetArray[n];
			alphabetArray[m] = c2;
			alphabetArray[n] = c1;
		}
		inputHandleBean.setCiphertextAlphabet(new String(alphabetArray));
	}
}
