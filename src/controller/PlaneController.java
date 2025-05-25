package controller;

import dataTransfer.PlaneDTO;
import java.util.List;
import response.Response;
import service.PlaneService;
import validation.PlaneValidator;

public class PlaneController {

    private final PlaneService service;
    
    public PlaneController(PlaneService service){
        this.service= service;
    }

    public Response createPlane(PlaneDTO dto) {
        Response validationResponse= PlaneValidator.validate(dto);
        if (!validationResponse.isSuccess()) {
            return validationResponse; 
        }
        return service.createPlane(dto);
    }
  
    public List<PlaneDTO> getAllPlane() {
        return service.getAllPlanes();
    }

    public PlaneService getService() {
        return service;
    }
    
    public int getNumFlights(PlaneDTO plane){
        return service.getNumFlights(plane);
    }
}

