package controller;

import dataTransfer.FlightDTO;
import dataTransfer.LocationDTO;
import dataTransfer.PlaneDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Location;
import response.Response;
import service.FlightService;
import validation.FlightValidator;

public class FlightController {

    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    public Response createFlight(FlightDTO dto) {
        Response validationResponse = FlightValidator.validate(dto);
        if (!validationResponse.isSuccess()) {
            return validationResponse; 
        }
        return service.createFlight(dto); 
    }

    public ArrayList<FlightDTO> getAllFlight() {
        return service.getAllFlights();
    }

    public Response delay(FlightDTO flight, int hour, int minute) {
        return service.delayFlight(flight, hour, minute);
    }

    public LocalDateTime calculateArrivalDate(FlightDTO flight) {
        return service.calculateArrivalDate(flight);
    }

    public LocationDTO getDepartureLocation(FlightDTO flight) {
        return service.getDepartureLocation(flight);
    }

    public LocationDTO getArrivalLocation(FlightDTO flight) {
        return service.getArrivalLocation(flight);
    }

    public LocationDTO getScaleLocation(FlightDTO flight) {
        return service.getScaleLocation(flight);
    }

    public PlaneDTO getPlane(FlightDTO flight) {
        return service.getPlane(flight);
    }

    public int getNumPassengers(FlightDTO flight) {
        return service.getNumPassengers(flight);
    }

}
