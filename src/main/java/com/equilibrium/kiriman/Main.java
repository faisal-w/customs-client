package com.equilibrium.kiriman;

import com.equilibrium.kiriman.entities.dto.*;
import com.equilibrium.kiriman.services.BulkProcessingService;
import com.equilibrium.kiriman.services.KirimDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by faisalw on 3/5/17.
 */
@SpringBootApplication
@EntityScan(basePackages = "com.equilibrium.kiriman.entities")
@EnableAsync
@EnableScheduling
@EnableWebMvc
@EnableAutoConfiguration
public class Main{

    @Autowired
    static BulkProcessingService testService;

    public static void main(String[] args) throws Exception{
        ApplicationContext context = SpringApplication.run(Main.class, args);

        /*
        testService = (BulkProcessingService) context.getBean("bulkProcessingService");

        File file = new File("HEADER_contoh_1.xls");
        System.out.println(file.exists());
        System.out.println(file);
        System.out.println(testService);
        String[] res = testService.bacaDanValidasiAwalFileUpload(file,"123456789");
        System.out.println(res[0]);
        if(null!=res[1])
            testService.simpanDokumens(file,Integer.parseInt(res[1]));
            */
    }


}
