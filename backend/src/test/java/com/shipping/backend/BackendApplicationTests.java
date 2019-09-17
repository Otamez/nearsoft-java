package com.shipping.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.backend.config.AppConfig;
import com.shipping.backend.controllers.ShippingRetrivalServiceController;
import com.shipping.backend.entities.BaseRequestMessage;
import com.shipping.backend.entities.PackageTypeResponse;
import com.shipping.backend.services.ShippingQueue;
import com.shipping.backend.services.ShippingQueueImpl;
import com.shipping.backend.services.ShippingRetrivalService;
import com.shipping.backend.services.ShippingRetrivalServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class BackendApplicationTests {

    private static RabbitTemplate rabbitTemplate;
    private static ShippingQueue shippingRequestSender;
    private static ShippingRetrivalService shippingRetrivalService;
    private static ShippingRetrivalServiceController shippingRetrivalServiceController;
    private static BaseRequestMessage baseRequestMessage = new BaseRequestMessage();
    private static ObjectMapper mapper = new ObjectMapper();
    private static AppConfig appConfig = new AppConfig();

    @BeforeClass
    public static void setUp(){
        rabbitTemplate = mock(RabbitTemplate.class);
        shippingRequestSender = new ShippingQueueImpl(rabbitTemplate);
        shippingRetrivalService = new ShippingRetrivalServiceImpl(shippingRequestSender, mapper, appConfig);
        shippingRetrivalServiceController = new ShippingRetrivalServiceController(shippingRetrivalService);
    }

	@Test
	public void getTypeTest() throws IOException {

	    //Add some mock values for the test request
        baseRequestMessage.setType("packageType");
        PackageTypeResponse packageTypeResponse1 = new PackageTypeResponse();

        appConfig.setPackageTypes(baseRequestMessage.getType());

	    //Add some mock values for the test response
        packageTypeResponse1.setId(1);
        packageTypeResponse1.setDescription("Box");
        packageTypeResponse1.setPrice(10);

        PackageTypeResponse[] typeResponseArray = new PackageTypeResponse[]{packageTypeResponse1};

        String mockTypes = new ObjectMapper().writeValueAsString(typeResponseArray);
        String mockRequest = new ObjectMapper().writeValueAsString(baseRequestMessage);
        when(rabbitTemplate.convertSendAndReceive(mockRequest)).thenReturn(mockTypes);

		List<String> types = shippingRetrivalServiceController.getTypes();



	}

}
