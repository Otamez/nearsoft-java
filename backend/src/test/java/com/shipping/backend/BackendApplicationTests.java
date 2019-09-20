package com.shipping.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.backend.config.AppConfiguration;
import com.shipping.backend.config.QueueClient;
import com.shipping.backend.controllers.ShipmentRetrievalController;
import com.shipping.backend.entities.PackageType;
import com.shipping.backend.entities.QueueRequestMessage;
import com.shipping.backend.services.QueueResponseHandler;
import com.shipping.backend.services.QueueResponseHandlerImp;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BackendApplicationTests {

    private static RabbitTemplate rabbitTemplate;
    private static QueueClient shippingRequestSender;
    private static QueueResponseHandler queueResponseHandler;
    private static ShipmentRetrievalController shippingRetrivalServiceController;
    private static AppConfiguration appConfiguration;
    private static QueueRequestMessage baseRequestMessage = new QueueRequestMessage();

    @BeforeClass
    public static void setUp(){
        rabbitTemplate = mock(RabbitTemplate.class);
        shippingRequestSender = new QueueClient(rabbitTemplate);
        appConfiguration = new AppConfiguration();
        queueResponseHandler = new QueueResponseHandlerImp(shippingRequestSender, appConfiguration);
        shippingRetrivalServiceController = new ShipmentRetrievalController(queueResponseHandler);
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

		List<String> types = shippingRetrivalServiceController.getTypes();



	}

}
