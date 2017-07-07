package com.equilibrium.kiriman.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.equilibrium.kiriman.entities.LogCnPibk;
import com.equilibrium.kiriman.entities.LogPekerjaanExcel;
import com.equilibrium.kiriman.entities.Perusahaan;
import com.equilibrium.kiriman.entities.ResponseDataCn;
import com.equilibrium.kiriman.services.repository.LogExcelRepo;
import com.equilibrium.kiriman.services.repository.PerusahaanRepo;
import com.equilibrium.kiriman.services.repository.ResponseDataCnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.equilibrium.kiriman.services.BulkProcessingService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

class ReturnProcessUpload {
	private boolean error = true;
	private boolean done = false;
	private String redirect;
	private String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
}

@Controller
public class ExcelController {
	
	@Autowired
	BulkProcessingService bulkProcessingService;
	@Autowired
	PerusahaanRepo perusahaanRepo;
	@Autowired
	LogExcelRepo logExcelRepo;
	@Autowired
	ResponseDataCnRepo responseDataCnRepo;
	
	@RequestMapping(value = "/upload-excel", method = RequestMethod.GET)
	public String uploadExcel(Model model) {
		String uniqueID = UUID.randomUUID().toString();
		model.addAttribute("file_id", uniqueID);
		return "uploadExcelPage";
	}
	
	@ResponseBody
	@RequestMapping(value = "/upload-excel", method = RequestMethod.POST)
    public ReturnProcessUpload processUpload(
			@RequestParam("file") MultipartFile file,
			@RequestHeader(name="Content-Disposition", required=false) String contentDisposition,
			@RequestHeader(name="Content-Range", required=false) String contentRange,
			@RequestParam("file_id") String fileID,
			final Authentication auth) throws IOException {

		//Get perusahaan by User credential
		Perusahaan perusahaan = perusahaanRepo.findByPegawais_username(auth.getName());

    	ReturnProcessUpload returnProcessUpload = new ReturnProcessUpload();
    	String namaFile = "";
    	String fileName = "";
    	int total = 0;
    	int fromByte = 0;
    	Pattern p;
    	Matcher m;
    	
    	//String tmpDir = System.getProperty("java.io.tmpdir"); // Simpan di dir Temp system
    	String tmpDir = System.getProperty("user.home");
    	
    	if (contentDisposition == null) {
    		fileName = file.getOriginalFilename();
    	}
    	else {
	    	// Ambil Nama File
	    	p = Pattern.compile("filename=\"(.*)\"");
	    	m = p.matcher(contentDisposition);
	    	
	    	if (m.find()) {
	    		fileName = java.net.URLDecoder.decode(m.group(1), "UTF-8");
	    	}
    	}
    	
    	// Konversi nama file menjadi file_id, karena bisa saja ada nama yang sama
    	String ext = "";
		int iE = fileName.lastIndexOf('.');
		int pE = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

		if (iE > pE) {
		    ext = fileName.substring(iE+1);
		}
		
		namaFile = fileID+"."+ext;
		
		System.out.println(namaFile);
    	
    	if (contentRange == null) {
    		total = file.getBytes().length;
    	}
    	else {
	    	// Ambil Total Ukuran File
	    	p = Pattern.compile("([0-9]*)-([0-9]*)/([0-9]*)");
	    	m = p.matcher(contentRange);
	    	
	    	if (m.find()) {
	    		total = Integer.parseInt(m.group(3));
	    		fromByte = Integer.parseInt(m.group(1));
	    	}
    	}

    	// Baru mulai melakukan aksinya kalau ada projectId, file yang dikirim ada namanya, namanya sama dengan file yang dikirim, dan totalnya lebih dari 0
    	if (namaFile.isEmpty() == false && total > 0) {
    		System.out.println("Mulai aksi penyimpanan file");
    		
    		String dirSaveTo = tmpDir + File.separator + "upload-excel-barang-kiriman" + File.separator;
    		System.out.println("Simpan ke folder: "+dirSaveTo);

            //nama file baru
            namaFile = namaFile.trim().toLowerCase().replace(" ","_");
            System.out.println("nama file baru : "+namaFile);

    		String saveTo = dirSaveTo + namaFile;
    		File newFile = new File(saveTo);
    		long currentByte = 0;
    		
    		// Apakah directorynya sudah ada? kalau belum maka buat dulu
    		if (new File(dirSaveTo).exists() == false) {
    			new File(dirSaveTo).mkdirs();
    		}

    		// Kalau ada filenya, kita periksa dulu berapa bytes ini file
    		if (newFile.exists()) {
    			currentByte = newFile.length();
    			
    			// Kalau yang sedang diupload fromByte = 0 dan filenya sudah ada isinya maka hapus
    			System.out.println(newFile.getName()+" = "+currentByte + ":" + fromByte);
    			if (currentByte >= total && fromByte == 0) {
    				System.out.println("Hapus: "+newFile.getName());
    				newFile.delete();
    				newFile.createNewFile();
    			}
    		}
    		else {
    			newFile.createNewFile();
    		}
    		
    		FileOutputStream output = new FileOutputStream(newFile.getAbsolutePath(), true);
    		try {
    		   output.write(file.getBytes());
    		} finally {
    		   output.close();
    		   currentByte = newFile.length();
    		   returnProcessUpload.setError(false);
    		}
    		
    		// Aksi baru dilakukan kalau filenya sudah semuanya diupload
    		if (currentByte >= total) {
    			System.out.println("Semua file sudah tersimpan, saatnya melakukan aksi terhadap file ini");
    			
    			// nama file menggunakan variable "namaFile"
    			// file lengkapnya menggnakan variabel newFile
    			
    			//=========== CONTOH ERROR SAAT VALIDASI AWAL =======================
        		
        		String[] validasi = bulkProcessingService.bacaDanValidasiAwalFileUpload(newFile, perusahaan.getNpwp());
        		
        		if (validasi[0].isEmpty() == false && validasi[1].isEmpty()) {
        			returnProcessUpload.setError(true);
        			returnProcessUpload.setMsg(validasi[0]);
        			
        			return returnProcessUpload;
        		}
        		
        		//=========================================================================
    			
    			// simpan dokumen
    			Integer idPekerjaan = Integer.parseInt(validasi[1]);
    			bulkProcessingService.simpanDokumens(newFile, idPekerjaan);
    			
    			// tampilkan pesan yang keluar dari bulk processing
    			returnProcessUpload.setMsg(validasi[0]);
    			
    			// set mau diredirect kemana saat sudah selesai
    			returnProcessUpload.setRedirect("/excel-status");
    		}
    	}
        
        return returnProcessUpload;
    }

