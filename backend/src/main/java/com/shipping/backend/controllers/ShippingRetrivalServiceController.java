package com.shipping.backend.controllers;


import com.shipping.backend.entities.PackageTypeResponse;
import com.shipping.backend.services.ShippingRetrivalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ShippingService")
public class ShippingRetrivalServiceController {

    private final static Logger log = LoggerFactory.getLogger(ShippingRetrivalServiceController.class);

    private ShippingRetrivalService shippingRetrivalService;

    public ShippingRetrivalServiceController(final ShippingRetrivalService shippingRetrivalService){
        this.shippingRetrivalService=shippingRetrivalService;
    }

    @GetMapping("/getTypes")
    public List<String> getTypes() throws IOException {
        List<String> packageTypes = new ArrayList<String>();
        List<PackageTypeResponse> packageTypeResponseList = shippingRetrivalService.getTypes();
        for (PackageTypeResponse type: packageTypeResponseList){
            packageTypes.add(type.getDescription());
        }
        return packageTypes;
    }
}
