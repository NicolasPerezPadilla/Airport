package validation;

import dataTransfer.PlaneDTO;
import response.Response;

public class PlaneValidator {

    public static Response validate(PlaneDTO dto) {
        if (!dto.getId().matches("[A-Z]{2}\\d{5}"))
            return new Response(false, "Plane ID must follow format XXYYYYY (2 letters + 5 digits).");

        if (isEmpty(dto.getBrand()) || isEmpty(dto.getModel()))
            return new Response(false, "Brand and model must not be empty.");

        return new Response(true, "Valid plane.");
    }

    private static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}

