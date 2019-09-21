package com.shipping.backend.services;

import com.shipping.backend.entities.PackageType;

import java.io.IOException;
import java.util.List;

public interface QueueResponseHandler {
    public List<PackageType> getTypes();
}
