package controller;

import dataTransfer.LocationDTO;
import java.util.List;
import response.Response;
import service.LocationService;
import validation.LocationValidator;

public class LocationController {

    private final LocationService service;
    
    public LocationController(LocationService service){
        this.service= service;
    }

    public Response createLocation(LocationDTO dto) {
        Response validationResponse= LocationValidator.validate(dto);
        if (!validationResponse.isSuccess()) {
            return validationResponse; 
        }
        return service.createLocation(dto);
    }
    
    public List<LocationDTO> getAllLocation() {
        return service.getAllLocations();
    }

    public LocationService getService() {
        return service;
    }
    
}
