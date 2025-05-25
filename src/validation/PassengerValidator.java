package validation;

import dataTransfer.PassengerDTO;
import response.Response;
import java.time.LocalDate;

public class PassengerValidator {

    public static Response validate(PassengerDTO dto) {
        if (dto.getId() < 0 || String.valueOf(dto.getId()).length() > 15)
            return new Response(false, "ID must be ≥ 0 and at most 15 digits.");

        if (dto.getBirthDate() == null || dto.getBirthDate().isAfter(LocalDate.now()))
            return new Response(false, "Invalid birth date.");

        if (dto.getCountryPhoneCode() < 0 || String.valueOf(dto.getCountryPhoneCode()).length() > 3)
            return new Response(false, "Country phone code must be ≥ 0 and ≤ 3 digits.");

        if (dto.getPhone() < 0 || String.valueOf(dto.getPhone()).length() > 11)
            return new Response(false, "Phone must be ≥ 0 and ≤ 11 digits.");

        if (isEmpty(dto.getFirstName()) || isEmpty(dto.getLastName()) || isEmpty(dto.getCountry()))
            return new Response(false, "Fields cannot be empty.");

        return new Response(true, "Valid passenger.");
    }

    private static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}

