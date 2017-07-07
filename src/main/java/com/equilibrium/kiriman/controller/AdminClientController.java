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

/**
 * Created by faisalw on 3/28/17.
 */
@Controller
@RequestMapping("/add_client")
public class AdminClientController {

    @Autowired
    AdminClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public String addClient(@ModelAttribute PerusahaanAndUserDto perusahaanAndUserDto, BindingResult errors,Model model){
        return "addClient";
    }

    @RequestMapping(value = {"/",""}, method = RequestMethod.POST)
    public String processAddClient(@ModelAttribute PerusahaanAndUserDto perusahaanAndUserDto, BindingResult errors,Model model){
        Perusahaan perusahaan = new Perusahaan();
        User user = new User();
        perusahaan.setNamaPerusahaan(perusahaanAndUserDto.getNamaPerusahaan());
        perusahaan.setNpwp(perusahaanAndUserDto.getNpwp());
        perusahaan.setAlamat(perusahaanAndUserDto.getAlamat());
        //perusahaan.setPassWSBeaCUkai(passwordEncoder.encode(dto.getPassWSBeaCukai()));
        perusahaan.setUserWSBeaCukai(perusahaanAndUserDto.getUserWSBeaCukai());
        perusahaan.setPassWSBeaCukai(perusahaanAndUserDto.getPassWSBeaCukai());
        perusahaan.setTokenWSBeaCukai(perusahaanAndUserDto.getTokenWSBeaCukai());
        user.setNama(perusahaanAndUserDto.getNama());
        user.setClient(perusahaan);
        user.setUsername(perusahaanAndUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(perusahaanAndUserDto.getPassword()));
        clientService.addClientAndUser(perusahaan,user);
        return "redirect:/add_client/";
    }

}