	@RequestMapping(value = "/excel-status")
	public String excelStatus(Model model,final Authentication auth) {
		model.addAttribute("nosearchbar", 0);
		Perusahaan perusahaan = perusahaanRepo.findByPegawais_username(auth.getName());
		model.addAttribute("dataStatus",logExcelRepo.findByNpwpOrderByDateUploadDesc(perusahaan.getNpwp()));
		return "excelStatusPage";
	}

	@RequestMapping(value = "/excel-kirim")
	public Callable<String> kirimExcel(@RequestParam("id") final Integer id, Model model, final RedirectAttributes redAtt){
		LogPekerjaanExcel statusExcel = logExcelRepo.findOne(id);
		statusExcel.setKeterangan("Dalam proses pengiriman, tunggu respon");
		statusExcel.setSent(true);
		statusExcel.setDateKirim(new Date());
		logExcelRepo.save(statusExcel);

		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				bulkProcessingService.kirimDokumen(id);
				return "redirect:/excel-status";
			}
		};

		redAtt.addAttribute("successMessage","Data anda sudah dalam pengiriman");
		return callable;
	}

	@RequestMapping(value = "/excel-respons")
	public String responsExcel(@RequestParam("id") final Integer id, Model model, final Authentication auth){
		List<LogCnPibk> cnPibkList = null;
		if(null!=id)
			cnPibkList = logExcelRepo.findOne(id).getLogCnPibks();
		System.out.println("isian CN : "+cnPibkList.size());
		//List<Integer> idsCn = new ArrayList<Integer>();
		List<ResponseDataCn> responses = null;
		if(cnPibkList.size()>0)
			responses = responseDataCnRepo.findByCnPibkTerkaitInOrderByWaktuCekResponDesc(cnPibkList);
		model.addAttribute("dataRespons",responses);
		return "responExcelPage";
	}
}
