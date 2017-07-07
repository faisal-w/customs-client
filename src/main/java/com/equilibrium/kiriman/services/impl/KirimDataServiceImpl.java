package com.equilibrium.kiriman.services.impl;

import com.equilibrium.kiriman.entities.*;
import com.equilibrium.kiriman.entities.dto.*;
import com.equilibrium.kiriman.services.KirimDataService;
import com.equilibrium.kiriman.services.PerusahaanAndUserService;
import com.equilibrium.kiriman.services.repository.HeaderPungutanRepo;
import com.equilibrium.kiriman.services.repository.LogCnPibkRepo;
import com.equilibrium.kiriman.services.repository.ResponseDataCnRepo;
import com.equilibrium.kiriman.wsclients.PrimaryWSClient;
import com.equilibrium.kiriman.wsdl.KirimDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by faisalw on 3/19/17.
 */
@Service("kirimDataService")
public class KirimDataServiceImpl implements KirimDataService {

    @Autowired
    LogCnPibkRepo cnRepo;
    @Autowired
    HeaderPungutanRepo hpRepo;
    @Autowired
    PrimaryWSClient wsClient;
    @Autowired
    ResponseDataCnRepo responseDataCnRepo;
    @Autowired
    PerusahaanAndUserService perusahaanAndUserService;

