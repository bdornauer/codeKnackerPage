package at.qe.sepm.skeleton.ui.controllers;

import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.qe.sepm.skeleton.services.TextConverterService;
import at.qe.sepm.skeleton.services.encryptionServices.AtbaschCryptography;
import at.qe.sepm.skeleton.services.encryptionServices.CaesarCryptography;
import at.qe.sepm.skeleton.ui.beans.InputHandleBean;

@Component
@ViewScoped
public class AtbaschController {

	@Autowired
	TextConverterService textConverter;

	@Autowired
	AtbaschCryptography atbaschCryptography;

	/**
	 * * The methode handleEvent is needed to convert an inputstring to specific
	 * outputstring. Also the encrypted or decrypted string is going to be
	 * calculated depentend on the outputstring. Following values are important: -
	 * specialCharacter (delete them?) - tabs and whitespaces (delete them?) -
	 * upperCase (change only to upper casse - direction (encrypt or decrypt the
	 * inputstring?)
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
				input.setCryptedText(atbaschCryptography.decryptString(input.getOutputText()));
			} else {
				input.setCryptedText(atbaschCryptography.encryptString(input.getOutputText()));
			}
		}
	}

}
