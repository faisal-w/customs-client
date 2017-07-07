package com.equilibrium.kiriman.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.equilibrium.kiriman.entities.LogCnPibk;
import com.equilibrium.kiriman.entities.LogDetilBarang;
import com.equilibrium.kiriman.entities.LogDetilPungutan;
import com.equilibrium.kiriman.entities.LogHeaderPungutan;
import com.equilibrium.kiriman.entities.Perusahaan;
import com.equilibrium.kiriman.entities.dto.DepositBaruDto;
import com.equilibrium.kiriman.services.KirimDataService;
import com.equilibrium.kiriman.services.repository.LogCnPibkRepo;
import com.equilibrium.kiriman.services.repository.PerusahaanRepo;
import com.equilibrium.kiriman.services.repository.ResponseDataCnRepo;

@Controller
public class MainController {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@Autowired
	KirimDataService kirimDataService;
	@Autowired
	ResponseDataCnRepo responseDataCnRepo;
	@Autowired
	PerusahaanRepo perusahaanRepo;
	@Autowired
	LogCnPibkRepo logCnPibkRepo;

	@RequestMapping("/")
	public String main(Model model, final Authentication auth){

		model.addAttribute("nosearchbar", 1);
		model.addAttribute("deposit", "3.000.000");
		Perusahaan perusahaan = perusahaanRepo.findByPegawais_username(auth.getName());
		//Pageable page = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "waktuCekRespon"));
		//model.addAttribute("dataRespons",responseDataCnRepo.findAll(page));
		model.addAttribute("dataRespons",responseDataCnRepo.findByCnPibkTerkait_perusahaanOrderByWaktuCekResponDesc(perusahaan));
		return "mainPage";
	}
	
