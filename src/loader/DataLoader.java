package loader;

import dataTransfer.FlightDTO;
import dataTransfer.LocationDTO;
import dataTransfer.PassengerDTO;
import dataTransfer.PlaneDTO;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {

    public static ArrayList<Passenger> loadPassengers() {
        ArrayList<Passenger> passengers = new ArrayList<>();
        InputStream is = DataLoader.class.getClassLoader().getResourceAsStream("json/passengers.json");
        if (is == null) {
            return passengers;
        }

        JSONArray array = new JSONArray(new JSONTokener(is));
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);

            PassengerDTO dto = new PassengerDTO();
            dto.setId(obj.getLong("id"));
            dto.setFirstName(obj.getString("firstname"));
            dto.setLastName(obj.getString("lastname"));
            dto.setBirthDate(LocalDate.parse(obj.getString("birthDate")));
            dto.setCountryPhoneCode(obj.getInt("countryPhoneCode"));
            dto.setPhone(obj.getLong("phone"));
            dto.setCountry(obj.getString("country"));

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
        }
        return passengers;
    }

    public static ArrayList<Location> loadLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        InputStream is = DataLoader.class.getClassLoader().getResourceAsStream("json/locations.json");
        if (is == null) {
            return locations;
        }

        JSONArray array = new JSONArray(new JSONTokener(is));
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);

            LocationDTO dto = new LocationDTO();
            dto.setAirportId(obj.getString("airportId"));
            dto.setAirportName(obj.getString("airportName"));
            dto.setAirportCity(obj.getString("airportCity"));
            dto.setAirportCountry(obj.getString("airportCountry"));
            dto.setAirportLatitude(obj.getDouble("airportLatitude"));
            dto.setAirportLongitude(obj.getDouble("airportLongitude"));

            Location location = new Location(
                    dto.getAirportId(),
                    dto.getAirportName(),
                    dto.getAirportCity(),
                    dto.getAirportCountry(),
                    dto.getAirportLatitude(),
                    dto.getAirportLongitude()
            );

            locations.add(location);
        }
        return locations;
    }

    public static ArrayList<Plane> loadPlanes() {
        ArrayList<Plane> planes = new ArrayList<>();
        InputStream is = DataLoader.class.getClassLoader().getResourceAsStream("json/planes.json");
        if (is == null) {
            return planes;
        }

        JSONArray array = new JSONArray(new JSONTokener(is));
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);

            PlaneDTO dto = new PlaneDTO();
            dto.setId(obj.getString("id"));
            dto.setModel(obj.getString("model"));
            dto.setBrand(obj.getString("brand"));
            dto.setMaxCapacity(obj.getInt("maxCapacity"));
            dto.setAirline(obj.getString("airline"));

            Plane plane = new Plane(
                    dto.getId(),
                    dto.getModel(),
                    dto.getBrand(),
                    dto.getMaxCapacity(),
                    dto.getAirline()
            );

            planes.add(plane);
        }
        return planes;
    }

    public static ArrayList<Flight> loadFlights(ArrayList<Location> locations, ArrayList<Plane> planes) {
        ArrayList<Flight> flights = new ArrayList<>();
        InputStream is = DataLoader.class.getClassLoader().getResourceAsStream("json/flights.json");
        if (is == null) {
            return flights;
        }

        JSONArray array = new JSONArray(new JSONTokener(is));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            String id = obj.getString("id");
            String planeId = obj.getString("plane");
            String departureId = obj.getString("departureLocation");
            String arrivalId = obj.getString("arrivalLocation");
            String scaleId = obj.optString("scaleLocation", "");
            LocalDateTime departureDate = LocalDateTime.parse(obj.getString("departureDate"), formatter);

            // Buscar Plane
            Plane plane = null;
            for (Plane p : planes) {
                if (p.getId().equals(planeId)) {
                    plane = p;
                    break;
                }
            }

            // Buscar Locations
            Location departure = null, arrival = null, scale = null;
            for (Location loc : locations) {
                if (loc.getAirportId().equals(departureId)) {
                    departure = loc;
                }
                if (loc.getAirportId().equals(arrivalId)) {
                    arrival = loc;
                }
                if (!scaleId.isEmpty() && loc.getAirportId().equals(scaleId)) {
                    scale = loc;
                }
            }

            Flight flight;
            if (scale != null) {
                flight = new Flight(
                        id, plane, departure, scale, arrival,
                        departureDate,
                        obj.getInt("hoursDurationArrival"),
                        obj.getInt("minutesDurationArrival"),
                        obj.getInt("hoursDurationScale"),
                        obj.getInt("minutesDurationScale")
                );
            } else {
                flight = new Flight(
                        id, plane, departure, arrival,
                        departureDate,
                        obj.getInt("hoursDurationArrival"),
                        obj.getInt("minutesDurationArrival")
                );
            }

            flights.add(flight);
        }

        return flights;
    }

}
