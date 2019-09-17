package com.shipping.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.shipping.backend.config.AppConfig;
import com.shipping.backend.entities.BaseRequestMessage;
import com.shipping.backend.entities.PackageTypeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ShippingRetrivalServiceImpl implements  ShippingRetrivalService {

    private final static Logger log = LoggerFactory.getLogger(ShippingRetrivalServiceImpl.class);


    private AppConfig appConfig;
    private ShippingQueue shippingRequestSender;
    private ObjectMapper mapper;

    public  ShippingRetrivalServiceImpl( final ShippingQueue shippingRequestSender,
                                         final ObjectMapper mapper,
                                         final AppConfig appConfig){
        this.shippingRequestSender=shippingRequestSender;
        this.mapper=mapper;
        this.appConfig=appConfig;
    }

    @Override
    public List<PackageTypeResponse> getTypes() throws JsonProcessingException, JsonMappingException, IOException {

            BaseRequestMessage baseRequestMessage = new BaseRequestMessage();
            baseRequestMessage.setType(appConfig.getPackageTypes());
            String requestMessage = mapper.writeValueAsString(baseRequestMessage);
            CollectionType responseType = mapper.getTypeFactory().constructCollectionType(List.class, PackageTypeResponse.class);
            List<PackageTypeResponse> packageTypes = mapper.readValue(shippingRequestSender.sendRequest(requestMessage), responseType);
            return packageTypes;

    }
}
