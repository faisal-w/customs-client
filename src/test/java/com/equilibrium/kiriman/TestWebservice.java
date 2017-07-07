package com.equilibrium.kiriman;

import com.equilibrium.kiriman.wsclients.ClientConfig;
import com.equilibrium.kiriman.wsclients.PrimaryWSClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.client.MockWebServiceServer;

/**
 * Created by faisalw on 3/22/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConfig.class)
public class TestWebservice {

    @Autowired
    private PrimaryWSClient client;

    private MockWebServiceServer mockServer;

    @Before
    public void init(){
        mockServer = MockWebServiceServer.createServer(client);
    }

    @Test
    public void request_response_test() throws Exception{

    }

}
