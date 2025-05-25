package controller;

import dataTransfer.FlightDTO;
import dataTransfer.PassengerDTO;
import java.util.ArrayList;
import service.PassengerService;

import java.util.List;
import response.Response;
import service.FlightService;
import validation.PassengerValidator;

public class PassengerController {
    private final PassengerService service;
    
    public PassengerController(PassengerService service) {
        this.service = service; 
    }

    public Response createPassenger(PassengerDTO dto) {
        Response validationResponse= PassengerValidator.validate(dto);
        if (!validationResponse.isSuccess()) {
            return validationResponse; 
        }
        return service.createPassenger(dto);
    }

    public PassengerDTO getPassengerById(long id) {
        return service.getPassengerById(id);
    }

    public ArrayList<PassengerDTO> getAllPassengers() {
        return service.getAllPassengers();
    }

    public boolean updatePassenger(PassengerDTO dto) {
        return service.updatePassenger(dto);
    }

    public boolean deletePassenger(long id) {
        return service.deletePassenger(id);
    }

    public Response addFlightToPassenger(PassengerDTO passenger, FlightDTO flight){
        return service.addFlightToPassenger(passenger, flight);
    }
    
    public PassengerService getPassengerService(){
        return service;
    }

    public ArrayList<FlightDTO> getAllFlights(PassengerDTO passenger) {
        return service.getAllFlights(passenger);
    }
    
    public int calculateAge(PassengerDTO passenger){
        return service.calculateAge(passenger);
    }
    
    public int getNumFlights(PassengerDTO passenger){
        return service.getNumFlights(passenger);
    }
    
    public String generateFullPhone(PassengerDTO passenger){
        return service.generateFullPhone(passenger);
    }
}