	@RequestMapping(value = "/add-deposit", method = RequestMethod.GET)
	public String addDeposit(@ModelAttribute DepositBaruDto depositBaruDto, BindingResult errors, Model model) {
		model.addAttribute("nosearchbar", 1);
		return "addDepositPage";
	}
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("nosearchbar", 1);
		return "adminPage";
	}
	
	@RequestMapping(value = "/form-cnpibk",method = RequestMethod.GET)
	public String formCNPIBK(LogCnPibk cnPibk, BindingResult errors, Model model) {
		model.addAttribute("nosearchbar", 1);
		model.addAttribute("page","HEADER");
		LogCnPibk tempForm = new LogCnPibk();
		tempForm.setHeaderPungutans(new ArrayList<LogHeaderPungutan>());
		tempForm.getHeaderPungutans().add(0,new LogHeaderPungutan());
		tempForm.getHeaderPungutans().add(1,new LogHeaderPungutan());
		tempForm.getHeaderPungutans().add(2,new LogHeaderPungutan());
		tempForm.getHeaderPungutans().add(3,new LogHeaderPungutan());
		model.addAttribute("cnPibk",tempForm);
		return "formCNPIBKPage";
	}

	/*
	@RequestMapping(value = "/form-cnpibk",params = {"addHeaderPungutan"})
	public String addHeaderPungutans(LogCnPibk cnPibk, BindingResult result, Model model){
		model.addAttribute("nosearchbar", 1);
		cnPibk.getHeaderPungutans().add(new LogHeaderPungutan());
		model.addAttribute("page","PUNGUTAN");
		return "formCNPIBKPage";
	}

	@RequestMapping(value = "/form-cnpibk",params = {"remHeaderPungutan"})
	public String removeHeaderPungutans(LogCnPibk cnPibk, BindingResult result, final HttpServletRequest req,Model model){
		model.addAttribute("nosearchbar", 1);
		final Integer hdrId = Integer.valueOf(req.getParameter("remHeaderPungutan"));
		cnPibk.getHeaderPungutans().remove(hdrId.intValue());
		model.addAttribute("page","PUNGUTAN");
		return "formCNPIBKPage";
	}*/

	@RequestMapping(value = "/form-cnpibk",params = {"addDetilBarang"})
	public String addDetilBarang(LogCnPibk cnPibk, BindingResult result, Model model){
		model.addAttribute("nosearchbar", 1);
		//java.util.Random rand = new Random();
		LogDetilBarang tmpDtl = new LogDetilBarang();
		//tmpDtl.setIdLogDetilBarang(rand.nextInt());
		tmpDtl.setDetilPungutans(new ArrayList<LogDetilPungutan>());
		tmpDtl.getDetilPungutans().add(0,new LogDetilPungutan());
		tmpDtl.getDetilPungutans().add(1,new LogDetilPungutan());
		tmpDtl.getDetilPungutans().add(2,new LogDetilPungutan());
		tmpDtl.getDetilPungutans().add(3,new LogDetilPungutan());
		if(null==cnPibk.getDetilBarangs()){
			cnPibk.setDetilBarangs(new ArrayList<LogDetilBarang>());
		}
		cnPibk.getDetilBarangs().add(tmpDtl);
		model.addAttribute("cnPibk",cnPibk);
		model.addAttribute("page","BARANG");
		return "formCNPIBKPage";
	}


	@RequestMapping(value = "/form-cnpibk",params = {"remDetilBarang"})
	public String removeDetilBarang(LogCnPibk cnPibk,BindingResult result,final HttpServletRequest request, Model model){
		model.addAttribute("nosearchbar", 1);
		final Integer brgId = Integer.valueOf(request.getParameter("remDetilBarang"));
		cnPibk.getDetilBarangs().remove(brgId.intValue());
		model.addAttribute("page","BARANG");
		return "formCNPIBKPage";
	}

	/*@RequestMapping(value = "/form-cnpibk",params = {"addDtlPungutan"})
	public String addDetilPungutan(LogCnPibk cnPibk, BindingResult result, final HttpServletRequest request, Model model){
		model.addAttribute("nosearchbar", 1);
		final Integer brgId = Integer.valueOf(request.getParameter("addDtlPungutan"));
		//cnPibk.getDetilBarangs(). get(brgId.intValue()).getDetilPungutans().add(new DetilPungutan());
		model.addAttribute("page","BARANG");
		return "formCNPIBKPage";
	}

	@RequestMapping(value = "/form-cnpibk",params = {"remDtlPungutan"})
	public String removeDetilPungutan(LogCnPibk cnPibk, BindingResult result, final HttpServletRequest request, Model model){
		model.addAttribute("nosearchbar", 1);
		final String brgAndPungutanIds = request.getParameter("remDtlPungutan").toString();
		String[] arrBrgAndPungIds = brgAndPungutanIds.split(",");
		int brgId = 0;
		int pungutanId = 0;
		if(arrBrgAndPungIds[0]!=null) brgId = Integer.valueOf(arrBrgAndPungIds[0]);
		if(arrBrgAndPungIds[1]!=null) pungutanId = Integer.valueOf(arrBrgAndPungIds[1]);
		//cnPibk.getDetilBarangs().get(brgId).getDetilPungutans().remove(pungutanId);
		model.addAttribute("page","BARANG");
		return "formCNPIBKPage";
	}*/

	// PEMROSESAN FORM
	@RequestMapping(value = "/form-cnpibk",method = RequestMethod.POST)
	public Callable<String> processCnpibk(final LogCnPibk cnPibk,
										  BindingResult errors,
										  final Authentication auth,
										  final RedirectAttributes redAtt)
				throws Exception{
		LOGGER.info("Isian CN_PIBK : \n" + cnPibk.toString());
		if(cnPibk.getDetilBarangs()!=null)
			LOGGER.info("Total Barang : " + cnPibk.getDetilBarangs().size());

		Perusahaan perusahaanUser = perusahaanRepo.findByPegawais_username(auth.getName());
		cnPibk.setPerusahaan(perusahaanUser);
		//Buat respon awal dulu!!!
		kirimDataService.catatRespon(50,"Dalam proses pengiriman.",cnPibk.getIdLogCnPibk());

		Callable<String> callable = new Callable<String>() {
			@Override
			public String call () throws Exception {
				logCnPibkRepo.save(cnPibk);
				LOGGER.info("async task no : "+cnPibk.getNoBarang()+" started");
				cnPibk.setDraft(false);
				kirimDataService.kirimDataSatuan(cnPibk);
				LOGGER.info("async task no : "+cnPibk.getNoBarang()+" finished");
				return "redirect:/response";
			}
		};

		redAtt.addAttribute("successMessage","Data dalam proses pengiriman");
		return callable;
	}

	@RequestMapping(value = "/form-cnpibk",params = {"saveDraft"})
	public String saveAsDraft(final LogCnPibk cnPibk, BindingResult errors, final Authentication auth,Model model){
		Perusahaan perusahaanUser = perusahaanRepo.findByPegawais_username(auth.getName());
		cnPibk.setPerusahaan(perusahaanUser);
		model.addAttribute("cnPibk",cnPibk);
		model.addAttribute("page","HEADER");
		cnPibk.setDraft(true);
		logCnPibkRepo.save(cnPibk);
		return "formCNPIBKPage";
	}
	
	@RequestMapping("/history-deposit")
	public String historyDeposit(Model model) {
		model.addAttribute("nosearchbar", 0);
		return "historyDepositPage";
	}
	
	@RequestMapping("/profile")
	public String profile(Model model) {
		model.addAttribute("nosearchbar", 1);
		
		model.addAttribute("namaPerusahaan","PT. ABC");
		model.addAttribute("alamat","Jln. ABC");
		model.addAttribute("npwp","000.00");
		model.addAttribute("noIzin","1234");
		model.addAttribute("tglIzin","10/10/2017");
		
		return "profilePage";
	}

}