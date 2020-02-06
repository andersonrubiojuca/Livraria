package br.com.livraria.livraria.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrosController implements ErrorController{
	
	private static final String ERROR_PATH = "/error";
	

	@RequestMapping(ERROR_PATH)
	public String handleError(HttpServletRequest request) {
		Object erronumero = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(erronumero != null) {
			Integer statusCode = Integer.valueOf(erronumero.toString());
			
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "redirect:" + ERROR_PATH + "/404";
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				return "redirect:" + ERROR_PATH + "/403";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "redirect:" + ERROR_PATH + "/500";
			} else if (statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
				return "error/503";
			} else return "redirect:" + ERROR_PATH + "/500";
		}
		/*
		if(erronumero != null) {
			Integer statusCode = Integer.valueOf(erronumero.toString());
			
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "erro/404";
			} else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "erro/404";
			}
		}
		*/
		return "redirect:" + ERROR_PATH + "/404";
	}
	
	@RequestMapping(ERROR_PATH + "/403")
	public String erro403() {
		return ERROR_PATH + "/403";
	}
	
	@RequestMapping(ERROR_PATH + "/404")
	public String erro404() {
		return ERROR_PATH + "/404";
	}
	
	@RequestMapping(ERROR_PATH + "/500")
	public String erro500() {
		return ERROR_PATH + "/500";
	}
	
	@RequestMapping(ERROR_PATH + "/503")
	public String erro503() {
		return ERROR_PATH + "/503";
	}

	@Override
	public String getErrorPath() {
		return "redirect:" + ERROR_PATH;
	}
}
