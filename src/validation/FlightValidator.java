package validation;

import dataTransfer.FlightDTO;
import response.Response;
import java.time.LocalDateTime;

public class FlightValidator {

    public static Response validate(FlightDTO dto) {
        if (!dto.getId().matches("[A-Z]{3}\\d{3}")) {
            return new Response(false, "Flight ID must follow format XXXYYY (3 letters + 3 digits).");
        }

        if (dto.getDepartureDate() == null || dto.getDepartureDate().isBefore(LocalDateTime.now())) {
            return new Response(false, "Invalid or past departure date.");
        }

        if (dto.getHoursDurationArrival() == 0 && dto.getMinutesDurationArrival() == 0) {
            return new Response(false, "Arrival duration must be greater than 00:00.");
        }

        if (dto.getDepartureLocationId() == null || dto.getArrivalLocationId() == null || dto.getPlaneId() == null) {
            return new Response(false, "Plane and locations must be specified.");
        }

        if (dto.getScaleLocationId() == null || dto.getScaleLocationId().isEmpty()) {
            if (dto.getHoursDurationScale() != 0 || dto.getMinutesDurationScale() != 0) {
                return new Response(false, "Scale duration must be 00:00 if no scale location.");
            }
        }

        return new Response(true, "Valid flight.");
    }
}
