package service;

import dataTransfer.LocationDTO;
import model.Location;
import response.Response;
import java.util.ArrayList;

public class LocationService {

    private final ArrayList<Location> locations = new ArrayList<>();

    public LocationService(ArrayList<Location> locationsJson) {
        for(Location l: locationsJson){
            locations.add(l);
        }
    }
    
    ArrayList<Location> getLocations(){
        return locations;
    }

    // Crear una nueva ubicación a partir de un DTO
    public Response createLocation(LocationDTO dto) {
        for (Location loc : locations) {
            if (loc.getAirportId().equals(dto.getAirportId())) {
                return new Response(false, "Location with this ID already exists.");
            }
        }

        Location location = new Location(
                dto.getAirportId(),
                dto.getAirportName(),
                dto.getAirportCity(),
                dto.getAirportCountry(),
                dto.getAirportLatitude(),
                dto.getAirportLongitude()
        );

        locations.add(location);
        return new Response(true, "Location created successfully.");
    }

    // Obtener todas las ubicaciones como DTOs
    public ArrayList<LocationDTO> getAllLocations() {
        ArrayList<LocationDTO> dtos = new ArrayList<>();
        for (Location loc : locations) {
            dtos.add(toDTO(loc));
        }
        return dtos;
    }

    // Buscar una ubicación por ID
    public Location getLocationById(String airportId) {
        for (Location loc : locations) {
            if (loc.getAirportId().equals(airportId)) {
                return loc;
            }
        }
        return null;
    }

    public LocationDTO toDTO(Location location) {
        if (location == null) {
            return null;
        }

        return new LocationDTO(
                location.getAirportId(),
                location.getAirportName(),
                location.getAirportCity(),
                location.getAirportCountry(),
                location.getAirportLatitude(),
                location.getAirportLongitude()
        );
    }

    // Actualizar una ubicación existente usando un DTO
    public boolean updateLocation(LocationDTO dto) {
        for (Location loc : locations) {
            if (loc.getAirportId().equals(dto.getAirportId())) {
                // Se asume que ID no cambia
                locations.remove(loc);
                Location updated = new Location(
                        dto.getAirportId(),
                        dto.getAirportName(),
                        dto.getAirportCity(),
                        dto.getAirportCountry(),
                        dto.getAirportLatitude(),
                        dto.getAirportLongitude()
                );
                locations.add(updated);
                return true;
            }
        }
        return false;
    }

    // Eliminar una ubicación
    public boolean deleteLocation(String airportId) {
        return locations.removeIf(loc -> loc.getAirportId().equals(airportId));
    }

}
