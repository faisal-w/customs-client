package com.equilibrium.kiriman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login() {
		return "loginPage";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String accessDenied() {

		// do what I want

		return "redirect:/";
	}

}
