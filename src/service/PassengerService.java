package service;

import dataTransfer.FlightDTO;
import dataTransfer.PassengerDTO;
import model.Passenger;
import service.FlightService;
import service.LocationService;
import service.PlaneService;
import java.util.ArrayList;
import java.util.List;
import loader.DataLoader;
import model.Flight;
import response.Response;

public class PassengerService {

    private ArrayList<Passenger> passengers = new ArrayList<>();
    private LocationService locationService;
    private PlaneService planeService;
    private FlightService flightService;
    
    public PassengerService(FlightService flightService, LocationService locationService, PlaneService planeService, ArrayList<Passenger> passengersJson) {
        this.flightService= flightService;
        this.locationService= locationService;
        this.planeService= planeService;
        for(Passenger p: passengersJson){
            passengers.add(p);
        }
    }

    //Se crea un pasajero en formato del Modelo, se devuelve el Data Transfer Object para visualización
    public Response createPassenger(PassengerDTO dto) {
        for (Passenger p : passengers) {
            if (p.getId()==dto.getId()) {
                return new Response(false, "Passenger with this ID already exists.");
            }
        }
        Passenger passenger = new Passenger(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getCountryPhoneCode(),
                dto.getPhone(),
                dto.getCountry()
        );
        passengers.add(passenger);
        return new Response(true,"Passenger created successfully.");
    }

    //Se busca pasajero por ID, se devuelve en formato del Data Transfer Object
    public PassengerDTO getPassengerById(long id) {
        for (Passenger p : passengers) {
            if (p.getId() == id) {
                return toDTO(p);
            }
        }
        return null;
    }

    public ArrayList<FlightDTO> getAllFlights(PassengerDTO passenger) {
        ArrayList<FlightDTO> flights = new ArrayList<>();
        for (Passenger p : passengers) {
            if (p.getId() == passenger.getId()) {
                for (Flight flight : p.getFlights()) {
                    FlightDTO flightDTO = flightService.toDTO(flight);
                    flights.add(flightDTO);
                }
                return flights;
            }
        }
        return flights;
    }

    public int calculateAge(PassengerDTO passengerDTO) {
        for (Passenger passenger : passengers) {
            if (passenger.getId() == passengerDTO.getId()) {
                return passenger.calculateAge();
            }
        }
        return 0;
    }

    public String generateFullPhone(PassengerDTO passengerDTO) {
        for (Passenger passenger : passengers) {
            if (passenger.getId() == passengerDTO.getId()) {
                return passenger.generateFullPhone();
            }
        }
        return null;
    }

    public int getNumFlights(PassengerDTO passengerDTO) {
        for (Passenger passenger : passengers) {
            if (passenger.getId() == passengerDTO.getId()) {
                return passenger.getNumFlights();
            }
        }
        return 0;
    }

    //Se obtienen todos los pasajeros en formato DTO
    public ArrayList<PassengerDTO> getAllPassengers() {
        ArrayList<PassengerDTO> dtos = new ArrayList<>();
        for (Passenger p : passengers) {
            dtos.add(toDTO(p));
        }
        return dtos;
    }

    //Se actualizan datos al Modelo a través del DTO
    public boolean updatePassenger(PassengerDTO dto) {
        for (Passenger p : passengers) {
            if (p.getId() == dto.getId()) {
                p.setFirstname(dto.getFirstName());
                p.setLastname(dto.getLastName());
                p.setBirthDate(dto.getBirthDate());
                p.setCountryPhoneCode(dto.getCountryPhoneCode());
                p.setPhone(dto.getPhone());
                p.setCountry(dto.getCountry());
                return true;
            }
        }
        return false;
    }

    //Se elimina pasajero de la lista
    public boolean deletePassenger(long id) {
        for (Passenger p : passengers) {
            if (p.getId() == id) {
                passengers.remove(p);
                return true;
            }
        }
        return false;
    }

    //Se entrega a formato DTO un pasajero con formato del Modelo
    private PassengerDTO toDTO(Passenger p) {
        return new PassengerDTO(
                p.getId(),
                p.getFirstname(),
                p.getLastname(),
                p.getBirthDate(),
                p.getCountryPhoneCode(),
                p.getPhone(),
                p.getCountry()
        );
    }

    public Response addFlightToPassenger(PassengerDTO passengerDTO, FlightDTO flightDTO) {
        for (Passenger passenger : passengers) {
            if (passengerDTO.getId() == passenger.getId()) {
                System.out.println("Hola");
                // Agrega el diagnóstico aquí
                System.out.println("Cantidad de vuelos en FlightService: " + flightService.getFlights().size());
                for (Flight f : flightService.getFlights()) {
                    if (f == null) {
                        continue;
                    }
                    System.out.println("ID de vuelo en lista: '" + f.getId() + "'");
                    System.out.println("ID vuelo buscado: '" + flightDTO.getId() + "'");

                    if (f.getId().equals(flightDTO.getId())) {
                        System.out.println("Vuelo encontrado!");

                        for (Passenger p : f.getPassengers()) {
                            if (p.getId() == passenger.getId()) {
                                return new Response(false, "Passenger already added to this flight.");
                            }
                        }
                        f.addPassenger(passenger);
                        passenger.addFlight(f);
                        return new Response(true, "Flight added to passenger.");
                    }
                }

                return new Response(true, "Flight not found.");
            }
        }
        return new Response(false, "Passenger not found.");
    }

}
