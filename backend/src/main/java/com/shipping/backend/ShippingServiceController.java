package com.shipping.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShippingServiceController {

    @GetMapping("/getTypes")
    public String[] getTypes() {
        String[] types = {"Box", "Bag"};
        return types;
    }
}
