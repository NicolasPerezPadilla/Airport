package validation;


import dataTransfer.LocationDTO;
import response.Response;

public class LocationValidator {

    public static Response validate(LocationDTO dto) {
        if (!dto.getAirportId().matches("[A-Z]{3}"))
            return new Response(false, "Location ID must be 3 uppercase letters.");

        if (dto.getAirportLatitude() < -90 || dto.getAirportLatitude() > 90)
            return new Response(false, "Latitude must be in range [-90, 90].");

        if (dto.getAirportLongitude() < -180 || dto.getAirportLongitude() > 180)
            return new Response(false, "Longitude must be in range [-180, 180].");

        if (!hasMaxFourDecimals(dto.getAirportLatitude()) || !hasMaxFourDecimals(dto.getAirportLongitude()))
            return new Response(false, "Latitude/Longitude must have at most 4 decimal places.");

        if (isEmpty(dto.getAirportCity()) || isEmpty(dto.getAirportCountry()))
            return new Response(false, "City and country must not be empty.");

        return new Response(true, "Valid location.");
    }

    private static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static boolean hasMaxFourDecimals(double value) {
        String[] parts = Double.toString(Math.abs(value)).split("\\.");
        return parts.length < 2 || parts[1].length() <= 4;
    }
}

