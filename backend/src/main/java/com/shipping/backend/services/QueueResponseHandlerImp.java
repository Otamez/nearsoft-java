package com.shipping.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.shipping.backend.config.AppConfiguration;
import com.shipping.backend.config.QueueClient;
import com.shipping.backend.entities.QueueRequestMessage;
import com.shipping.backend.entities.PackageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class QueueResponseHandlerImp implements QueueResponseHandler {

    private final static Logger log = LoggerFactory.getLogger(QueueResponseHandlerImp.class);

    private QueueClient shippingRequestSender;
    private AppConfiguration appConfiguration = new AppConfiguration();

    public QueueResponseHandlerImp(final QueueClient shippingRequestSender){
        this.shippingRequestSender=shippingRequestSender;
    }

    @Override
    public List<PackageType> getTypes() throws JsonProcessingException, JsonMappingException, IOException {

            QueueRequestMessage baseRequestMessage = new QueueRequestMessage();
            ObjectMapper mapper = new ObjectMapper();
            baseRequestMessage.setType(appConfiguration.getPackageTypes());
            String requestMessage = mapper.writeValueAsString(baseRequestMessage);
            CollectionType responseType = mapper.getTypeFactory().constructCollectionType(List.class, PackageType.class);
            List<PackageType> packageTypes = mapper.readValue(shippingRequestSender.sendRequest(requestMessage), responseType);
            return packageTypes;

    }
}
