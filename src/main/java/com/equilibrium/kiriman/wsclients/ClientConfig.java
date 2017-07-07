package com.equilibrium.kiriman.wsclients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by faisalw on 3/18/17.
 */
@Configuration
public class ClientConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientConfig.class);

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.equilibrium.kiriman.wsdl");
        return marshaller;
    }

    @Bean
    public PrimaryWSClient wsClient(Jaxb2Marshaller marshaller){
        PrimaryWSClient client = new PrimaryWSClient();
//        client.setDefaultUri("https://esbbcext01.beacukai.go.id:9082/BarangKirimanPublic/WSBarangKiriman");
        client.setDefaultUri("http://10.241.145.19:8080/BarangKirimanPublic/WSBarangKiriman");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
