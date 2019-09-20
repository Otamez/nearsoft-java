package com.shipping.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.shipping.backend.config.AppConfiguration;
import com.shipping.backend.config.CustomException;
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
    private ObjectMapper mapper;
    private AppConfiguration appConfiguration;

    public QueueResponseHandlerImp(final QueueClient shippingRequestSender,
                                   final AppConfiguration appConfiguration,
                                   final ObjectMapper mapper){
        this.shippingRequestSender=shippingRequestSender;
        this.appConfiguration=appConfiguration;
        this.mapper=mapper;
    }

    @Override
    public List getTypes() {

        QueueRequestMessage baseRequestMessage = new QueueRequestMessage();
        baseRequestMessage.setType(appConfiguration.getPackageTypes());
        log.info("Generating package type list");

        try {
            List<PackageType> packageTypes = mapper.readValue(shippingRequestSender.sendRequest(mapper.writeValueAsString(baseRequestMessage)),
                    mapper.getTypeFactory().constructCollectionType(List.class, PackageType.class));
            log.info("Package type list successfully generated");
            return packageTypes;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomException("Please contact your administrator");
        }
    }

}
