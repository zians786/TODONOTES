package com.bridgeit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {
	
	@RequestMapping(value = {"/hello"}, method = RequestMethod.GET)
	public String welcome() {
		return "Welcome Admin";
	}

	
	
}
