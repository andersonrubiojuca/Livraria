package br.com.livraria.livraria.conf;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.livraria.livraria.model.CompraEnvio;

@Service
public class EnviarEmail{

	@Autowired
	private JavaMailSender sender;
	
	
	public void enviar(CompraEnvio compraEnvio) throws MessagingException{
		MimeMessage message = sender.createMimeMessage();
		message.setContent("", "text/html; charset=utf-8");
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setTo(compraEnvio.getEmail());
		helper.setSubject("Compra finalizada com sucesso!");
		
		sender.send(message);
	}
	
	/*
	public void enviar(CompraEnvio compraEnvio) throws MessagingException {
		Properties props = new Properties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    Session session = Session.getInstance(props, new Authenticator() {
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		return new PasswordAuthentication("jucaanderson66@gmail.com", "uDR3YUn69RZwcFg");
	    	}
	    });
		
		MimeMessage message = new MimeMessage(session);
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setTo(compraEnvio.getEmail());
		helper.setSubject("Compra finalizada com sucesso!");
		
		sender = getJavaMailSender();
		
		sender.send(message);
	}
	*/
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("jucaanderson66@gmail.com");
	    mailSender.setPassword("uDR3YUn69RZwcFg");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}
}
