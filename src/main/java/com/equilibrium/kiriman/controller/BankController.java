package com.equilibrium.kiriman.controller;

import com.equilibrium.kiriman.entities.RefBank;
import com.equilibrium.kiriman.services.repository.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/bank")
public class BankController {

	@Autowired
    BankRepo bankRepo;

	@RequestMapping(value = "add.html",method = RequestMethod.GET)
	public String add(@ModelAttribute RefBank refBank, BindingResult errors, Model model) {
		model.addAttribute("nosearchbar", 1);
		return "bankAddPage";
	}

	@RequestMapping(value = "add.html",method = RequestMethod.POST)
    public String processAdd(@ModelAttribute RefBank refBank, BindingResult errors, Model model){
        RefBank bankBaru = refBank;
        bankRepo.save(bankBaru);
        return "redirect:/bank/index.html";
    }
	
	@RequestMapping("index.html")
	public String index(Model model) {
        model.addAttribute("dataBank",bankRepo.findAll());
		return "bankIndexPage";
	}
}
