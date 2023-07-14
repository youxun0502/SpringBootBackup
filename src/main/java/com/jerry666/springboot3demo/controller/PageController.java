package com.jerry666.springboot3demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class PageController {

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/playAjax/intro")
	public String playAjax() {
		return "ajax/ajax1";
	}

	@GetMapping("/playAjax/send")
	public String sendJsonAjax() {
		return "ajax/ajaxSend";
	}
}
