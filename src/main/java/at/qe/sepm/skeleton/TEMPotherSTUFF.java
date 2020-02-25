package at.qe.sepm.skeleton;

public class TEMPotherSTUFF {
	
	static char alphabetArray[] =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); 
	
	public static void main(String[] args) {
		for(int i = 0; i<26; i++) {

			System.out.println("<div class=\"alphabetCell\"\n" +
					"\t\t\tonmouseover=\"addColor(document.getElementById('phrase'),'"+alphabetArray[i]+" ',0); \"\n" +
					"\t\t\tonmouseout=\"removeColor()\">\n" +
					"\n" +
					"\t\t\t<div class=\"plaintextLetter\">\n" +
					"\t\t\t\t<p:outputLabel value=\""+ alphabetArray[i] +"\" />\n" +
					"\t\t\t</div>\n" +
					"\n" +
					"\t\t\t<div class=\"ciphretextLetter\">\n" +
					"\t\t\t\t<p:inputText size=\"1\"\n" +
					"\t\t\t\t\tvalue=\"#{codeKnackerBean.ciphretextAlphabet["+ i +"]}\" id=\""+alphabetArray[i]+"\"\n" +
					"\t\t\t\t\tclass=\"inputLetter\" maxlength=\"1\">\n" +
					"\t\t\t\t\t<p:ajax event=\"keydown\" update=\"out1 out2\"\n" +
					"\t\t\t\t\t\tlistener=\"#{codeKnackerController.updateCiphretextAlphabet(codeKnackerBean)}\" />\n" +
					"\t\t\t\t\t<p:ajax event=\"keydown\" update=\"letters\"\n" +
					"\t\t\t\t\t\t\tlistener=\"#{codeKnackerController.updateUseAndUnusedLetters(codeKnackerBean)}\" />\n" +
					"\t\t\t\t\t<f:validateRequired />\n" +
					"\t\t\t\t\t<p:keyFilter regEx=\"/[\\-a-zA-Z]/\" />\n" +
					"\t\t\t\t</p:inputText>\n" +
					"\t\t\t</div>\n" +
					"\t\t</div>");
		}
		
	}
}
