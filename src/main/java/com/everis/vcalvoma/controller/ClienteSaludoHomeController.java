package com.everis.vcalvoma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClienteSaludoHomeController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
