package br.com.livraria.livraria.conf.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.livraria.livraria.model.CompraEnvio;

public class EnviarEmail{
	
	
	public void enviar(CompraEnvio compraEnvio){
		PaginaDeCompra pagina = new PaginaDeCompra();
		String conteudo = pagina.email(compraEnvio);
		
		String to = compraEnvio.getEmail();
		
		String from = "jucaanderson66@gmail.com";
		String password = "uDR3YUn69RZwcFg";
		
		String host = "smtp.gmail.com";
		
		Properties props = new Properties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.host", host);
	    
	    Session session = Session.getInstance(props, new Authenticator() {
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		return new PasswordAuthentication(from, password);
	    	}
	    });
	    
	    try {
	    	Message message = new MimeMessage(session);
	    	
	    	message.setFrom(new InternetAddress(from));
	    	
	    	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	    	
	    	message.setSubject("Compra finalizada com sucesso!");
	    	
	    	message.setContent(conteudo, "text/html; charset=utf-8");
	    	
	    	new Thread(new Runnable() {
				public void run() {
					try {
						Transport.send(message);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}).start();
	    	
	    	
	    	System.out.println("Deu certo!");
	    } catch (MessagingException e) {
	    	e.printStackTrace();
	    }
	}
}
