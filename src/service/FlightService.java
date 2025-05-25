package service;

import dataTransfer.FlightDTO;
import dataTransfer.LocationDTO;
import dataTransfer.PlaneDTO;
import java.time.LocalDateTime;
import model.Flight;
import model.Location;
import model.Plane;
import response.Response;
import service.LocationService;
import service.PlaneService;
import java.util.ArrayList;
import loader.DataLoader;
import model.Passenger;

public class FlightService {

    private final ArrayList<Flight> flights = new ArrayList<>();
    private final PlaneService planeService;
    private final LocationService locationService;

    public FlightService(PlaneService planeService, LocationService locationService, ArrayList<Flight> flightsJson) {
        this.planeService = planeService;
        this.locationService = locationService;
        for(Flight f: flightsJson){
            flights.add(f);
        }
    }

    public Response createFlight(FlightDTO dto) {
        for (Flight f : flights) {
            if (f.getId().equals(dto.getId())) {
                return new Response(false, "Flight with this ID already exists.");
            }
        }

        Plane plane = planeService.getPlaneById(dto.getPlaneId());
        if (plane == null) {
            return new Response(false, "Plane not found.");
        }

        Location departure = locationService.getLocationById(dto.getDepartureLocationId());
        Location arrival = locationService.getLocationById(dto.getArrivalLocationId());

        if (departure == null || arrival == null) {
            return new Response(false, "Departure or Arrival location not found.");
        }

        Location scale = null;
        if (dto.getScaleLocationId() != null && !dto.getScaleLocationId().isEmpty()) {
            scale = locationService.getLocationById(dto.getScaleLocationId());
            if (scale == null) {
                return new Response(false, "Scale location not found.");
            }
        }

        Flight flight;
        if (scale != null) {
            flight = new Flight(
                    dto.getId(),
                    plane,
                    departure,
                    scale,
                    arrival,
                    dto.getDepartureDate(),
                    dto.getHoursDurationArrival(),
                    dto.getMinutesDurationArrival(),
                    dto.getHoursDurationScale(),
                    dto.getMinutesDurationScale()
            );
        } else {
            flight = new Flight(
                    dto.getId(),
                    plane,
                    departure,
                    arrival,
                    dto.getDepartureDate(),
                    dto.getHoursDurationArrival(),
                    dto.getMinutesDurationArrival()
            );
        }
        flights.add(flight);
        return new Response(true, "Flight added successfully.");
    }

    public ArrayList<FlightDTO> getAllFlights() {
        ArrayList<FlightDTO> flightDTOs = new ArrayList<>();
        for (Flight f : flights) {
            flightDTOs.add(toDTO(f));
        }
        return flightDTOs;
    }

    public Flight getFlightById(String id) {
        for (Flight f : flights) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    FlightDTO toDTO(Flight f) {
        return new FlightDTO(
                f.getId(),
                f.getPlane().getId(),
                f.getDepartureLocation().getAirportId(),
                f.getScaleLocation() != null ? f.getScaleLocation().getAirportId() : null,
                f.getArrivalLocation().getAirportId(),
                f.getDepartureDate(),
                f.getHoursDurationArrival(),
                f.getMinutesDurationArrival(),
                f.getHoursDurationScale(),
                f.getMinutesDurationScale()
        );
    }
    
    public ArrayList<Flight> getFlights(){
        return flights;
    }

    public LocalDateTime calculateArrivalDate(FlightDTO flightDTO) {
        for (Flight flight : flights) {
            if (flight.getId() == flightDTO.getId()) {
                return flight.calculateArrivalDate();
            }
        }
        return null;
    }

    public LocationDTO getDepartureLocation(FlightDTO flightDTO) {
        for (Flight flight : flights) {
            if (flight.getId() == flightDTO.getId()) {
                Location location = flight.getDepartureLocation();
                return locationService.toDTO(location);
            }
        }
        return null;
    }

    public LocationDTO getArrivalLocation(FlightDTO flightDTO) {
        for (Flight flight : flights) {
            if (flight.getId() == flightDTO.getId()) {
                Location location = flight.getArrivalLocation();
                return locationService.toDTO(location);
            }
        }
        return null;
    }

    public Response delayFlight(FlightDTO flightDTO, int hours, int minutes) {
        if (hours < 0 || minutes < 0 || (hours == 0 && minutes == 0)) {
            return new Response(false, "Delay time must be greater than 00:00.");
        }

        Flight flight = this.mapDTOToModel(flightDTO);
        if (flight == null) {
            return new Response(false, "Flight not found.");
        }

        flight.delay(hours, minutes);

        return new Response(true, "Flight delayed successfully.");
    }

    //Se define como función Package-Private por seguridad
    Flight mapDTOToModel(FlightDTO dto) {
        Plane plane = planeService.getPlaneById(dto.getPlaneId());
        if (plane == null) {
            return null;
        }

        Location departure = locationService.getLocationById(dto.getDepartureLocationId());
        Location arrival = locationService.getLocationById(dto.getArrivalLocationId());
        if (departure == null || arrival == null) {
            return null;
        }

        Location scale = null;
        if (dto.getScaleLocationId() != null && !dto.getScaleLocationId().isEmpty()) {
            scale = locationService.getLocationById(dto.getScaleLocationId());
            if (scale == null) {
                return null;
            }
        }

        Flight flight;
        if (scale != null) {
            flight = new Flight(
                    dto.getId(),
                    plane,
                    departure,
                    scale,
                    arrival,
                    dto.getDepartureDate(),
                    dto.getHoursDurationArrival(),
                    dto.getMinutesDurationArrival(),
                    dto.getHoursDurationScale(),
                    dto.getMinutesDurationScale()
            );
        } else {
            flight = new Flight(
                    dto.getId(),
                    plane,
                    departure,
                    arrival,
                    dto.getDepartureDate(),
                    dto.getHoursDurationArrival(),
                    dto.getMinutesDurationArrival()
            );
        }

        return flight;
    }

    public PlaneDTO getPlane(FlightDTO flight) {
        return planeService.getPlane(flight.getPlaneId());
    }

    public LocationDTO getScaleLocation(FlightDTO flightDTO) {
        for (Flight flight : flights) {
            if (flight.getId() == flightDTO.getId()) {
                Location location = flight.getScaleLocation();
                return locationService.toDTO(location);
            }
        }
        return null;
    }

    public int getNumPassengers(FlightDTO flightDTO) {
        for (Flight flight : flights) {
            if (flight.getId().equals(flightDTO.getId())) {
                return flight.getNumPassengers();
            }
        }
        return 0;
    }
    
    public void addPassenger(FlightDTO flightDTO, Passenger passenger){
        for(Flight flight: flights){
            if(flight.getId().equals(flightDTO.getId())){
                flight.addPassenger(passenger);
            }
        }
    }

}
