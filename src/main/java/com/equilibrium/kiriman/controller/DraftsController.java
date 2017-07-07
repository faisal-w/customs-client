package com.equilibrium.kiriman.controller;

import com.equilibrium.kiriman.entities.LogCnPibk;
import com.equilibrium.kiriman.entities.Perusahaan;
import com.equilibrium.kiriman.services.repository.LogCnPibkRepo;
import com.equilibrium.kiriman.services.repository.PerusahaanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by faisalw on 4/24/17.
 */
@Controller
public class DraftsController {

    @Autowired
    LogCnPibkRepo logRepo;
    @Autowired
    PerusahaanRepo perusahaanRepo;

    @RequestMapping("/drafts")
    public String response(Model model, final Authentication auth){
        model.addAttribute("nosearchbar",0);
        Perusahaan perusahaan = perusahaanRepo.findByPegawais_username(auth.getName());
        List<LogCnPibk> drafts = logRepo.findByIsDraftTrueAndPerusahaanOrderByTglSimpanDesc(perusahaan);
        model.addAttribute("dataDrafts",drafts);
        return "draftIndexPage";
    }

    @RequestMapping("/drafts/edit")
    public String editForm(@RequestParam("id") Integer id, LogCnPibk cnPibk,
                           Model model, BindingResult errors){
        model.addAttribute("nosearchbar",0);
        model.addAttribute("page","HEADER");
        if(null!=id){
            model.addAttribute("cnPibk",logRepo.findOne(id));
        }
        return "formCNPIBKPage";
    }

}
