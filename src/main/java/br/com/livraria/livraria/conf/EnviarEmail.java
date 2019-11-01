package br.com.livraria.livraria.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

//mecher com isso depois
@Component
public class EnviarEmail {

	@Autowired
	private JavaMailSender sender;
	
	public void enviar() {
		
	}
}