    @Value("${webservice.userpassdemo}")
    private String userPassDemo;
    @Value("${webservice.tokendemo}")
    private String tokenDemo;

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimaryWSClient.class);

    @Override
    @Async
    public Callable<Void> kirimDataSatuan(LogCnPibk cnPibk) {
        String userAndPassWS, tokenWS;
        //Perusahaan perusahaanUser = perusahaanAndUserService.getLoggedInUserPerusahaan(username);
        Perusahaan perusahaanUser = cnPibk.getPerusahaan();
        if(null!=perusahaanUser){
            userAndPassWS = perusahaanUser.getUserWSBeaCukai()+"^$"+perusahaanUser.getPassWSBeaCukai();
            tokenWS = perusahaanUser.getTokenWSBeaCukai();
        }else{
            userAndPassWS = userPassDemo;
            tokenWS = tokenDemo;
        }
        System.out.println("User and Token : "+userPassDemo+" -- "+tokenDemo);

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){}

        //1.Validasi data (atau di controller saja?)

        //2.Simpan Log ke DB
        System.out.println("Mulai masuk service");
        //LogCnPibk newCnPibk = new LogCnPibk(cnPibk);
        //newCnPibk.setPerusahaan(perusahaanUser);
        //newCnPibk.setIdLogCnPibk(1);
        //List<LogHeaderPungutan> entPungs = new ArrayList<>();
        //int idPung = 1;
        /*if(null!=cnPibk.getHeaderPungutans()) {
            for (HeaderPungutan pung : cnPibk.getHeaderPungutans()) {
                LogHeaderPungutan logPung = new LogHeaderPungutan();
                logPung.setKdPungutan(pung.getKdPungutan());
                logPung.setNilai(pung.getNilai());
                //logPung.setIdLogHeaderPungutan(idPung);
                entPungs.add(logPung);
                //idPung += 1;
            }
        }*/
        /*newCnPibk.setHeaderPungutans(entPungs);
        List<LogDetilBarang> entDetilBrg = new ArrayList<>();
        //int id=1;
        for(DetilBarang brg : cnPibk.getDetilBarangs()){
            LogDetilBarang newDtlBrg = new LogDetilBarang(brg);
            //newDtlBrg.setIdLogDetilBarang(id);
            List<LogDetilPungutan> dtlPungs = new ArrayList<>();
            if(null!=brg.getDetilPungutans())
                for(DetilPungutan detPng : brg.getDetilPungutans()){
                    LogDetilPungutan ldp = new LogDetilPungutan(detPng);
                    //ldp.setIdLogDetilPungutan(id);
                    //ldp.setTarif(222.0);
                    ldp.setBarang(newDtlBrg);
                    dtlPungs.add(ldp);
                }
            newDtlBrg.setDetilPungutans(dtlPungs);
            newDtlBrg.setCnPibk(newCnPibk);
            entDetilBrg.add(newDtlBrg);
            //id += 1;
        }
        newCnPibk.setDetilBarangs(entDetilBrg);
        cnRepo.saveAndFlush(newCnPibk);*/

        //Catat respon awal : "Dalam proses"
        //Callable<Void> catres = this.catatRespon(50,"Dalam proses pengiriman.",cnPibk.getIdLogCnPibk());

        //Fetch dari Log di DB
        TempHeaderCnPibk hdrCn = new TempHeaderCnPibk();
        CnPibk xmlCnPibk = new CnPibk(cnPibk);
        List<HeaderPungutan> xmlHdrPungs = new ArrayList<HeaderPungutan>();
        for (LogHeaderPungutan logHdrPung: cnPibk.getHeaderPungutans()) {
            HeaderPungutan xmlHdrPung = new HeaderPungutan();
            xmlHdrPung.setKdPungutan(logHdrPung.getKdPungutan());
            xmlHdrPung.setNilai(logHdrPung.getNilai());
            xmlHdrPungs.add(xmlHdrPung);
        }
        xmlCnPibk.setHeaderPungutans(xmlHdrPungs);
        List<DetilBarang> xmlDtlBrgs = new ArrayList<DetilBarang>();
        for (LogDetilBarang logDtlBrg:cnPibk.getDetilBarangs()) {
            DetilBarang dtlBrg = new DetilBarang(logDtlBrg);
            List<DetilPungutan> dtlPungs = new ArrayList<DetilPungutan>();
            for(LogDetilPungutan ldp : logDtlBrg.getDetilPungutans()){
                DetilPungutan dtlPung = new DetilPungutan(ldp);
                dtlPungs.add(dtlPung);
            }
            dtlBrg.setDetilPungutans(dtlPungs);
            xmlDtlBrgs.add(dtlBrg);
        }
        xmlCnPibk.setDetilBarangs(xmlDtlBrgs);
        hdrCn.setCnPibk(xmlCnPibk);

        //3.Kirim ke server Web Service BC
        ResponCnPibk responKirim = null;
        KirimDataResponse respon = null;
        String tempRespon=null;
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(TempHeaderCnPibk.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            StringWriter sw = new StringWriter();
            marshaller.marshal(hdrCn, sw);
            String xmlStringCnPibk = sw.toString();
            LOGGER.info("hasil xml : "+xmlStringCnPibk);
            try {
                respon = wsClient.kirimDataUtama(
                        xmlStringCnPibk,
                        userAndPassWS,
                        tokenWS
                );
                LOGGER.info("respon : " + respon.getReturn());
                StringReader strRd = new StringReader(respon.getReturn().trim());
                JAXBContext jxbCntxtStatus = JAXBContext.newInstance(ResponCnPibk.class);
                Unmarshaller unmarshaller = jxbCntxtStatus.createUnmarshaller();
                responKirim = (ResponCnPibk) unmarshaller.unmarshal(strRd);

                //CEK TERKIRIM ATAU TIDAK!!!
            }catch (org.springframework.ws.client.WebServiceIOException e) {
                tempRespon = "Tidak berhasil connect ke server. Time out.";
                LOGGER.info(tempRespon);
            }catch (javax.xml.bind.UnmarshalException e){
                LOGGER.info("Gagal UNMARSHALL.");
                e.printStackTrace();
                // gagal unmarshal!!!
            }catch (Exception e){
                tempRespon = "Kirim data untuk id "+cnPibk.getNoHouseBlawb()+" tidak berhasil, ERROR.";
                LOGGER.error(tempRespon);
                e.printStackTrace();
            }
        }catch (Exception e){
            LOGGER.error("Marshalling error.");
            e.printStackTrace();
        }

        if(null==responKirim){
            tempRespon = tempRespon + "\n" + (null!=respon ? respon.getReturn().toString() : "");
            this.catatRespon(60,tempRespon,cnPibk.getIdLogCnPibk());
        }else {
            System.out.println("Kode respon : "+responKirim.getTempHeader().getKdRespon()+"\nKet Respon : "+responKirim.getTempHeader().getKetRespon());
            if(responKirim.getTempHeader().getKdRespon().equals("ERR")){
                this.catatRespon(65,responKirim.getTempHeader().getKetRespon(),cnPibk.getIdLogCnPibk());
            }else{
                this.catatRespon(new Integer(responKirim.getTempHeader().getKdRespon()),responKirim.getTempHeader().getKetRespon(),cnPibk.getIdLogCnPibk());
            }
        }

        //return catres;
        return null;
    }

    @Async
    @Transactional
    @Override
    public Callable<Void> catatRespon(int kodeRespon,String keteranganRespon,int idCnPibk){
        // Update status obtained from KirimDataResponse
        System.out.println("Masuk catat respon");
        ResponseDataCn logRespon = new ResponseDataCn();
        logRespon.setWaktuCekRespon(new Date());
        logRespon.setKodeRespon(kodeRespon);
        logRespon.setKeterangan(keteranganRespon);
        logRespon.setCnPibkTerkait(cnRepo.findOne(idCnPibk));
        responseDataCnRepo.saveAndFlush(logRespon);
        responseDataCnRepo.flush();
        return null;
    }

    @Transactional
    public void kirimDataUlang(CnPibk cnPibk,LogCnPibk logCnPibk,int idRespon) {
        TempHeaderCnPibk hdrCn = new TempHeaderCnPibk();
        hdrCn.setCnPibk(cnPibk);
        KirimDataResponse respon = null;
        ResponCnPibk responKirim = null;
        String tempRespon=null;
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(TempHeaderCnPibk.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            StringWriter sw = new StringWriter();
            marshaller.marshal(hdrCn, sw);
            String xmlStringCnPibk = sw.toString();
            try{
                respon = wsClient.kirimDataUtama(
                        xmlStringCnPibk,
                        logCnPibk.getPerusahaan().getUserWSBeaCukai()+"^$"
                                +logCnPibk.getPerusahaan().getPassWSBeaCukai(),
                        //"userdemo^$123456",
                        logCnPibk.getPerusahaan().getTokenWSBeaCukai()
                        //"TVRJek5EVTJOemc1TFRFMw0K"
                );
                LOGGER.info("respon : " + respon);
                StringReader strRd = new StringReader(respon.getReturn().trim());
                JAXBContext jxbCntxtStatus = JAXBContext.newInstance(ResponCnPibk.class);
                Unmarshaller unmarshaller = jxbCntxtStatus.createUnmarshaller();
                // If terkirim, catat
                responKirim = (ResponCnPibk) unmarshaller.unmarshal(strRd);
                // If tidak terkirim langsung throws exception
            }catch (org.springframework.ws.client.WebServiceIOException e) {
                tempRespon = "Kirim ulang tidak berhasil.";
                LOGGER.error(tempRespon);
                e.printStackTrace();
            }catch (Exception e){
                tempRespon = "Kirim ulang tidak berhasil.";
                LOGGER.error(tempRespon);
                e.printStackTrace();
            }
        }catch (Exception e){
            LOGGER.error("Marshalling error.");
            e.printStackTrace();
        }

        // Update status obtained from KirimDataResponse
        if(null==responKirim){
            tempRespon = tempRespon + "\n" + (null!=respon ? respon.getReturn().toString() : "");
            //this.catatRespon(60,tempRespon,logCnPibk.getIdLogCnPibk());
            //update saja
            ResponseDataCn updateRespon = responseDataCnRepo.getOne(idRespon);
            updateRespon.setWaktuCekRespon(new Date());
            updateRespon.setKeterangan(tempRespon);
            responseDataCnRepo.saveAndFlush(updateRespon);
        }else {
            LOGGER.info("Kode respon : "+responKirim.getTempHeader().getKdRespon()+"\nKet Respon : "+responKirim.getTempHeader().getKetRespon());
            if(responKirim.getTempHeader().getKdRespon().equals("ERR")){
                this.catatRespon(65,responKirim.getTempHeader().getKetRespon(),logCnPibk.getIdLogCnPibk());
            }else{
                this.catatRespon(new Integer(responKirim.getTempHeader().getKdRespon()),responKirim.getTempHeader().getKetRespon(),logCnPibk.getIdLogCnPibk());
                responseDataCnRepo.delete(idRespon);
                responseDataCnRepo.flush();
            }
        }
    }

    @Override
    public void kirimDataBulk() {

    }

}
