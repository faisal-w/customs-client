package com.equilibrium.kiriman.controller;

import com.equilibrium.kiriman.services.AdminClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	AdminClientService clientService;

	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("nosearchbar", 1);
		return "userAddPage";
	}
	
	@RequestMapping(value = {"index","/",""})
	public String index(Model model) {
		model.addAttribute("dataUser",clientService.getAllUser());
		return "userIndexPage";
	}
}
