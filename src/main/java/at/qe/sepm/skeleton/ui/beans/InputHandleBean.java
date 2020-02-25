package at.qe.sepm.skeleton.ui.beans;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@Component
@Scope("session")
public class InputHandleBean {
	private boolean specialCharacters;
	private boolean withSpaces;

	private boolean direction;
	private int key;
	private String ciphertextAlphabet; 
	
	private String inputText;
	private String outputText;
	private String cryptedText;


	public boolean isDirection() {
		return direction;
	}
	
	@PostConstruct
	public void init() {
		setCiphertextAlphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}

	/**
	 * This method chooses between the encryption and decryption. According to that the correct 
	 * layout is chosen.  
	 * @param direction
	 */
	public void setDirection(boolean direction) {
		this.direction = direction;
		if(direction == true) {
			PrimeFaces.current().executeScript("off()");
		}else {
			PrimeFaces.current().executeScript("on()");
		}
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	public boolean isSpecialCharacters() {
		return specialCharacters;
	}

	public void setSpecialCharacters(boolean specialCharacters) {
		this.specialCharacters = specialCharacters;
	}

	public boolean isWithSpaces() {
		return withSpaces;
	}

	public void setWithSpaces(boolean withSpaces) {
		this.withSpaces = withSpaces;
	}

	public String getOutputText() {
		return outputText;
	}

	public void setOutputText(String outputText) {
		this.outputText = outputText;
	}

	public String getCryptedText() {
		return cryptedText;
	}

	public void setCryptedText(String cryptedText) {
		this.cryptedText = cryptedText;
	}

	public String getCiphertextAlphabet() {
		return ciphertextAlphabet;
	}

	public void setCiphertextAlphabet(String ciphertextAlphabet) {
		this.ciphertextAlphabet = ciphertextAlphabet;
	}
	
}
