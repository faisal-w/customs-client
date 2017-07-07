package com.equilibrium.kiriman.controller;

import com.equilibrium.kiriman.entities.Perusahaan;
import com.equilibrium.kiriman.entities.User;
import com.equilibrium.kiriman.entities.dto.PerusahaanAndUserDto;
import com.equilibrium.kiriman.services.AdminClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/perusahaan")
public class PerusahaanController {

    @Autowired
    AdminClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String add(@ModelAttribute PerusahaanAndUserDto perusahaanAndUserDto, BindingResult errors, Model model) {
		model.addAttribute("nosearchbar", 1);
		return "perusahaanAddPage";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAdd(@ModelAttribute PerusahaanAndUserDto perusahaanAndUserDto, BindingResult errors,Model model){
        Perusahaan perusahaan = new Perusahaan();
        User user = new User();
        perusahaan.setNamaPerusahaan(perusahaanAndUserDto.getNamaPerusahaan());
        perusahaan.setNpwp(perusahaanAndUserDto.getNpwp());
        perusahaan.setAlamat(perusahaanAndUserDto.getAlamat());
        perusahaan.setUserWSBeaCukai(perusahaanAndUserDto.getUserWSBeaCukai());
        perusahaan.setPassWSBeaCukai(perusahaanAndUserDto.getPassWSBeaCukai());
        perusahaan.setTokenWSBeaCukai(perusahaanAndUserDto.getTokenWSBeaCukai());
        perusahaan.setJenisDeposit(perusahaanAndUserDto.getJenisDeposit());
        perusahaan.setNomorIzin(perusahaanAndUserDto.getNomorIzin());
        try {
            SimpleDateFormat parser = new SimpleDateFormat("dd/mm/yyyy");
            perusahaan.setTglIzin(new java.sql.Date(parser.parse(perusahaanAndUserDto.getTglIzin()).getTime()));
        }catch (ParseException e){
            e.printStackTrace();
            perusahaan.setTglIzin(null);
        }
        user.setNama(perusahaanAndUserDto.getNama());
        user.setClient(perusahaan);
        user.setUsername(perusahaanAndUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(perusahaanAndUserDto.getPassword()));
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        clientService.addClientAndUser(perusahaan,user);
        return "redirect:/perusahaan/index";
    }
	
	@RequestMapping("index")
	public String index(Model model) {
        model.addAttribute("dataPerusahaan",clientService.getAllPerusahaan());
		return "perusahaanIndexPage";
	}
}
