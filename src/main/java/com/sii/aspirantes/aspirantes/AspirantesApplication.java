package com.sii.aspirantes.aspirantes;

import com.sii.aspirantes.aspirantes.Service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class AspirantesApplication {

	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(AspirantesApplication.class, args);
	}

	/*@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		String[] emails = {"miguel.ea.bahena1312@gmail.com", "miguel_anaya15@hotmail.com"};

		emailSenderService.sendSimpleEmail(emails,
				"This is Email Body with Attachment...",
				"This email has attachment");
	}*/

}
