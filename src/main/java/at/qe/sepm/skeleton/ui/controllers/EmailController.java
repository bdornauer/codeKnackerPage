package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.services.EmailService;
import at.qe.sepm.skeleton.ui.beans.EmailBean;
import at.qe.sepm.skeleton.ui.beans.InputHandleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class EmailController {

	@Autowired
	EmailService emailService;

	/**
	 * Sends an email with the cryptedText message and a massage from the sender to
	 * specific email.
	 * 
	 * @param emailBean Bean including the email from the recipient and the massage
	 *                  from the sender.
	 */
	public void sendEmail(EmailBean emailBean, InputHandleBean input) {
		emailService.sendEmailWithEncryption(input.getCryptedText(), emailBean.getTo(), emailBean.getName(),
				emailBean.getMessage());
	}

}
