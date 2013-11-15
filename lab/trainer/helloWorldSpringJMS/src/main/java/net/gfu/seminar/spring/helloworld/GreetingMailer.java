package net.gfu.seminar.spring.helloworld;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class GreetingMailer {
	private MailSender mailSender;
	private SimpleMailMessage templateMessage;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public void sendMessage(SpecialGuest guest) {
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setTo(guest.getMailAddress());
		msg.setText("Dear " + guest.getFirstName() + guest.getLastName()
				+ ", welcome to Spring!");
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			ex.printStackTrace(System.err);
		}
	}
}
