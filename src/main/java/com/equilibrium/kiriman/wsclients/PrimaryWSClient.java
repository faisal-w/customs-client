package com.equilibrium.kiriman.wsclients;

import com.equilibrium.kiriman.entities.dto.CekStatus;
import com.equilibrium.kiriman.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 * Created by faisalw on 3/18/17.
 */
public class PrimaryWSClient extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimaryWSClient.class);

    @Value("${webservice.url}")
    private String webserviceUrl;

    // Get status per NO-BARANG
    public GetResponByAwbResponse getResponByAwb(String data, String id, String sign) throws Exception{
        ObjectFactory objectFactory = new ObjectFactory();
        GetResponByAwb request = objectFactory.createGetResponByAwb();
        request.setData(data);
        request.setId(id);
        request.setSign(sign);
        LOGGER.info("Requesting info for id : "+id);

        Object response = getWebServiceTemplate()
                .marshalSendAndReceive(
                        webserviceUrl,
                        //"http://10.241.145.19:8080/BarangKirimanPublic/WSBarangKiriman",
                        //"http://10.241.145.19:8080/barangkiriman",
                        //"https://esbbcext01.beacukai.go.id:9082/BarangKirimanOnline/WSBarangKiriman",
                        request
                );
        GetResponByAwbResponse tempResponse = (GetResponByAwbResponse) response;
        //System.out.println("RESULT : "+tempResponse.getReturn());
        return tempResponse;
    }

    public KirimDataResponse kirimDataUtama(String xmlCnPibk, String id, String sign) throws Exception{
        ObjectFactory objectFactory = new ObjectFactory();
        KirimData request = objectFactory.createKirimData();
        request.setData(xmlCnPibk);
        request.setId(id);
        request.setSign(sign);
        LOGGER.info("Send data CN ke server : "+webserviceUrl);

         Object response = getWebServiceTemplate()
                .marshalSendAndReceive(
                    webserviceUrl,
                    //"http://10.241.145.19:8080/BarangKirimanPublic/WSBarangKiriman",
//                    "https://esbbcext01.beacukai.go.id:9082/BarangKirimanOnline/WSBarangKiriman",
                    request
                );
        KirimDataResponse tempResponse = (KirimDataResponse) response;
        System.out.println("Respons : " + tempResponse.getReturn());
        return tempResponse;
    }

    // Get status by NPWP, all documents!
    public RequestResponResponse getAllResponsByNpwp(String npwp, String id, String sign) throws Exception {
        ObjectFactory objectFactory = new ObjectFactory();
        RequestRespon request = objectFactory.createRequestRespon();
        request.setNpwp(npwp);
        request.setId(id);
        request.setSign(sign);
        LOGGER.info("Get respon for NPWP : "+npwp);

        RequestResponResponse response = (RequestResponResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        webserviceUrl,
                        //"http://10.241.145.19:8080/BarangKirimanPublic/WSBarangKiriman",
                         //"https://esbbcext01.beacukai.go.id:9082/BarangKirimanOnline/WSBarangKiriman",
                        request

                );
        return response;
    }

}
