package com.shipping.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private ShippingRequestSender shippingRequestSender;
    private BaseRequestMessage baseRequestMessage;

    public  ShippingRetrivalServiceImpl( final ShippingRequestSender shippingRequestSender,
                                         final BaseRequestMessage baseRequestMessage){
        this.baseRequestMessage=baseRequestMessage;
        this.shippingRequestSender=shippingRequestSender;
    }

    @Override
    public List<PackageTypeResponse> getTypes() throws JsonProcessingException, JsonMappingException, IOException {

            ObjectMapper mapper = new ObjectMapper();
            baseRequestMessage.setType("packageType");
            String requestMessage = mapper.writeValueAsString(baseRequestMessage);
            List<PackageTypeResponse> packageTypes = mapper.readValue(shippingRequestSender.sendRequest(requestMessage), List.class);
            return packageTypes;

    }
}
