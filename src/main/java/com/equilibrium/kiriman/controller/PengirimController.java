package com.equilibrium.kiriman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pengirim")
public class PengirimController {
	
	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("nosearchbar", 1);
		return "pengirimAddPage";
	}
	
	@RequestMapping("index")
	public String index() {
		return "pengirimIndexPage";
	}
}