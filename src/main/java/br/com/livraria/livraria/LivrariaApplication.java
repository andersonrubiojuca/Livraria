package br.com.livraria.livraria;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Controller
public class LivrariaApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(LivrariaApplication.class, args);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
        .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}
}
