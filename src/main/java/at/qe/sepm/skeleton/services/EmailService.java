package at.qe.sepm.skeleton.services;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * This is a class providing a service to send emails in differnet way. 
 * @author Benedikt
 *
 */
@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * This is the template to send emails including encrypted texts. 
	 * 
	 * @param encryptinTechniqueName The name of the encryption technique. 
	 * @param enryptionMessage The encrypted message. 
	 * @param to The recipient of the email.
	 * @param messag The message of the recipient for the email. 
	 */
	public void sendEmailWithEncryption(String enryptionMessage, String to, String name, 
			String messageFromUser) {
		String stringTobeSend =  "<!DOCTYPE html>" +
				"<head>\r\n" + 
				"<meta charset=\"utf-8\"> <!-- utf-8 works for most cases -->\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width\"> <!-- Forcing initial-scale shouldn't be necessary -->\r\n" + 
				"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <!-- Use the latest (edge) version of IE rendering engine -->\r\n" + 
				"<meta name=\"x-apple-disable-message-reformatting\">  <!-- Disable auto-scale in iOS 10 Mail entirely -->"+
				"</head>\r\n" + 
				"<html>\r\n" + 
				"<body style=\"text-align: center; background: #8FC1E3;\">\r\n" + 
				"\r\n" + 
				"	<img alt=\"Symobl\" src=\"cid:identifier1234\" width=\"150px\" height=\"150px\" >\r\n" + 
				"	<h2>Kryptografie-App</h2>\r\n" + 
				"	\r\n" + 
				"	<p>Der*Die Verfasser*in <b>"+ name +"</b> sendet dir folgende verschl&uumlsselte Nachricht:</p>\r\n" +
				"	<div style=\"border: black 2.5px solid; text-align: center; margin:0px auto;max-width:500px; background: white;\">\r\n" + 
					enryptionMessage +
				"	</div>\r\n" + 
				"	\r\n" + 
				"	<p>Au&szligerdem sendet sie*er dir folgende <b>unverschl&uumlsselte</b> Nachricht mit</p>\r\n" +
				"	<div style=\"border: black 2.5px solid; text-align: center; margin:0px auto;max-width:500px; background: white;\">\r\n" + 
					messageFromUser+
				"	</div>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"	<h2>Viel Spa&szlig beim Entschl&uumlsseln!</h2>\r\n" +
				"	\r\n" + 
				"	<p>\r\n" + 
				"		PS: Falls du Fragen zum Verschl&uumlsselungsverfahren hast, wende dich an die*den Verfasser*in <br>\r\n" +
				"		oder informiere dich auf der CodeKnacker-Seite\r\n" +
				"	</p>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		 try {
			 	FileSystemResource res = new FileSystemResource(new File("src/main/java/at/qe/sepm/skeleton/services/key.png"));
	            MimeMessage message = mailSender.createMimeMessage();
	            message.setSubject("Eine Geheimbotschaft");
	            MimeMessageHelper helper;
	            helper = new MimeMessageHelper(message, true);
	            helper.setTo(to);
	            helper.setFrom("codeknacker@mail.de");
	            helper.setText(stringTobeSend, true);
	            helper.addInline("identifier1234", res);
	            mailSender.send(message);
	        } catch (MessagingException ex) {
	            ex.printStackTrace(); 
	        }
	}
}
