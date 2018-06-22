package com.everis.vcalvoma.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everis.vcalvoma.domain.Saludo;
import com.everis.vcalvoma.service.ClientSaludoService;

@Controller
public class ClienteSaludoController {
	
	@Autowired
	protected ClientSaludoService clienteSaludoService;

	protected Logger logger = Logger.getLogger(ClienteSaludoController.class
			.getName());


	@RequestMapping("/clienteSaludo")
	public String goHome() {
		return "index";
	}
	
	@RequestMapping("/clienteSaludo/{name}")
	public String saludo(Model model, @PathVariable("name") String name) {	

		Saludo saludo = clienteSaludoService.saludo(name);
	
		model.addAttribute("saludo", saludo.getContent());
	
		return "saludo";
		
	}
	
	@RequestMapping("/clienteSaludoFuture/{name}")
	public String saludoFuture(Model model, @PathVariable("name") String name) throws InterruptedException, ExecutionException {
	
		Future<Saludo> saludo = clienteSaludoService.saludoFuture(name);
		
		model.addAttribute("saludo", saludo.get().getContent());
	
		return "saludo";
		
	}
}
