package com.shipping.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.backend.config.AppConfiguration;
import com.shipping.backend.config.QueueClient;
import com.shipping.backend.controllers.ShipmentRetrievalController;
import com.shipping.backend.entities.PackageType;
import com.shipping.backend.entities.QueueRequestMessage;
import com.shipping.backend.services.QueueResponseHandler;
import com.shipping.backend.services.QueueResponseHandlerImp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@TestPropertySource(locations = "classpath:application.yml")
public class BackendApplicationTests {

    private  RabbitTemplate rabbitTemplate;
    private  QueueClient shippingRequestSender;
    private  QueueResponseHandler queueResponseHandler;
    private  AppConfiguration appConfiguration;
    private  QueueRequestMessage baseRequestMessage = new QueueRequestMessage();
    private  ObjectMapper mapper;

    @Before
    public void setUp(){
        rabbitTemplate = mock(RabbitTemplate.class);
        shippingRequestSender = new QueueClient(rabbitTemplate);
        appConfiguration = new AppConfiguration();
        mapper = new ObjectMapper();
        queueResponseHandler = new QueueResponseHandlerImp(shippingRequestSender, appConfiguration, mapper);
    }

	@Test
	public void getTypeTest() throws IOException {

	    //Add some mock values for the test request
        baseRequestMessage.setType("packageType");
        PackageType packageTypeResponse1 = new PackageType();

        appConfiguration.setPackageTypes("packageType");

	    //Add some mock values for the test response
        packageTypeResponse1.setId(1);
        packageTypeResponse1.setDescription("Box");
        packageTypeResponse1.setPrice(10);

        PackageType[] typeResponseArray = new PackageType[]{packageTypeResponse1};

        String mockTypes = new ObjectMapper().writeValueAsString(typeResponseArray);
        String mockRequest = new ObjectMapper().writeValueAsString(baseRequestMessage);
        when(rabbitTemplate.convertSendAndReceive(mockRequest)).thenReturn(mockTypes);



	}

}
