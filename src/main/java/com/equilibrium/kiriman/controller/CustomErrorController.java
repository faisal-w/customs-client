package com.equilibrium.kiriman.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
	
	public static final String PATH = "/error";
	
	@RequestMapping(value = PATH)
	public String error() {
		
		return "errorPage";
	}
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}

}
