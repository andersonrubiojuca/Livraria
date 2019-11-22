package br.com.livraria.livraria.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfiguracaoSpring {
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
