package com.equilibrium.kiriman.services.impl;

import com.equilibrium.kiriman.entities.*;
import com.equilibrium.kiriman.entities.dto.*;
import com.equilibrium.kiriman.services.ArgoBillingService;
import com.equilibrium.kiriman.services.CekResponService;
import com.equilibrium.kiriman.services.KirimDataService;
import com.equilibrium.kiriman.services.repository.LogCnPibkRepo;
import com.equilibrium.kiriman.services.repository.PerusahaanRepo;
import com.equilibrium.kiriman.services.repository.ResponseDataCnRepo;
import com.equilibrium.kiriman.wsclients.PrimaryWSClient;
import com.equilibrium.kiriman.wsdl.GetResponByAwbResponse;
import com.equilibrium.kiriman.wsdl.RequestResponResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by faisalw on 3/19/17.
 */
@Service("cekResponService")
public class CekResponServiceImpl implements CekResponService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CekResponServiceImpl.class);

    @Autowired
    ResponseDataCnRepo responseDataCnRepo;
    @Autowired
    PrimaryWSClient wsClient;
    @Autowired
    KirimDataService kirimDataService;
    @Autowired
    PerusahaanRepo perusahaanRepo;
    @Autowired
    LogCnPibkRepo logCnPibkRepo;
    @Autowired
    ArgoBillingService argoService;
    //@Autowired
    //HttpServletRequest httpRequest;
    //@Value("#{servletContext.contextPath}")
    //private String servletContextPath;

    //Belum kepake
    /*
    @Override
    public void cekAndUpdateResponSentDocuments() {
        //1. Ambil semua yang status responnya belum tutup/complete, urutkan berdasar tanggal terlama
        //2. For each dokumen, cek ke server BC dengan web service, ambil respon status
        //3. Marshal xml respon status ke java object, save ke DB untuk status
        //4. Jika status penting atau ada kesalahan, notify user

        List<Integer> codes = Arrays.asList(404,205,311);//DUMMY CODEs
        List<ResponseDataCn> doksNotFinished = responseDataCnRepo.findDokumensByKodeNotIn(codes);
        for (ResponseDataCn dataCn:doksNotFinished) {
            //Step 2
            CekStatus hasilCekStatus = null;
            try{
                hasilCekStatus = this.cekStatusWithWsclient(
                        "userdemo^$123456",
                        "TVRJek5EVTJOemc1TFRFMw0K",
                        "123456789012345",
                        dataCn.getCnPibkTerkait().getNoBarang(),
                        new Date());
            }catch (Exception e){
                System.out.println("CekAndUpdateResponByPerusahaan error : \n");
                e.printStackTrace();
            }
            if(null!=hasilCekStatus){
                if(hasilCekStatus.getKdRespon().toLowerCase()!="err"){
                    int kodeRespon = new Integer(hasilCekStatus.getKdRespon());
                    dataCn.setKodeRespon(new Integer(hasilCekStatus.getKdRespon()));
                    dataCn.setKeterangan(hasilCekStatus.getKetRespon());
                    dataCn.setWaktuCekRespon(new Date());
                    responseDataCnRepo.save(dataCn);
                }else{
                    System.out.println("Cek status ERROR, ada yang salah pada param : \n");
                    System.out.println(hasilCekStatus.getKetRespon());
                }
            }
        }

        return;
    }
    */

    @Override
    public void cekDanKirimUlangDokBelumTerkirim() {
        List<ResponseDataCn> cnBelumTerkirim = responseDataCnRepo.findDokumensNotTerkirim(60);
        for (ResponseDataCn respCn : cnBelumTerkirim){
            kirimUlangDokWithWS(respCn.getCnPibkTerkait(),respCn.getIdResponse());
            //update status respon!
        }
    }

    @Transactional
    void kirimUlangDokWithWS(LogCnPibk cnPibk,int idRespon){
        //ambil data log, kirim ulang WS ke server bc, terima respon
        CnPibk dataCn = new CnPibk(cnPibk);
        List<HeaderPungutan> headerPungutans = new ArrayList<HeaderPungutan>();
        if(null!=cnPibk.getHeaderPungutans()){
            cnPibk.getHeaderPungutans().size();
            for (LogHeaderPungutan dbHp : cnPibk.getHeaderPungutans() ) {
                HeaderPungutan tempHp = new HeaderPungutan();
                tempHp.setKdPungutan(dbHp.getKdPungutan());
                tempHp.setNilai(dbHp.getNilai());
                headerPungutans.add(tempHp);
            }
        }
        dataCn.setHeaderPungutans(headerPungutans);
        List<DetilBarang> detilBarangs = new ArrayList<DetilBarang>();
        if(null!=cnPibk.getDetilBarangs()){
	        cnPibk.getDetilBarangs().size();
            for (LogDetilBarang dbBrg: cnPibk.getDetilBarangs()) {
                DetilBarang tempBrg = new DetilBarang(dbBrg);
                List<DetilPungutan> detilPungutans = new ArrayList<DetilPungutan>();
                if(null!=dbBrg.getDetilPungutans()){
                    dbBrg.getDetilPungutans().size();
                            for (LogDetilPungutan dbDtlPung: dbBrg.getDetilPungutans()) {
                            DetilPungutan tempDtlPung = new DetilPungutan(dbDtlPung);
                            detilPungutans.add(tempDtlPung);
                            }
                }
                tempBrg.setDetilPungutans(detilPungutans);
                detilBarangs.add(tempBrg);
            }
	    }
        dataCn.setDetilBarangs(detilBarangs);
        kirimDataService.kirimDataUlang(dataCn,cnPibk,idRespon);
        return;
    }

    CekStatus cekStatusWithWsclient(String id,String sign,String npwp,String noBarang,Date tglHouseblawb) throws Exception{
        JAXBContext jaxbContext = JAXBContext.newInstance(CekStatus.class);
        CekStatus cekStatus = new CekStatus();
        HeaderCekStatus headerCekStatus = new HeaderCekStatus();
        headerCekStatus.setNpwp(npwp);
        headerCekStatus.setNoBarang(noBarang);
        headerCekStatus.setTglHouseBlawb(tglHouseblawb);
        cekStatus.setHeader(headerCekStatus);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(cekStatus,sw);
        System.out.println("XML : " + sw.toString());
        GetResponByAwbResponse response = null;
        try {
            response = wsClient.getResponByAwb(
                     sw.toString(),
                    "userdemo^$123456", //id
                    "TVRJek5EVTJOemc1TFRFMw0K" //sign
            );
        }catch (Exception e){
            System.err.println("Cek Status Error : \n");
            e.printStackTrace();
        }
        CekStatus cekStatusResponse = null;
        if(null!= response){
            StringReader stringReader = new StringReader(response.getReturn().trim());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            cekStatusResponse = (CekStatus) unmarshaller.unmarshal(stringReader);
        }
        return cekStatusResponse;
    }

    @Override //REAL SERVICE
    public void cekSemuaResponTerbaru() {
        LOGGER.info("Mulai cek semua respon by NPWP ....");
        List<Perusahaan> perusahaanAktifs = perusahaanRepo.findByIsAktifTrue();
        for (Perusahaan perusahaan:perusahaanAktifs) {
            try {
                RequestResponResponse res = wsClient.getAllResponsByNpwp(perusahaan.getNpwp(),
                                                                        perusahaan.getUserWSBeaCukai()+"^$"+perusahaan.getPassWSBeaCukai(),
                                                                        perusahaan.getTokenWSBeaCukai());
                LOGGER.info("respon : "+res+" \n "+res.getReturn().toString());
                if(null!=res)
                    if(!res.getReturn().isEmpty()) {
                        ParentCekStatus hslCek = this.decodeResponCekStatus(res.getReturn().toString().trim());
//                        ParentCekStatus hslCek = this.decodeResponCekStatus("");
                        if (null != hslCek)
                            if (hslCek.getHeader().size() > 0) {
                                LOGGER.info("Jumlah respon untuk NPWP "+perusahaan.getNpwp()+" : "+hslCek.getHeader().size());
                                for (HeaderChildGetSemuaRespon heder : hslCek.getHeader()) {
                                    System.out.println("header : "+heder.getNoBarang());
                                    ResponseDataCn tempRes = new ResponseDataCn();
                                    if (heder.getKdRespon().toUpperCase() != "ERR") {
                                        tempRes.setKodeRespon(new Integer(heder.getKdRespon()));
                                        if(heder.getKdRespon().equals("203")){
                                            // Bagian argo billing
                                            if(perusahaan.getJenisDeposit()!=null) //dok or kg
                                                if(perusahaan.getJenisDeposit().toLowerCase().equals("dok")){
                                                    argoService.potongKuotaPerDokumen(perusahaan,heder.getNoBarang(),heder.getTglHouseBlawb());
                                                }else{
                                                    argoService.potongKuotaPerKilo(perusahaan,heder.getNoBarang(),heder.getTglHouseBlawb());
                                                }
                                        }
                                    } else {
                                        tempRes.setKodeRespon(60);
                                    }
                                    tempRes.setKeterangan(heder.getKetRespon());
                                    tempRes.setWaktuCekRespon(new Date());
                                    tempRes.setCnPibkTerkait(logCnPibkRepo.findByNoBarangAndTglHouseBlawb(heder.getNoBarang(), heder.getTglHouseBlawb()));//check if working or not

                                    if((heder.getNoSpbl()!=null)||(heder.getNoPibk()!=null)||(heder.getNoSppbmcp()!=null)||
                                       (heder.getNoSppb()!=null)||(heder.getNoSptnp()!=null)||(heder.getKodeBilling()!=null)) {
                                        System.out.println("buat detil respon...");
                                        DetailResponDataCn tempDetRes = new DetailResponDataCn();
                                        tempDetRes.setNdpbm(heder.getNdpbm());
                                        if (heder.getNoPibk() != null) {
                                            System.out.println("ada no pibk");
                                            tempDetRes.setNoPibk(heder.getNoPibk());
                                            tempDetRes.setTglPibk(heder.getTglPibk());
                                        }
                                        if (heder.getNoSpbl() != null) {
                                            tempDetRes.setNoSpbl(heder.getNoSpbl());
                                            tempDetRes.setTglSpbl(heder.getTglSpbl());
                                            if (null != heder.getDetilLartas())
                                                if (heder.getDetilLartas().size() > 0) {
                                                    List<DetilLartasResponDataCn> detilLartasses = new ArrayList<DetilLartasResponDataCn>();
                                                    for (ChildDetilLartas detilLartas : heder.getDetilLartas()) {
                                                        DetilLartasResponDataCn newDtlLartas = new DetilLartasResponDataCn();
                                                        newDtlLartas.setDetailRespon(tempDetRes);
                                                        newDtlLartas.setSeriBrg(detilLartas.getSeriBrg());
                                                        newDtlLartas.setUrBrg(detilLartas.getUrBrg());
                                                        newDtlLartas.setKetLartas(detilLartas.getKetLartas());
                                                        detilLartasses.add(newDtlLartas);
                                                    }
                                                    tempDetRes.setDetilLartasses(detilLartasses);
                                                }
                                        }
                                        if (heder.getKodeBilling() != null) {
                                            System.out.println("ada billing...");
                                            tempDetRes.setKodeBilling(heder.getKodeBilling());
                                            tempDetRes.setTotalBilling(heder.getTotalBilling());
                                            tempDetRes.setKdDokBilling(heder.getKdDokBilling());
                                            tempDetRes.setTglJtTempo(heder.getTglJtTempo());
                                        }
                                        if (heder.getNoSppb() != null) {
                                            System.out.println("ada sppb...");
                                            tempDetRes.setNoSppb(heder.getNoSppb());
                                            tempDetRes.setTglSppb(heder.getTglSppb());
                                        }
                                        tempDetRes.setNoSppbmcp(heder.getNoSppbmcp());
                                        tempDetRes.setTglSppbmcp(heder.getTglSppbmcp());
                                        tempDetRes.setNoSptnp(heder.getNoSptnp());
                                        tempDetRes.setTglSptnp(heder.getTglSptnp());
                                        tempRes.setDetailRespon(tempDetRes);
                                    }

                                    if (null != heder.getPdf()) {
                                        if (heder.getPdf().length() > 0) {
                                            LOGGER.info("Ada file pdf...");
                                            BASE64Decoder decoder = new BASE64Decoder();
                                            byte[] decodedBytes = decoder.decodeBuffer(heder.getPdf());
                                            //Need to write the file ELSEWHERE!
                                            if (Files.notExists(Paths.get("../respons_pdf")))
                                                Files.createDirectory(Paths.get("../respons_pdf"));
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                            String tempDirPerusahaan =  perusahaan.getNpwp() + "/" + sdf.format(new Date());
                                            if (Files.notExists(Paths.get("../respons_pdf/" + tempDirPerusahaan)))
                                                Files.createDirectories(Paths.get("../respons_pdf/" + tempDirPerusahaan));
                                            String partFilename = "respon_" + heder.getNoBarang() + "_" + heder.getKdRespon() + "_" + new Date().getTime() + ".pdf";
                                            String compFileName = "../respons_pdf/" + tempDirPerusahaan + "/" + partFilename;
                                            FileOutputStream fos = new FileOutputStream(compFileName);
                                            DataOutputStream os = new DataOutputStream(fos);
                                            os.write(decodedBytes);
                                            os.close();
                                            //String baseUrl = InetAddress.getLocalHost().getCanonicalHostName();
                                            System.out.println("file pdf : "+heder.getPdf().substring(0,10));
                                            if(tempRes.getDetailRespon()!=null)
                                                tempRes.getDetailRespon().setFilePdf(tempDirPerusahaan + "/" + partFilename);
                                        }
                                    }
                                    //Save detail respon ke DB
                                    responseDataCnRepo.save(tempRes);
                                    responseDataCnRepo.flush();
                                }
                            }
                    }
            }catch (Exception e){
                LOGGER.error("Cek perusahaan "+perusahaan.getNamaPerusahaan()+" error.\n");
                e.printStackTrace();
            }
        }

    }

    ParentCekStatus decodeResponCekStatus(String str) {
        StringReader strRd;
        ParentCekStatus hasil = null;
//        System.out.println("Tes cardig");
//        String tempStr = this.readFile("responcontohdrcardig.txt",Charset.defaultCharset());
        strRd = new StringReader(str);
        try{
            JAXBContext jxbCntxtStatus = JAXBContext.newInstance(ParentCekStatus.class);
            Unmarshaller unmarshaller = jxbCntxtStatus.createUnmarshaller();
            hasil = (ParentCekStatus) unmarshaller.unmarshal(strRd);
            //HeaderChildGetSemuaRespon conto1 = hasil.getHeader().get(0);
            //System.out.println(conto1.getNoBarang());
        }catch (JAXBException e){
            System.out.println("Gagal unmarshall respon dengan sumber:\n" + str);
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Gagal unmarshall respon dengan sumber:\n" + str);
            e.printStackTrace();
        }
        return hasil;
    }

    //used for reading xml example, not to write pdf
    static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
