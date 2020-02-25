package at.qe.sepm.skeleton.services;

import org.springframework.stereotype.Service;

@Service
public class CodeKnackerService {
	
	private Character[] alphabetSimple = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; 
	
	public String encryptTextWithCiphreText(String input, char[] alphabetCiphre) {
		char[] inputChar = input.toCharArray(); 
		char[] outputChar = input.toCharArray(); 
		
		for (int i = 0; i < alphabetSimple.length; i++) {
			for(int j=0; j < input.length(); j++) {
				if(alphabetSimple[i] != alphabetCiphre[i] && alphabetSimple[i]==inputChar[j]) {
					outputChar[j] = alphabetCiphre[i];
				}
			}
		}
		return String.copyValueOf(outputChar); 
	}
}