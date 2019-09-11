package com.shipping.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shipping.backend.entities.PackageTypeResponse;

import java.io.IOException;
import java.util.List;

public interface ShippingRetrivalService {
    public List<PackageTypeResponse> getTypes() throws IOException;
}
