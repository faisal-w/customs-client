package com.equilibrium.kiriman.controller;

import com.equilibrium.kiriman.entities.ArgoBilling;
import com.equilibrium.kiriman.entities.dto.DepositBaruDto;
import com.equilibrium.kiriman.services.AdminClientService;
import com.equilibrium.kiriman.services.ArgoBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    AdminClientService clientService;

    @Autowired
    ArgoBillingService argoBillingService;
	
	@RequestMapping(value = "add.html",method = RequestMethod.GET)
	public String add(@ModelAttribute DepositBaruDto depositBaruDto, BindingResult errors, Model model) {
		model.addAttribute("nosearchbar", 1);
		model.addAttribute("dataPerusahaan",clientService.getAllPerusahaan());
		return "depositAddPage";
	}

	@RequestMapping(value = "add.html",method = RequestMethod.POST)
    public String processAddDeposit(@ModelAttribute DepositBaruDto depositBaruDto, BindingResult errors, Model model){
        ArgoBilling deposit = new ArgoBilling();
        deposit.setPerusahaan(clientService.getOnePerusahaan(depositBaruDto.getIdPerusahaan()));
        deposit.setNilaiTransaksi(BigInteger.valueOf(depositBaruDto.getNilaiDeposit()));
        deposit.setDeposit(true);
        deposit.setTglTrans(new java.sql.Date(new java.util.Date().getTime()));
        deposit.setCnPibk(null);
        deposit.setKeterangan("No.Referensi : "+depositBaruDto.getNoReferensi()
                             +"\nBank Pengirim : "+depositBaruDto.getBankPengirim()
                             +"\nBank Penerima : "+depositBaruDto.getBankTujuan());
        argoBillingService.depositBaru(deposit);
	    return "redirect:/deposit/index.html";
    }

	@RequestMapping("index.html")
	public String index(Model model) {
        List<ArgoBilling> deposits = argoBillingService.getAllDeposit();
        model.addAttribute("dataDeposit",deposits);
		return "depositIndexPage";
	}
}
