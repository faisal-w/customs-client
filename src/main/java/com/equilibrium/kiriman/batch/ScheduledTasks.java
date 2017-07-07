package com.equilibrium.kiriman.batch;

import com.equilibrium.kiriman.services.CekResponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by faisalw on 3/26/17.
 */
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    CekResponService cekResponService;

    @Scheduled(fixedRate = 200000)
    public void timeReport(){
        System.out.println("[Cek Unsent Document] Now: " + dateFormat.format(new Date()));
        cekResponService.cekDanKirimUlangDokBelumTerkirim();
    }

    @Scheduled(initialDelay = 15000, fixedRate = 200000)
    public void initialDelayTimeReport(){
        System.out.println("[Cek document statuses] Now : " + dateFormat.format(new Date()));
        cekResponService.cekSemuaResponTerbaru();
    }

    /*@Scheduled(cron = "*//*5 * * * * *")
    public void cronTimeReport(){
        System.out.println("[cronTimeReport] Now : " + dateFormat.format(new Date()));
    }*/

}
