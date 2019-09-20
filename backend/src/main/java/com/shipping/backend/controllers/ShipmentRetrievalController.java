package com.shipping.backend.controllers;


import com.shipping.backend.services.QueueResponseHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shipment-management")
public class ShipmentRetrievalController {

    private QueueResponseHandler queueResponseHandler;

    public ShipmentRetrievalController(final QueueResponseHandler queueResponseHandler){
        this.queueResponseHandler = queueResponseHandler;
    }

    @GetMapping("/package-types")
    public List<String> getTypes() throws IOException {
        return queueResponseHandler.getTypes().stream().map(packageType -> packageType.getDescription()).collect(Collectors.toList());
    }
}
