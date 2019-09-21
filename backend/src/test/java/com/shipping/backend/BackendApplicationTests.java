package com.shipping.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< HEAD
import com.shipping.backend.config.AppConfig;
import com.shipping.backend.controllers.ShippingRetrivalServiceController;
import com.shipping.backend.entities.BaseRequestMessage;
import com.shipping.backend.entities.PackageTypeResponse;
import com.shipping.backend.services.ShippingQueue;
import com.shipping.backend.services.ShippingQueueImpl;
import com.shipping.backend.services.ShippingRetrivalService;
import com.shipping.backend.services.ShippingRetrivalServiceImpl;
import org.junit.BeforeClass;
=======
import com.shipping.backend.config.AppConfiguration;
import com.shipping.backend.config.CustomException;
import com.shipping.backend.config.QueueClient;
import com.shipping.backend.entities.PackageType;
import com.shipping.backend.entities.QueueRequestMessage;
import com.shipping.backend.services.QueueResponseHandler;
import com.shipping.backend.services.QueueResponseHandlerImp;
import org.junit.Before;
>>>>>>> 218d14fd143027dc84ff754e4908b3309aaca5dd
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
<<<<<<< HEAD

public class BackendApplicationTests {

    private static RabbitTemplate rabbitTemplate;
    private static ShippingQueue shippingRequestSender;
    private static ShippingRetrivalService shippingRetrivalService;
    private static ShippingRetrivalServiceController shippingRetrivalServiceController;
    private static BaseRequestMessage baseRequestMessage = new BaseRequestMessage();
    private static ObjectMapper mapper = new ObjectMapper();
    private static AppConfig appConfig = new AppConfig();
=======
import static org.mockito.Mockito.when;

public class BackendApplicationTests {

    private  RabbitTemplate rabbitTemplate;
    private  QueueClient shippingRequestSender;
    private  QueueResponseHandler queueResponseHandler;
    private  AppConfiguration appConfiguration;
    private  QueueRequestMessage queueRequestMessage;
    private  ObjectMapper mapper;
>>>>>>> 218d14fd143027dc84ff754e4908b3309aaca5dd

    @Before
    public void setUp(){

        //Initialize functional classes for testing
        mapper = new ObjectMapper();
        queueRequestMessage = new QueueRequestMessage();
        appConfiguration = new AppConfiguration();
        rabbitTemplate = mock(RabbitTemplate.class);
<<<<<<< HEAD
        shippingRequestSender = new ShippingQueueImpl(rabbitTemplate);
        shippingRetrivalService = new ShippingRetrivalServiceImpl(shippingRequestSender, mapper, appConfig);
        shippingRetrivalServiceController = new ShippingRetrivalServiceController(shippingRetrivalService);
=======
        shippingRequestSender = new QueueClient(rabbitTemplate);
        queueResponseHandler = new QueueResponseHandlerImp(shippingRequestSender, appConfiguration, mapper);
>>>>>>> 218d14fd143027dc84ff754e4908b3309aaca5dd
    }

	@Test
	public void getPackageTypesTestSuccess()  {

        //Set request message to get package types
        queueRequestMessage.setType("packageType");

<<<<<<< HEAD
        appConfig.setPackageTypes(baseRequestMessage.getType());

	    //Add some mock values for the test response
        packageTypeResponse1.setId(1);
        packageTypeResponse1.setDescription("Box");
        packageTypeResponse1.setPrice(10);
=======
        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setPackageTypes("packageType");
>>>>>>> 218d14fd143027dc84ff754e4908b3309aaca5dd

        //Mocked Response Values
        PackageType packageType = new PackageType();
        packageType.setId(1);
        packageType.setDescription("Box");
        packageType.setPrice(100);

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(
                packageType.toString());
        List packageTypesList = queueResponseHandler.getTypes();

        assertEquals(packageTypesList.size(),1);
        assertEquals(packageTypesList.get(0).getClass(), PackageType.class);
        assertThat(packageTypesList.get(0), hasProperty("description", is("Box")));

	}

    @Test(expected = CustomException.class)
    public void getPackageTypesTestFailure()  {
        //Set request message to get package types
        queueRequestMessage.setType("packageType");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setPackageTypes("packageType");

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(null);
        List packageTypesList = queueResponseHandler.getTypes();

    }

}
