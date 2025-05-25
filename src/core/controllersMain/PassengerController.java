package core.controllersMain;

import core.models.Passenger;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.PassengerStorage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PassengerController {

    public static Response registerPassenger(String idStr, String firstname, String lastname, String yearStr,
            String monthStr, String dayStr, String phoneCodeStr,
            String phoneStr, String country) {
        try {
            // Validar campos vacíos
            if (idStr.isEmpty() || firstname.isEmpty() || lastname.isEmpty()
                    || yearStr.isEmpty() || monthStr.isEmpty() || dayStr.isEmpty()
                    || phoneCodeStr.isEmpty() || phoneStr.isEmpty() || country.isEmpty()) {
                return new Response("All fields must be filled", Status.BAD_REQUEST);
            }

            // Parseo
            long id = Long.parseLong(idStr);
            int year = Integer.parseInt(yearStr);
            int month = Integer.parseInt(monthStr);
            int day = Integer.parseInt(dayStr);
            int phoneCode = Integer.parseInt(phoneCodeStr);
            long phone = Long.parseLong(phoneStr);

            // Validar rangos
            if (id < 0 || id > 99999999) {
                return new Response("ID must be a positive number with up to 15 digits.", Status.BAD_REQUEST);
            }

            if (phoneCode < 0 || phoneCode > 999) {
                return new Response("Phone code must be a number between 0 and 999.", Status.BAD_REQUEST);
            }

            if (phone < 0 || phone > 999999999) {
                return new Response("Phone number must be a number between 0 and 999999999.", Status.BAD_REQUEST);
            }

            LocalDate birthDate;
            try {
                birthDate = LocalDate.of(year, month, day);
            } catch (DateTimeParseException e) {
                return new Response("Invalid birth date.", Status.BAD_REQUEST);
            }

            // Validar duplicados
            PassengerStorage storage = PassengerStorage.getInstance();
            if (storage.findById(id) != null) {
                return new Response("A passenger with this ID already exists.", Status.BAD_REQUEST);
            }

            // Crear y guardar pasajero
            Passenger passenger = new Passenger(id, firstname, lastname, birthDate, phoneCode, phone, country);
            PassengerStorage.getInstance().add(passenger);

            return new Response("Passenger registered", Status.OK, passenger.copy());

        } catch (NumberFormatException e) {
            return new Response("ID, date, and phone values must be numeric.", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

}
