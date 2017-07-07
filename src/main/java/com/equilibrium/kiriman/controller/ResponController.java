package com.equilibrium.kiriman.controller;

import com.equilibrium.kiriman.entities.Perusahaan;
import com.equilibrium.kiriman.services.repository.PerusahaanRepo;
import com.equilibrium.kiriman.services.repository.ResponseDataCnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by faisalw on 4/6/17.
 */
@Controller
public class ResponController {

    @Autowired
    ResponseDataCnRepo responRepo;
    @Autowired
    PerusahaanRepo perusahaanRepo;

    @RequestMapping("/response")
    public String response(Model model, final Authentication auth) {
        model.addAttribute("nosearchbar", 0);
        //System.out.println("username logged in : "+auth.getName());
        Perusahaan perusahaan = perusahaanRepo.findByPegawais_username(auth.getName());
        //Sort sort = new Sort(Sort.Direction.DESC,"waktuCekRespon");
        //model.addAttribute("dataRespons",responRepo.findAll(sort));
        //System.out.println("Perusahaan : " + perusahaan.getNamaPerusahaan());
        model.addAttribute("dataRespons",responRepo.findByCnPibkTerkait_perusahaanOrderByWaktuCekResponDesc(perusahaan));
        return "responsePage";
    }

}
